package com.medhead.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Specialisation;
import com.medhead.api.repository.SpecialisationRepository;

import lombok.Data;

@Data
@Service
public class SpecialisationService {

    @Autowired
    private SpecialisationRepository specialisationRepository;

    public Optional<Specialisation> getSpecialisation(Long id) {
        return specialisationRepository.findById(id);
    }

    public List<Specialisation> getSpecialisationBySpecialite(Long id) {
        return specialisationRepository.findSpecialisationBySpecialite(id);
    }
}
