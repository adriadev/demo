package com.example.demo.shared;

public enum StatusEnum {
    APPROVED("Acceptée"),
    REJECTED("Refusée"),
    PENDING("En attente");

    private String label;

    StatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
