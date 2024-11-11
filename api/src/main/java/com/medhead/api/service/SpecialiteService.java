package com.medhead.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Specialite;
import com.medhead.api.repository.SpecialiteRepository;

import lombok.Data;

@Data
@Service
public class SpecialiteService {
    
    @Autowired
    private SpecialiteRepository specialiteRepository;


    /**
     * Récupération d'une spécialité à partir de son id
     * @param id - id de la spécialité
     * @return la spécialité demandée
     */
    public Optional<Specialite> getSpecialite(final Long id) {
        return specialiteRepository.findById(id);
    }


    /**
     * Récupération de toutes les spécialités
     * @return liste des spécialités
     */
    public Iterable<Specialite> getSpecialites() {
        return specialiteRepository.findAll();
    }


    /**
     * Suppression d'une spécialité
     * @param id - id de la spécialité à supprimer
     */
	public void deleteSpecialite(final Long id) {
		specialiteRepository.deleteById(id);
	}


    /**
     * Enregistrement d'une nouvelle specialité
     * @param specialite - spécialité à enregistrer
     * @return la spécialité qui a été créée
     */
	public Specialite saveSpecialite(Specialite specialite) {
		Specialite savedSpecialite = specialiteRepository.save(specialite);
		return savedSpecialite;
	}


    /**
     * Récupération de tous les groupes de spécialités
     * @return liste des groupes
     */
    public Iterable<Specialite> getGroupes() {
        return specialiteRepository.findByParent(null);
    }


    /**
     * Récupération des spécialités d'un groupe
     * @param parent - id du groupe parent
     * @return liste des spécialités du groupe
     */
    public Iterable<Specialite> getSpecialitesByParent(Long parent) {
        return specialiteRepository.findByParent(parent);
    }
}
