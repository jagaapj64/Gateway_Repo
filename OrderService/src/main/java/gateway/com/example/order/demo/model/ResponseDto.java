package gateway.com.example.order.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

	private Orders orders;
	private ProductDto productDto;
}
