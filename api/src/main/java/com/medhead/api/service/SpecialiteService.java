package com.medhead.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Specialite;
import com.medhead.api.repository.SpecialiteRepository;

import lombok.Data;

@Data
@Service
public class SpecialiteService {
    
    @Autowired
    private SpecialiteRepository specialiteRepository;

    public List<Specialite> getGroupes() {
        return specialiteRepository.findByParent("");
    }

    public List<Specialite> getSpecialites(String parent) {
        return specialiteRepository.findByParent(parent);
    }
}
