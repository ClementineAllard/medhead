package com.medhead.webapp.model;

import lombok.Data;

@Data
public class Utilisateur {

    private Integer id;

    private String email;

    private String prenom;

    private String nom;

    private String mdp;

}
