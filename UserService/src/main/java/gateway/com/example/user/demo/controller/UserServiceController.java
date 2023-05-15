package gateway.com.example.user.demo.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import gateway.com.example.user.demo.model.User;
import gateway.com.example.user.demo.request.OrderRequestDto;
import gateway.com.example.user.demo.request.ProductRequestDto;
import gateway.com.example.user.demo.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserServiceController {

	@Autowired
	UserService userService;
	
	@PostMapping("save/details")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return userService.saveUserDetails(user);
		
	}
	
	@PostMapping("save/product/details")
	public ResponseEntity<?> saveProduct(@RequestBody ProductRequestDto productRequestDto) throws URISyntaxException{
		return userService.saveProduct(productRequestDto);
		
	}
	
	@GetMapping("show/product/details")
	public ResponseEntity<?> showProductDetailsList() throws URISyntaxException{
		return userService.showProductList();
		
	}
	
	@PostMapping("save/order/details/")
	public ResponseEntity<?> orderProducts(@RequestBody OrderRequestDto orderRequestDto) throws URISyntaxException{
		return userService.orderProduct(orderRequestDto);
		
	}
	
	@GetMapping("show/ordered/details/")
	public ResponseEntity<?> showOrderedDetailsList() throws URISyntaxException{
		return userService.showOrderedList();
		
	}
	
	@PutMapping("update/product/details/")
	public ResponseEntity<?> updateProductDetails(@RequestParam(value="pId",required = true) Integer pId,
			                                      @RequestParam(value="prodName",required = false) String prodName,
			                                       @RequestParam(value="price",required = false)Integer price) throws URISyntaxException{
		return userService.updateProduct(pId,prodName,price);
		
	}
	@PutMapping("update/order/details/{Id}/{prodName}/{quantity}")
	public ResponseEntity<?> updateOrder(@PathVariable(value="Id") Integer Id,
			                             @PathVariable(value="prodName") String prodName,
			                             @PathVariable(value="quantity") Integer quantity) throws URISyntaxException {
		return  ResponseEntity.ok(userService.updateOrder(Id,prodName,quantity));
		
	}

	@DeleteMapping("delete/ordered/details/{Id}")
	public ResponseEntity<?> cancelOrder(@PathVariable Integer Id) throws URISyntaxException{
		return userService.cancelOrder(Id);
	}
	
	@GetMapping("get/order/byId/getForEntity/{Id}")
	public ResponseEntity<?> getOrderByIdGetForEntity(@PathVariable Integer Id) throws URISyntaxException, JsonMappingException, JsonProcessingException{
		return userService.getOrderById(Id);
	}
	
	@GetMapping("get/order/byId/getForObject/{Id}")
	public ResponseEntity<?> getOrderByIdGetForObject(@PathVariable Integer Id) throws URISyntaxException, JsonMappingException, JsonProcessingException{
		return userService.getOrderById(Id);
	}
	
	
	@PostMapping("save/order/postForObject/")
	public ResponseEntity<?> orderProductsPostForObject(@RequestBody OrderRequestDto orderRequestDto) throws URISyntaxException{
		return userService.orderProductPostForObject(orderRequestDto);
		
	}
	
	@PostMapping("save/order/postForEntity/")
	public ResponseEntity<?> orderProductsPostForEntity(@RequestBody OrderRequestDto orderRequestDto) throws URISyntaxException{
		return userService.orderProductPostForEntity(orderRequestDto);
		
	}
}
