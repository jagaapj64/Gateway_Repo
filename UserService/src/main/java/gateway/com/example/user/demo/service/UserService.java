package gateway.com.example.user.demo.service;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import gateway.com.example.user.demo.model.User;
import gateway.com.example.user.demo.request.OrderRequestDto;
import gateway.com.example.user.demo.request.ProductRequestDto;

public interface UserService {

	ResponseEntity<?> saveUserDetails(User user);

	ResponseEntity<?> orderProduct(OrderRequestDto orderRequestDto) throws URISyntaxException;

	ResponseEntity<?> saveProduct(ProductRequestDto productRequestDto) throws URISyntaxException;
	
	ResponseEntity<?> showProductList() throws URISyntaxException;
	
	ResponseEntity<?> showOrderedList() throws URISyntaxException;
	
	ResponseEntity<?> updateProduct(Integer pId,String prodName,Integer price) throws URISyntaxException;
	
	ResponseEntity<?> updateOrder(Integer Id,String prodName,Integer quantity) throws URISyntaxException;
	
	ResponseEntity<?> cancelOrder(Integer Id) throws URISyntaxException;

	ResponseEntity<?> getOrderById(Integer Id) throws URISyntaxException, JsonMappingException, JsonProcessingException;
	
	ResponseEntity<?> getOrderByIdGetForObject(Integer Id) throws URISyntaxException, JsonMappingException, JsonProcessingException;
	
	ResponseEntity<?> orderProductPostForObject(OrderRequestDto orderRequestDto) throws URISyntaxException;
	
	ResponseEntity<?> orderProductPostForEntity(OrderRequestDto orderRequestDto) throws URISyntaxException;
}
