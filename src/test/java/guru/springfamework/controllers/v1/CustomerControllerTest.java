package guru.springfamework.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;

import static org.mockito.ArgumentMatchers.anyLong;

public class CustomerControllerTest {
	
	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController customerController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Paul");
		customer.setLastName("Dupond");
		customer.setId(1L);
		
		CustomerDTO customer2 = new CustomerDTO();
		customer.setFirstName("Jack");
		customer.setLastName("Tortue");
		customer.setId(2L);
		
		List<CustomerDTO> customers = Arrays.asList(customer,customer2);
		
		when(customerService.getAllCustomers()).thenReturn(customers);
		
		mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Paul");
		customer.setLastName("Dupond");
		customer.setId(1L);
		
		when(customerService.getCustomerById(anyLong())).thenReturn(customer);
		
		mockMvc.perform(get("/api/v1/customers/"+1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Paul")))
                .andExpect(jsonPath("$.lastName", equalTo("Dupond")));
	}

}
