package com.lambdaschool.javaordersapp.controllers;

import com.lambdaschool.javaordersapp.models.Agent;
import com.lambdaschool.javaordersapp.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController
{

    @Autowired
    private AgentServices agentServices; // what is this

    // ---------- GET requests -----------
    // http://localhost:2019/agents/agent/{agentcode}
    @GetMapping(value = "/agent/{agentcode}",
        produces = "application/json")
    public ResponseEntity<?> getAgentById(
        @PathVariable
            long agentcode)
    {
        Agent a = agentServices.findAgentById(agentcode);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }


}