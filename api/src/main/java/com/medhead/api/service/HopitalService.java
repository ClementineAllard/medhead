package com.medhead.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Hopital;
import com.medhead.api.repository.HopitalRepository;

import lombok.Data;

@Data
@Service
public class HopitalService {
    
    @Autowired
    private HopitalRepository hopitalRepository;


    /**
     * Récupération d'un hôpital à partir de son id
     * @param id - id de l'hôpital
     * @return l'hôpital demandé
     */
    public Optional<Hopital> getHopital(final Long id) {
        return hopitalRepository.findById(id);
    }


    /**
     * Récupération de toutes les hôpitaux
     * @return liste des hôpitaux
     */
    public Iterable<Hopital> getHopitaux() {
        return hopitalRepository.findAll();
    }
    
    
    /**
     * Suppression d'un hôpital
     * @param id - id de l'hôpital à supprimer
     */
	public void deleteHopital(final Long id) {
		hopitalRepository.deleteById(id);
	}


    /**
     * Enregistrement d'un nouvel hôpital
     * @param specialite - hôpital à enregistrer
     * @return l'hôpital qui a été créé
     */
    public Hopital saveHopital(Hopital hopital) {
		Hopital savedHopital = hopitalRepository.save(hopital);
		return savedHopital;
	}

}
