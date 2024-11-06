package com.medhead.webapp.model;

import lombok.Data;

@Data
public class Hopital {

    private Integer id;

    private String nom;

    private String adresse;

    private String ville;

    private String cp;

    private String telephone;

    private Integer nbLit;

}
