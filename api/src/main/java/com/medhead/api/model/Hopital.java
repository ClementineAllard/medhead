package com.medhead.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "hopitaux")
public class Hopital {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    private String ville;

    @Column(name="code_postal")
    private String cp;

    @Column(name="code_pays")
    private String pays;

    private String telephone;
    
    @Column(name="nb_lit")
    private Integer nbLit;

}
