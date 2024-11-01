package com.medhead.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Utilisateur;
import com.medhead.api.repository.UtilisateurRepository;

import lombok.Data;

@Data
@Service
public class UtilisateurService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> getUtilisateur(final Long id) {
        return utilisateurRepository.findById(id);
    }

    public boolean getUtilisateurByEmailAndMdp(String email, String mdp) {
        List<Utilisateur> utilisateurs = utilisateurRepository.findByEmailAndMdp(email, mdp);
        if (utilisateurs.size() == 1){
            return true;
        }
        return false;
    }

    public Iterable<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

}
