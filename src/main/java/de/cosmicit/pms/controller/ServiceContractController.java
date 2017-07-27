package de.cosmicit.pms.controller;

import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import de.cosmicit.pms.model.entities.ServiceContract;
import de.cosmicit.pms.model.repository.ServiceContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/servicecontract")
public class ServiceContractController extends AbstractRestController<ServiceContract> {

    @Autowired
    ServiceContractRepository serviceContractRepository;

    @Override
    public Class<ServiceContract> getEntityClass() {
        return ServiceContract.class;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ServiceContract get(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.get(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ServiceContract> getList() {
        List<ServiceContract> serviceContracts = super.getList();
        return serviceContracts;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ServiceContract create(@RequestBody ServiceContract entity) throws InvalidParameterException {
        return super.create(entity);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ServiceContract replace(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.replace(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ServiceContract update(@PathVariable("id") Long id, @RequestBody String jsonData)
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
