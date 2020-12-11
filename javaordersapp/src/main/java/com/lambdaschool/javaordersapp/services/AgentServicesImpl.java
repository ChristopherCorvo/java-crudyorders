package com.lambdaschool.javaordersapp.services;

import com.lambdaschool.javaordersapp.models.Agent;
import com.lambdaschool.javaordersapp.repositories.AgentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentservices")
public class AgentServicesImpl implements AgentServices
{
    // we use autowire to wire in the agentservices file
    @Autowired
    // we create a variable of type agent repository
    private AgentRepository agentRepository; // what does the lowercase represent

    // This refers to the Get request defined in the controller and services
    @Override
    public Agent findAgentById(long id)
    {
        return agentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found."));

    }

    @Transactional
    @Override
    public Agent save(Agent agent)
    {
        return agentRepository.save(agent);
    }
}

