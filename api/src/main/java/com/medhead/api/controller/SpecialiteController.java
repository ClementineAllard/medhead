package com.medhead.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Specialite;
import com.medhead.api.service.SpecialiteService;

@RestController
public class SpecialiteController {

    
    @Autowired
    private SpecialiteService specialiteService;

    /**
    * Read - Récupérer tous les groupes de spécialités
    * @return - Liste des groupes
    */
    @GetMapping("/groupes")
    public Iterable<Specialite> getGroupes() {
        return specialiteService.getGroupes();
    }

    /**
	 * Read - Récupérer les specialités d'un groupe
	 * @param parent code du groupe parent
	 * @return - Liste des specialités du groupe
	 */
	@GetMapping("/specialites/{parent}")
	public Iterable<Specialite> getSpecialites(@PathVariable("parent") Long parent) {
		return specialiteService.getSpecialites(parent);
	}

}
