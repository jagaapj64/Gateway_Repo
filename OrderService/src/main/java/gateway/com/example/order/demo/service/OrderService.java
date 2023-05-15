package gateway.com.example.order.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import gateway.com.example.order.demo.model.Orders;

public interface OrderService {

	ResponseEntity<?> saveOrderDetails(Orders order);
	
	ResponseEntity<?> getOrderDetails(Integer id);
	
	ResponseEntity<?> getAllOrderDetailsList();
	
	ResponseEntity<?> updateOrderDetails(Integer id,String prodName,Integer quantity);
	
	ResponseEntity<?> cancelOrderDetails(Integer id);
}
