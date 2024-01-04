package io.audit.audit_sec.model;

import jakarta.validation.constraints.Size;


public class StandardsDTO {

    private Long id;

    @Size(max = 255)
    private String nom;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

}
