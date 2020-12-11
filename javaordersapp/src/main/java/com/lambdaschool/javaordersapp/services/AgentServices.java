package com.lambdaschool.javaordersapp.services;


import com.lambdaschool.javaordersapp.models.Agent;

public interface AgentServices
{
    // --------- Get request methods -------
    // Returns the agent with the given primary key
    Agent findAgentById(long id);
    Agent save(Agent agent);
}
