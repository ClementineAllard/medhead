package com.medhead.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Utilisateur;
import com.medhead.api.service.UtilisateurService;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
    * Read - Récupérer les utilisateurs à partir d'un email
    * @param email Email de l'utilisateur
    * @return - Liste des utilisateurs
    */
    @GetMapping("/utilisateur/{email}/{mdp}")
    public boolean getUtilisateurByEmail(@PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        return utilisateurService.getUtilisateurByEmailAndMdp(email, mdp);
    }

    /**
	 * Read - Récupérer un utilisateur
	 * @param id Id de l'utilisateur
	 * @return l'utilisateur demandé
	 */
	@GetMapping("/utilisateur/{id}")
	public Utilisateur getUtilisateur(@PathVariable("id") final Long id) {
		Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id);
		if(utilisateur.isPresent()) {
			return utilisateur.get();
		} else {
			return null;
		}
	}
    
    /**
    * Read - Récupérer tous les utilisateurs
    * @return - Liste des utilisateurs
    */
    @GetMapping("/utilisateurs")
    public Iterable<Utilisateur> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }


}
