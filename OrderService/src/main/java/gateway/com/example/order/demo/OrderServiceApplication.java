package gateway.com.example.order.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySources(@PropertySource(value="classpath:env.properties"))
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
