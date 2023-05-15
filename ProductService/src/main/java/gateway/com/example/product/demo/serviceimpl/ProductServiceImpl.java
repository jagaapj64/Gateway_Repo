package gateway.com.example.product.demo.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gateway.com.example.product.demo.model.Product;
import gateway.com.example.product.demo.repo.ProductRepository;
import gateway.com.example.product.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ResponseEntity<?> saveProductDetails(Product product) {
		Product products=Product.builder().pId(product.getPId()).productName(product.getProductName()).price(product.getPrice()).build();
		return ResponseEntity.ok(productRepository.save(products));
	}

	@Override
	public ResponseEntity<?> fetchAllProductDetails(Integer id) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(productRepository.findById(id));
	}

	@Override
	public ResponseEntity<?> fetchAllProductDetails() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(productRepository.findAll());
	}

	@Override
	public ResponseEntity<?> updateProductDetails(Integer id, String prodName, Integer price) {
		Optional<Product> optional=productRepository.findById(id);
		if(prodName!=null)
		optional.get().setProductName(prodName);
		if(price!=null)
		optional.get().setPrice(price);
		return ResponseEntity.ok(productRepository.save(optional.get()));
	}
}
