package com.medhead.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.webapp.model.Specialisation;
import com.medhead.webapp.repository.SpecialisationProxy;

import lombok.Data;

@Data
@Service
public class SpecialisationService {

    @Autowired
	private SpecialisationProxy specialisationProxy;

	/**
	 * Récupération de la liste des specialisations d'hopitaux
     * @param id specialité demandée
	 * @return liste de specialisation
	 */
	public Iterable<Specialisation> getHopitauxBySpecialite(final Long id) {
		return specialisationProxy.getHopitauxBySpecialite(id);
	}

}
