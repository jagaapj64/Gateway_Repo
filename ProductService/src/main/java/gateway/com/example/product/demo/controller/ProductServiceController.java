package gateway.com.example.product.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gateway.com.example.product.demo.model.Product;
import gateway.com.example.product.demo.service.ProductService;

@RestController
@RequestMapping("/api/product/")

public class ProductServiceController {

	@Autowired
	ProductService productService;

	@PostMapping("save/product/details")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		return productService.saveProductDetails(product);

	}

	@GetMapping("get/details/{id}")
	public ResponseEntity<?> getProducts(@PathVariable Integer id) {
		return productService.fetchAllProductDetails(id);

	}

	@GetMapping("get/all/details/")
	public ResponseEntity<?> getAllProductsList() {
		return productService.fetchAllProductDetails();

	}

	@PutMapping("update/product/details/")
	public ResponseEntity<?> updateProduct(@RequestParam(value="pId") Integer pId, @RequestParam(value="prodName") String prodName,
			@RequestParam(value="price") Integer price) {
		return productService.updateProductDetails(pId, prodName, price);

	}
}
