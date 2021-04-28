package com.example.demo.service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.FieldAccessJPAEntity;
import com.example.demo.entity.Person;
import com.example.demo.repo.CommandeRepo;
import com.example.demo.repo.FieldAccessJPAEntityRepo;
import com.example.demo.repo.PersonRepository;
import com.example.demo.shared.CommandeDTO;
import com.example.demo.shared.StatusEnum;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.spring.integration.Flowable;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    CommandeRepo commandeRepo;

    @Autowired
    private FieldAccessJPAEntityRepo fieldAccessJPAEntityRepo;

    public void startProcess(CommandeDTO commandeDTO) {
        Map<String, Object> variables = new HashMap<>();
        Commande commande = commandeRepo.saveAndFlush(commandeDTO.dtoToEntity());
        variables.put("commande", commande);
        runtimeService.startProcessInstanceByKey("commandeProcess", variables);
    }

    public List<CommandeDTO> getTasks(String assignee) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        return tasks.stream()
                .map(task -> {
                    Map<String, Object> variables = taskService.getVariables(task.getId());
                    if (variables.get("commande") instanceof Commande) {
                        CommandeDTO dto =  CommandeDTO.entityToDTO((Commande) variables.get("commande"));
                        dto.setTaskID(task.getId());
                        return dto;
                    } else {
                        return new CommandeDTO();
                    }
                })
                .collect(Collectors.toList());
    }

    public void submitReview(CommandeDTO dto) {
        Map<String, Object> variables = new HashMap<String, Object>();
        Commande cmd = commandeRepo.getOne(dto.getId());
        cmd.setStatus(StatusEnum.APPROVED);
        commandeRepo.saveAndFlush(cmd);
        taskService.complete(dto.getTaskID());
    }

    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }
}
