package gateway.com.example.user.demo.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gateway.com.example.user.demo.constant.UserServiceConstant;
import gateway.com.example.user.demo.model.User;
import gateway.com.example.user.demo.repo.UserRepository;
import gateway.com.example.user.demo.request.OrderRequestDto;
import gateway.com.example.user.demo.request.ProductRequestDto;
import gateway.com.example.user.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	UserRepository userRepository;

	@Autowired
	Environment env;

	@Value("${gateway.url}")
	private String gateWayUrl;
	

	@Override
	public ResponseEntity<?> saveUserDetails(User user) {

		User users = User.builder().firstName(user.getFirstName()).email(user.getEmail()).build();
		return ResponseEntity.ok(userRepository.save(users));
	}

	@Override
	public ResponseEntity<?> orderProduct(OrderRequestDto orderRequestDto) throws URISyntaxException {

		String url = gateWayUrl + UserServiceConstant.SAVE_ORDER;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RequestEntity<OrderRequestDto> requestEntity = new RequestEntity<OrderRequestDto>(orderRequestDto, header,
				HttpMethod.POST, new URI(url), OrderRequestDto.class);

		return restTemplate.exchange(requestEntity, Object.class);
	}

	@Override
	public ResponseEntity<?> showOrderedList() throws URISyntaxException {
		String url = gateWayUrl + UserServiceConstant.GET_ALL_ORDERS;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		RequestEntity<OrderRequestDto> requestEntity = new RequestEntity<OrderRequestDto>(headers, HttpMethod.GET,
				new URI(url));
		return restTemplate.exchange(requestEntity, Object.class);

	}

	@Override
	public ResponseEntity<?> saveProduct(ProductRequestDto productRequestDto) throws URISyntaxException {

		String url = gateWayUrl + UserServiceConstant.SAVE_PRODUCT;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

//		HttpEntity<ProductRequestDto> httpEntity=new HttpEntity<ProductRequestDto>(productRequestDto, header);
//		return restTemplate.exchange(url, HttpMethod.POST, httpEntity, ProductRequestDto.class);

		RequestEntity<ProductRequestDto> requestEntity = new RequestEntity<ProductRequestDto>(productRequestDto, header,
				HttpMethod.POST, new URI(url), ProductRequestDto.class);

		return restTemplate.exchange(requestEntity, ProductRequestDto.class);

	}

	@Override
	public ResponseEntity<?> showProductList() throws URISyntaxException {
		String url = gateWayUrl + UserServiceConstant.GET_ALL_PRODUCTS;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RequestEntity<ProductRequestDto> requestEntity = new RequestEntity<ProductRequestDto>(header, HttpMethod.GET,
				new URI(url));
		return restTemplate.exchange(requestEntity, Object.class);
	}

	@Override
	public ResponseEntity<?> updateProduct(Integer pId, String prodName, Integer price) throws URISyntaxException {
		String url = gateWayUrl + UserServiceConstant.UPDATE_PRODUCT;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId", pId);
		map.put("prodName", prodName);
		map.put("price", price);

//		https://stackoverflow.com/questions/8297215/spring-resttemplate-get-with-parameters      <----- refer this link
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Entry<String, Object> entry : map.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		RequestEntity<ProductRequestDto> requestEntity = new RequestEntity<ProductRequestDto>(header, HttpMethod.PUT,
				new URI(builder.toUriString()));
		System.out.println(builder.toUriString());
		return restTemplate.exchange( requestEntity, ProductRequestDto.class);
	}

	@Override
	public ResponseEntity<?> updateOrder(Integer id, String productName, Integer qty) throws URISyntaxException {
		String url=gateWayUrl+UserServiceConstant.UPDATE_ORDER+id+"/"+productName+"/"+qty;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		RequestEntity<OrderRequestDto> requestEntity = new RequestEntity<OrderRequestDto>(header, HttpMethod.PUT,new URI(url));
		System.out.println(url);
		return restTemplate.exchange(requestEntity, Object.class);
//		 return ResponseEntity.ok(result.getBody());
	}

	@Override
	public ResponseEntity<?> cancelOrder(Integer Id) throws URISyntaxException {
		String url=gateWayUrl+UserServiceConstant.CANCEL_ORDER+Id;
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		RequestEntity<OrderRequestDto> requestEntity = new RequestEntity<OrderRequestDto>(header, HttpMethod.DELETE,new URI(url));
		System.out.println(url);
		
		return restTemplate.exchange(requestEntity, String.class);
	}

	@Override
	public ResponseEntity<?> getOrderById(Integer Id) throws URISyntaxException, JsonMappingException, JsonProcessingException {


		String url=gateWayUrl+UserServiceConstant.GET_ORDER_BYID+Id;
		ResponseEntity<String> urlJosn=restTemplate.getForEntity(url, String.class,Map.of("id", Id));
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(urlJosn.getBody());
		return ResponseEntity.ok(root);
		
	}

	@Override
	public ResponseEntity<?> getOrderByIdGetForObject(Integer Id)
			throws URISyntaxException, JsonMappingException, JsonProcessingException {
		String url=gateWayUrl+UserServiceConstant.GET_ORDER_BYID+Id;
		String urlJosn=restTemplate.getForObject(url, String.class,Map.of("id", Id));
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(urlJosn);
		return ResponseEntity.ok(root);
	}

	@Override
	public ResponseEntity<?> orderProductPostForObject(OrderRequestDto orderRequestDto) throws URISyntaxException {
		String url = gateWayUrl + UserServiceConstant.SAVE_ORDER;	
		Object rest=restTemplate.postForObject(url, orderRequestDto, Object.class);
		return ResponseEntity.ok(rest);
	}

	@Override
	public ResponseEntity<?> orderProductPostForEntity(OrderRequestDto orderRequestDto) throws URISyntaxException {
		String url = gateWayUrl + UserServiceConstant.SAVE_ORDER;	
		ResponseEntity<Object> rest=restTemplate.postForEntity(url, orderRequestDto, Object.class);
		return ResponseEntity.ok(rest);
	}

}
