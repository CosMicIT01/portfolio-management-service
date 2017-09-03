package de.cosmicit.pms.controller;

import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import de.cosmicit.pms.model.entities.Agent;
import de.cosmicit.pms.model.entities.Subscription;
import de.cosmicit.pms.model.repository.AgentRepository;
import de.cosmicit.pms.model.repository.AgentRepository;
import de.cosmicit.pms.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/agent")
public class AgentController extends AbstractRestController<Agent> {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    AgentService agentService;

    @Override
    public Class<Agent> getEntityClass() {
        return Agent.class;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Agent get(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return super.get(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Agent> getList() {
        List<Agent> agents = super.getList();
        return agents;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Agent create(@RequestBody Agent entity) throws InvalidParameterException {
        return super.create(entity);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Agent replace(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        return super.replace(id, jsonData);
    }

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public Agent update(@PathVariable("id") Long id, @RequestBody String jsonData)
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
    @RequestMapping(path = "/{id}/subscriptions", method = RequestMethod.GET)
    public Set<Subscription> getSubscriptionsByAgentId(@PathVariable("id") Long id) throws ResourceNotFoundException {
//        Agent agent = findEntityOrThrowException(id);
//        return agentService.getSubscriptionsByAgentId(agent);
        return agentService.getSubscriptionsByAgentIdNumeric(id);

    }
}
