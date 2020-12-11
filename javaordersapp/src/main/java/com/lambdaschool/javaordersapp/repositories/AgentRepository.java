package com.lambdaschool.javaordersapp.repositories;

import com.lambdaschool.javaordersapp.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long>
{

}
