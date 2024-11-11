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


    /**
     * Récupération d'une spécialisation à partir de son id
     * @param id - id de la spécialisation
     * @return la spécialisation demandée
     */
    public Optional<Specialisation> getSpecialisation(Long id) {
        return specialisationRepository.findById(id);
    }


    /**
     * Récupération de toutes les spécialisations
     * @return liste des spécialisations
     */
    public Iterable<Specialisation> getSpecialisations() {
        return specialisationRepository.findAll();
    }


    /**
     * Suppression d'une spécialisation
     * @param id - id de la spécialisaiton à supprimer
     */
	public void deleteSpecialisation(final Long id) {
		specialisationRepository.deleteById(id);
	}


    /**
     * Enregistrement d'une nouvelle spécialisation
     * @param specialisation - spécialisation à enregistrer
     * @return la spécialisation qui a été créée
     */
    public Specialisation saveSpecialisation(Specialisation specialisation) {
		Specialisation savedSpecialisation = specialisationRepository.save(specialisation);
		return savedSpecialisation;
	}


    /**
     * Récupération des spécialisations correspondant à une spécialité et à des hôpitaux ayant des lits disponibles
     * @param idSpecialite - id de la spécialité critère de la recherche
     * @return les spécialisations correspondant à la recherche
     */
    public List<Specialisation> getSpecialisationBySpecialite(Long idSpecialite) {
        return specialisationRepository.findSpecialisationBySpecialite(idSpecialite);
    }
}
