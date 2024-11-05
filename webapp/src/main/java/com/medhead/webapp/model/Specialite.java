package com.medhead.webapp.model;

import lombok.Data;

@Data
public class Specialite {

    private Integer id;

    private String libelle;

    private Integer parent;
}
