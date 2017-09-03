package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

//    @Query("SELECT s FROM Subscription s INNER JOIN FETCH u.matchteams mt WHERE mt.tournament = :tournament")
//    Set<Subscription> getSubscriptionsByAgentId(@Param("agent") Agent agent);



    @Query(value = "select sub.subscription_id,sub.subscription_name ,sub.expiry_date, sub.creation_date, sub.renewal_date, " +
            "sub.subscription_link_service_id, sub.subscription_link_service_id, sub.subscription_link_customer_id," +
            " sub.subscription_link_subscription_type_id " +
            "FROM tbl_subscription sub " +
            "INNER JOIN  tbl_service ser " +
            "ON sub.subscription_link_service_id = ser.service_id " +
            "INNER JOIN tbl_service_request sr " +
            "ON sr.service_request_link_service_id = ser.service_id " +
            "INNER JOIN tbl_agent a " +
            "ON sr.service_request_link_agent_id = a.agent_id " +
            "WHERE  a.agent_id = ?1", nativeQuery = true)
    Set<Subscription> getSubscriptionsByAgentIdNumeric(Long agentId);
}
