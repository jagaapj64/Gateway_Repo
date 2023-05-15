package gateway.com.example.order.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import gateway.com.example.order.demo.model.Orders;
import gateway.com.example.order.demo.service.OrderService;

@RestController
@RequestMapping("/api/order/")
public class OrderServiceController {

	@Autowired
	OrderService orderService;
	
	
	@PostMapping("save/order/details")
	public ResponseEntity<?> saveOrder(@RequestBody Orders order) {
		return  ResponseEntity.ok(orderService.saveOrderDetails(order));
		
	}
	
	@GetMapping("get/order/details/{id}")
	public ResponseEntity<?> getOrder(@PathVariable Integer id) {
		return  ResponseEntity.ok(orderService.getOrderDetails(id));
		
	}
	
	@GetMapping("get/all/order/details/")
	public ResponseEntity<?> getAllOrderDetailsList() {
		return  ResponseEntity.ok(orderService.getAllOrderDetailsList());
		
	}
	
	@PutMapping("update/ordered/details/{Id}/{prodName}/{quantity}")
	public ResponseEntity<?> updateOrder(@PathVariable(value="Id") Integer Id,
			                             @PathVariable(value="prodName") String prodName,
			                             @PathVariable(value="quantity") Integer quantity) {
		return  ResponseEntity.ok(orderService.updateOrderDetails(Id,prodName,quantity));
		
	}
	
	@DeleteMapping("cancel/ordered/details/{Id}")
	public ResponseEntity<?> cancelOrder(@PathVariable Integer Id){
		return orderService.cancelOrderDetails(Id);
		
		
		///////
	}
}
