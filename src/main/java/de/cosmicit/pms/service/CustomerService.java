package de.cosmicit.pms.service;

import de.cosmicit.pms.model.entities.Customer;
import de.cosmicit.pms.model.entities.Subscription;
import de.cosmicit.pms.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("customerService")
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Set<Subscription> getSubscriptionsForCustomer(Customer customer) {
        return customerRepository.getSubscriptionsForCustomer(customer);
    }

}
