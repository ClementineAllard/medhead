package com.medhead.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Hopital;
import com.medhead.api.service.HopitalService;

@RestController
public class HopitalController {

    @Autowired
    private HopitalService hopitalService;

    /**
	 * Read - Récupérer l'hopital à partir de son id
	 * @param id Id de l'hopital
	 * @return - Hopital
	 */
	@GetMapping("/hopital/{id}")
	public Hopital getHopitaux(@PathVariable("id") final Long id) {
        Optional<Hopital> hopital = hopitalService.getHopital(id);
		if(hopital.isPresent()) {
			return hopital.get();
		} else {
			return null;
		}
	}

	/**
	 * Update - Réservation d'un lit d'hôpital
	 * @param id - Id de l'hôpital
	 */
	@PutMapping("/reservation/{id}")
	public Hopital putReservationLit(@PathVariable("id") final Long id) {
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

}
