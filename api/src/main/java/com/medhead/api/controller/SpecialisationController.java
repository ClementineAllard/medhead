package com.medhead.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Specialisation;
import com.medhead.api.service.SpecialisationService;

@RestController
public class SpecialisationController {

    @Autowired
    private SpecialisationService specialisationService;

    /**
	 * Read - Récupérer une spécialisaiton
	 * @param id Id de la spécialisation
	 * @return - Spécialisation demandée
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
	 * Read - Récupérer des spécialisations à partir d'une spécialité
	 * @param id Id de la spécialité
	 * @return - Liste des spécialisations
	 */
	@GetMapping("/hopitaux/{id}")
	public Iterable<Specialisation> getSpecialisationBySpecialite(@PathVariable("id") final Long id) {
		return specialisationService.getSpecialisationBySpecialite(id);
	}
}
