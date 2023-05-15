package gateway.com.example.user.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(generator  = "user-sequence-generator")
	@GenericGenerator(name="user-sequence-generator",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	                  parameters = {@Parameter(name="sequence_name",value = "user_product_sequence"),
	                               @Parameter(name="initial_value",value = "100"),
	                               @Parameter(name="increment_size",value = "1")})
	private int userId;
	private String firstName;
	private String email;

}
