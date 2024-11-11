package com.medhead.webapp.model;

import lombok.Data;

@Data
public class Specialisation {
    
    private Integer id;

    private Hopital hopital;

    private Specialite specialite;
    
}
