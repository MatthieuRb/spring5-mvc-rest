package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.*;

import org.junit.Test;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;

public class CustomerMapperTest {

	public static final String FIRSTNAME = "Joe";
	public static final String LASTNAME = "Dupond";
	public static final long ID = 1L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {

        //given
        Customer customer = new Customer();
        customer.setLastName(LASTNAME);
        customer.setFirstName(FIRSTNAME);
        customer.setId(ID);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(Long.valueOf(ID), customerDTO.getId());
        assertEquals(LASTNAME, customerDTO.getLastName());
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
    }

}
