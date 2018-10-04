package guru.springfamework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

import static org.mockito.ArgumentMatchers.*;

public class CustomerServiceTest {
	
	CustomerService customerService;
	
	@Mock
	CustomerRepository customerRepository;
	
	private final static Long ID = 1L;

	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }
	
	@Test
	public void getAllCustomersTest() {
		
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
		
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<CustomerDTO> customersDTO = customerService.getAllCustomers();
		
		assertEquals(3, customersDTO.size());
	}

	@Test
	public void getCustomerByIdTest() {
		
		Customer customer = new Customer();
		customer.setFirstName("Paul");
		customer.setLastName("Dupond");
		customer.setId(ID);
		
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
		
		CustomerDTO customerDTO = customerService.getCustomerById(ID);
		
		assertEquals("Paul", customerDTO.getFirstName());
		assertEquals("Dupond", customerDTO.getLastName());
		assertEquals(ID, customerDTO.getId());

	}

}
