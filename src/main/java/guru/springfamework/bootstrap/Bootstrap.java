package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
		this.categoryRespository = categoryRespository;
		this.customerRepository = customerRepository;
	}
    
	@Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);


        System.out.println("Categories Loaded = " + categoryRespository.count() );
        
        Customer paul = new Customer();
        paul.setFirstName("Paul");
        paul.setLastName("Dupond");
        
        Customer jack = new Customer();
        jack.setFirstName("Jack");
        jack.setLastName("Ford");
        
        Customer toto = new Customer();
        toto.setFirstName("Toto");
        toto.setLastName("Rambaud");
        
        Customer henry = new Customer();
        henry.setFirstName("Henry");
        henry.setLastName("Bremaud");
        
        customerRepository.save(paul);
        customerRepository.save(jack);
        customerRepository.save(toto);
        customerRepository.save(henry);
        
        System.out.println("Customers Loaded = " + customerRepository.count() );

    }
}
