package com.example.demo.shared;

import com.example.demo.entity.Commande;

public class CommandeDTO {
    private Long id;
    private String label;
    private StatusEnum status;
    private String validateur;
    private String sendNotif;
    private String taskID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getValidateur() {
        return validateur;
    }

    public void setValidateur(String validateur) {
        this.validateur = validateur;
    }

    public String getSendNotif() {
        return sendNotif;
    }

    public void setSendNotif(String sendNotif) {
        this.sendNotif = sendNotif;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public Commande dtoToEntity(){
        Commande c = new Commande();
        c.setId(this.getId());
        c.setLabel(this.getLabel());
        c.setStatus(this.status);
        c.setValidateur(this.getValidateur());
        return c;
    }

    public static CommandeDTO entityToDTO(Commande cmd){
        CommandeDTO c = new CommandeDTO();
        c.setId(cmd.getId());
        c.setLabel(cmd.getLabel());
        c.setStatus(cmd.getStatus());
        c.setValidateur(cmd.getValidateur());
        return c;
    }
}
