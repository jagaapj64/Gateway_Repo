package gateway.com.example.product.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gateway.com.example.product.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
