package gateway.com.example.product.demo.service;

import org.springframework.http.ResponseEntity;

import gateway.com.example.product.demo.model.Product;

public interface ProductService {
 
	ResponseEntity<?> saveProductDetails(Product product);
	
	ResponseEntity<?> fetchAllProductDetails(Integer id);
	
	ResponseEntity<?> fetchAllProductDetails();
	
	ResponseEntity<?> updateProductDetails(Integer id,String prodName,Integer  price); 
}
