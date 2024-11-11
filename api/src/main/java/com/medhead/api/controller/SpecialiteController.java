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

import com.medhead.api.model.Specialite;
import com.medhead.api.service.SpecialiteService;

@RestController
public class SpecialiteController {

    
    @Autowired
    private SpecialiteService specialiteService;


    /**
     * Create - Ajout d'une nouvelle spécialité
     * @param specialite - spécialité à créer
     * @return la nouvelle spécialité créée
     */
    @PostMapping("/specialite")
    public Specialite createSpecialite(@RequestBody Specialite specialite){
        return specialiteService.saveSpecialite(specialite);
    }

    
     /**
	 * Read - Récupération d'une spécialité
	 * @param id - id de la spécialité
	 * @return la spécialité demandée
	 */
	@GetMapping("/specialite/{id}")
	public Specialite getSpecialite(@PathVariable("id") final Long id) {
		Optional<Specialite> specialite = specialiteService.getSpecialite(id);
		if(specialite.isPresent()) {
			return specialite.get();
		} else {
			return null;
		}
	}
    

    /**
    * Read - Récupération de toutes les spécialités
    * @return la liste des spécialités
    */
    @GetMapping("/specialites")
    public Iterable<Specialite> getSpecialites() {
        return specialiteService.getSpecialites();
    }

    
    /**
     * Update - Modification d'une spécialité
     * @param id - id de la spécialité à modifier
     * @param specialite - spécialité à modifier
     * @return la spécialité modifiée
     */
    @PutMapping("/specialite/{id}")
    public Specialite updateSpecialite(@PathVariable("id") final Long id, @RequestBody Specialite specialite){
        // On recherche la spécialité enregistrée qui est à modifier
        Optional<Specialite> s = specialiteService.getSpecialite(id);
		if(s.isPresent()) { // Si elle existe alors on la modifie
        Specialite currentSpecialite = s.get();
			
			String libelle = specialite.getLibelle();
			if(libelle != null) {
				currentSpecialite.setLibelle(libelle);
			}

            Long parent = specialite.getParent();
			if(parent != null) {
				currentSpecialite.setParent(parent);
			}

			specialiteService.saveSpecialite(currentSpecialite);
			return currentSpecialite;
		} else {
			return null;
		}
    }


	/**
	 * Delete - Suppression d'une spécialité
	 * @param id - id de la spécialité à supprimer
	 */
	@DeleteMapping("/specialite/{id}")
	public void deleteSpecialite(@PathVariable("id") final Long id) {
		specialiteService.deleteSpecialite(id);
	}


    /**
    * Read - Récupérer tous les groupes de spécialités
    * @return - Liste des groupes
    */
    @GetMapping("/specialite/groupes")
    public Iterable<Specialite> getGroupes() {
        return specialiteService.getGroupes();
    }

    /**
	 * Read - Récupérer les specialités d'un groupe
	 * @param parent id du groupe parent
	 * @return - Liste des specialités du groupe
	 */
	@GetMapping("/specialites/{parent}")
	public Iterable<Specialite> getSpecialitesByParent(@PathVariable("parent") Long parent) {
		return specialiteService.getSpecialitesByParent(parent);
	}

}
