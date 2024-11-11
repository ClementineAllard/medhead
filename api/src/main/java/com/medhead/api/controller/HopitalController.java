package com.medhead.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Hopital;
import com.medhead.api.service.HopitalService;

@RestController
public class HopitalController {

    @Autowired
    private HopitalService hopitalService;


	/**
     * Create - Ajout d'un nouvel hôpital
     * @param hopital - hôpital à créer
     * @return le nouvel hôpital créé
     */
    @PostMapping("/hopital")
    public Hopital createHopital(@RequestBody Hopital hopital){
        return hopitalService.saveHopital(hopital);
    }


    /**
	 * Read - Récupération d'un hopital
	 * @param id - id de l'hopital
	 * @return l'hôpital demandé
	 */
	@GetMapping("/hopital/{id}")
	public Hopital getHopital(@PathVariable("id") final Long id) {
        Optional<Hopital> hopital = hopitalService.getHopital(id);
		if(hopital.isPresent()) {
			return hopital.get();
		} else {
			return null;
		}
	}


	/**
    * Read - Récupération de tous les hôpitaux
    * @return la liste des hôpitaux
    */
    @GetMapping("/hopitaux")
    public Iterable<Hopital> getHopitaux() {
        return hopitalService.getHopitaux();
    }


	/**
     * Update - Modification d'un hopital
     * @param id - id de l'hôpital à modifier
     * @param hopital - hôpital à modifier
     * @return l'hôpital modifié
     */
    @PutMapping("/hopital/{id}")
    public Hopital updateHopital(@PathVariable("id") final Long id, @RequestBody Hopital hopital){
        // On recherche l'hôpital enregistré qui est à modifier
        Optional<Hopital> h = hopitalService.getHopital(id);
		if(h.isPresent()) { // S'il existe alors on le modifie
        	Hopital currentHopital = h.get();
			
			String nom = hopital.getNom();
			if(nom != null) {
				currentHopital.setNom(nom);
			}

			String adresse = hopital.getAdresse();
			if(adresse != null) {
				currentHopital.setAdresse(adresse);
			}

			String ville = hopital.getVille();
			if(ville != null) {
				currentHopital.setVille(ville);
			}

			String cp = hopital.getCp();
			if(cp != null) {
				currentHopital.setCp(cp);
			}

			String pays = hopital.getPays();
			if(pays != null) {
				currentHopital.setPays(pays);
			}

			String telephone = hopital.getTelephone();
			if(telephone != null) {
				currentHopital.setTelephone(telephone);
			}

            Integer nbLit = hopital.getNbLit();
			if(nbLit != null) {
				currentHopital.setNbLit(nbLit);
			}

			hopitalService.saveHopital(currentHopital);
			return currentHopital;
		} else {
			return null;
		}
    }


	/**
	 * Update - Réservation d'un lit d'hôpital
	 * @param id - id de l'hôpital
	 * @return l'hôpital modifié
	 */
	@PutMapping("/hopital/reservation/{id}")
	public Hopital updateReservationLit(@PathVariable("id") final Long id) {
        Optional<Hopital> hopital = hopitalService.getHopital(id);
		if(hopital.isPresent()) {
			Hopital hopitalAReserver = hopital.get();
			Integer nbLit = hopitalAReserver.getNbLit();
			if(nbLit > 0){
				hopitalAReserver.setNbLit(nbLit-1);
			}

			hopitalService.saveHopital(hopitalAReserver);
			return hopitalAReserver;
		}else{
			return null;
		}
	}


	/**
	 * Delete - Suppression d'un hôpital
	 * @param id - id de l'hôpital à supprimer
	 */
	@DeleteMapping("/hopital/{id}")
	public void deleteHopital(@PathVariable("id") final Long id) {
		hopitalService.deleteHopital(id);
	}

}
