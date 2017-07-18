package de.cosmicit.pms.controller;

import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import de.cosmicit.pms.model.entities.Customer;
import de.cosmicit.pms.model.entities.Subscription;
import de.cosmicit.pms.model.repository.CustomerRepository;
import de.cosmicit.pms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/user")
public class UserController extends AbstractRestController<Customer> {

    public static final String SUBSCRIPTION_URL = "/user/subscription";

    @Autowired
    CustomerRepository userRepository;

    @Autowired
    CustomerService customerService;

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Customer get(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.get(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Customer> getList() {
        List<Customer> users = super.getList();
        return users;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer entity) throws InvalidParameterException {
        return super.create(entity);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Customer replace(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.replace(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Customer update(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.update(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        super.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}/subscription", method = RequestMethod.GET)
    public Set<Subscription> getUserSubscriptions(@PathVariable("id") Long customerId) {
        Customer customer = userRepository.findOne(customerId);
        Set<Subscription> subscriptions = customerService.getSubscriptionsForCustomer(customer);
        return subscriptions;
    }
}
