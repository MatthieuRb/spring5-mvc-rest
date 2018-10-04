package guru.springfamework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		
		return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		Customer customer = optional.isPresent() ? customer = optional.get() : null;
		return customerMapper.customerToCustomerDTO(customer);
	}

}
