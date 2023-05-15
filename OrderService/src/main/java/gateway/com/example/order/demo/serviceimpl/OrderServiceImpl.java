package gateway.com.example.order.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gateway.com.example.order.demo.model.Orders;
import gateway.com.example.order.demo.model.ProductDto;
import gateway.com.example.order.demo.model.ResponseDto;
import gateway.com.example.order.demo.repo.OrdersRepository;
import gateway.com.example.order.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<?> saveOrderDetails(Orders order) {
		Orders orders = Orders.builder().orderId(order.getOrderId()).productName(order.getProductName())
				.quanitity(order.getQuanitity()).build();
		Orders result = ordersRepository.save(orders);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<?> getOrderDetails(Integer id) {
		Optional<Orders> ids = ordersRepository.findById(id);

//		ResponseEntity<ProductDto> responseEntity = restTemplate
//				.getForEntity("http://localhost:8082/api/product/get/details/" + id, ProductDto.class);
//		ProductDto body = responseEntity.getBody();
//
//		ResponseDto responseDto = ResponseDto.builder().orders(ids.get()).productDto(body).build();
		return ResponseEntity.ok(ids);
	}

	@Override
	public ResponseEntity<?> getAllOrderDetailsList() {

		return ResponseEntity.ok(ordersRepository.findAll());
	}

	@Override
	public ResponseEntity<?> updateOrderDetails(Integer id, String prodName, Integer quantity) {
		Optional<Orders> optional = ordersRepository.findById(id);
		if (prodName != null)
			optional.get().setProductName(prodName);
		if (quantity != null)
			optional.get().setQuanitity(quantity);
		return ResponseEntity.ok(ordersRepository.save(optional.get()));
	}

	@Override
	public ResponseEntity<?> cancelOrderDetails(Integer id) {
		ordersRepository.deleteById(id);
		return ResponseEntity.ok("Canceled Order Details ");
	}

}
