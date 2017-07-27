package de.cosmicit.pms.controller;

import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import de.cosmicit.pms.model.entities.ServiceRequest;
import de.cosmicit.pms.model.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/servicerequest")
public class ServiceRequestController extends AbstractRestController<ServiceRequest> {

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Override
    public Class<ServiceRequest> getEntityClass() {
        return ServiceRequest.class;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ServiceRequest get(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.get(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ServiceRequest> getList() {
        List<ServiceRequest> serviceRequests = super.getList();
        return serviceRequests;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ServiceRequest create(@RequestBody ServiceRequest entity) throws InvalidParameterException {
        return super.create(entity);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ServiceRequest replace(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.replace(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ServiceRequest update(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.update(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        super.delete(id);
    }
}
