package guru.springfamework.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {
	
	public Long id;
	public String firstName;
	public String lastName;
	public String customer_url = "/shop/customers/2"+id;

}
