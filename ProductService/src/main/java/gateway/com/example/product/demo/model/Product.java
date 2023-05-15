package gateway.com.example.product.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	@Id
	private int pId;
	private String productName;
	private int price;
	
}
