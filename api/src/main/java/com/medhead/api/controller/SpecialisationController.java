package com.medhead.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Hopital;
import com.medhead.api.model.Specialite;
import com.medhead.api.model.Specialisation;
import com.medhead.api.service.SpecialisationService;

@RestController
public class SpecialisationController {

    @Autowired
    private SpecialisationService specialisationService;


	/**
     * Create - Ajout d'une nouvelle spécialisation
     * @param specialisation - spécialisation à créer
     * @return la nouvelle spécialisation créée
     */
    @PostMapping("/specialisation")
    public Specialisation createSpecialisation(@RequestBody Specialisation specialisation){
        return specialisationService.saveSpecialisation(specialisation);
    }


    /**
	 * Read - Récupération d'une spécialisaiton
	 * @param id - id de la spécialisation
	 * @return la spécialisation demandée
	 */
	@GetMapping("/specialisation/{id}")
	public Specialisation getSpecialisation(@PathVariable("id") final Long id) {
		Optional<Specialisation> s = specialisationService.getSpecialisation(id);
        if (s.isPresent()){
            return s.get();
        }
        return null;
	}


	/**
    * Read - Récupération de toutes les spécialisations
    * @return la liste des spécialisations
    */
    @GetMapping("/specialisations")
    public Iterable<Specialisation> getSpecialisations() {
        return specialisationService.getSpecialisations();
    }


	/**
     * Update - Modification d'une spécialisation
     * @param id - id de la spécialisation à modifier
     * @param specialisation - spécialisation à modifier
     * @return la spécialisation modifiée
     */
    @PutMapping("/specialisation/{id}")
    public Specialisation updateSpecialisation(@PathVariable("id") final Long id, @RequestBody Specialisation specialisation){
        // On recherche la spécialisation enregistrée qui est à modifier
        Optional<Specialisation> s = specialisationService.getSpecialisation(id);
		if(s.isPresent()) { // Si elle existe alors on la modifie
        	Specialisation currentSpecialisation = s.get();
			
			Hopital hopital = specialisation.getHopital();
			if(hopital != null) {
				currentSpecialisation.setHopital(hopital);
			}

			Specialite specialite = specialisation.getSpecialite();
			if(specialite != null) {
				currentSpecialisation.setSpecialite(specialite);
			}

			specialisationService.saveSpecialisation(currentSpecialisation);
			return currentSpecialisation;
		} else {
			return null;
		}
    }


	 /**
     * Récupération des spécialisations correspondant à une spécialité et à des hôpitaux ayant des lits disponibles
     * @param idSpecialite - id de la spécialité critère de la recherche
     * @return les spécialisations correspondant à la recherche
     */
	@GetMapping("/specialisation/recherche/{specialite}")
	public Iterable<Specialisation> getSpecialisationBySpecialite(@PathVariable("specialite") final Long specialite) {
		return specialisationService.getSpecialisationBySpecialite(specialite);
	}


	/**
	 * Delete - Suppression d'une spécialisation
	 * @param id - id de la spécialisation à supprimer
	 */
	@DeleteMapping("/specialisation/{id}")
	public void deleteSpecialisation(@PathVariable("id") final Long id) {
		specialisationService.deleteSpecialisation(id);
	}
}
