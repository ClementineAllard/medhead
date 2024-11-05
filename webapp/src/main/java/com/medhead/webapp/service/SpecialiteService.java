package com.medhead.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.webapp.model.Specialite;
import com.medhead.webapp.repository.SpecialiteProxy;

import lombok.Data;

@Data
@Service
public class SpecialiteService {

    @Autowired
	private SpecialiteProxy specialiteProxy;

	/**
	 * Récupération de la liste des groupes de spécialités
	 * @return liste de groupes
	 */
	public Iterable<Specialite> getGroupes() {
		return specialiteProxy.getGroupes();
	}

	/**
	 * Récupération des spécialités d'un groupe
	 * @param parent id du groupe parent
	 * @return liste de spécialités
	 */
    public Iterable<Specialite> getSpecialites(Long parent) {
		return specialiteProxy.getSpecialites(parent);
	}

}
