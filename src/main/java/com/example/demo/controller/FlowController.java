package com.example.demo.controller;

import com.example.demo.service.MyService;
import com.example.demo.shared.CommandeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin()
public class FlowController {
    @Autowired
    private MyService myService;

    @PostMapping(value = "/process")
    public ResponseEntity startProcessInstance(@RequestBody CommandeDTO commandeDTO) {
        try {
            myService.startProcess(commandeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommandeDTO> getTasks(@RequestParam String assignee) {
        List<CommandeDTO> dtos = myService.getTasks(assignee);
        return dtos;
    }

    @PostMapping("/go")
    public void review(@RequestBody CommandeDTO dto) {
        myService.submitReview(dto);
    }
}
