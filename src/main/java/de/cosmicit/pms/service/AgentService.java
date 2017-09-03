package de.cosmicit.pms.service;

import de.cosmicit.pms.model.entities.Subscription;
import de.cosmicit.pms.model.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("agentService")
public class AgentService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

//    public Set<Subscription> getSubscriptionsByAgentId(Agent agent) {
//        return subscriptionRepository.getSubscriptionsByAgentId(agent);
//    }

    public Set<Subscription> getSubscriptionsByAgentIdNumeric(Long id) {
        return subscriptionRepository.getSubscriptionsByAgentIdNumeric(id);
    }
}
