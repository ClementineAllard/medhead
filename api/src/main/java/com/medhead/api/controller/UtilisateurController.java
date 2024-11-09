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
    * Read - Vérifier que l'utilisateur peut se connecter
    * @param email email de l'utilisateur
    * @param mdp mdp saisi par l'utilisateur
    * @param cle clé de cryptage pour le mot de passe
    * @return - vrai si le mdp est correct
    */
    @GetMapping("/utilisateur/{email}/{mdp}/{cle}")
    public boolean getUtilisateurByEmail(@PathVariable("email") String email, @PathVariable("mdp") String mdp, @PathVariable("cle") String cle) {
        return utilisateurService.getUtilisateurByEmailAndMdp(email, mdp, cle);
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
