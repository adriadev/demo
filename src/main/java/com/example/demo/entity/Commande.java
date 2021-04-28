package com.example.demo.entity;

import com.example.demo.shared.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commande {
    @Id
    @GeneratedValue
    @Column(name = "ID_")
    private Long id;

    private String label;

    private String validateur;

    private String sendNotif;

    public String getSendNotif() {
        return sendNotif;
    }

    public void setSendNotif(String sendNotif) {
        this.sendNotif = sendNotif;
    }

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

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
    @Override
    public String toString(){
        return this.label;
    }
}
