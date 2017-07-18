package de.cosmicit.pms.entities;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import de.cosmicit.pms.model.entities.Customer;
import de.cosmicit.pms.model.repository.CustomerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/integrationtest.properties")		
public class CustomerEntityTest {

	@Autowired
	CustomerRepository customerRepository;
	
	
	@Test
	public void testCreateCustomer() {					
	Customer customer = new Customer();
	customer.setUserName("testUserName");
	customer.setFirstName("test");
	customer.setLastName("userlastname");
	customer.setCity("Munich");
	customer.setCountry("Germany");
	assertNull(customer.getId());
	
	customer = customerRepository.save(customer);
	assertNotNull(customer.getId());
	assertTrue(customer.getId()>0);
	}

}
