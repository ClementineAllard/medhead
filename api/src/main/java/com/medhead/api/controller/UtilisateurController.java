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

import com.medhead.api.model.Utilisateur;
import com.medhead.api.service.UtilisateurService;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Create - Ajout d'un nouvel utilisateur
     * @param cle - clé de cryptage pour le mot de passe
     * @param utilisateur - utilisateur à créer
     * @return le nouvel utilisateur créé
     */
    @PostMapping("/utilisateur/{cle}")
    public Utilisateur createUtilisateur(@PathVariable("cle") String cle, @RequestBody Utilisateur utilisateur){
        return utilisateurService.saveUtilisateur(utilisateur, cle);
    }


     /**
	 * Read - Récupération d'un utilisateur
	 * @param id - id de l'utilisateur
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
    * Read - Récupération de tous les utilisateurs
    * @return la liste des utilisateurs
    */
    @GetMapping("/utilisateurs")
    public Iterable<Utilisateur> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }


    /**
     * Update - Modification d'un utilisateur
     * @param id - id de l'utilisateur à modifier
     * @param cle - clé de cryptage pour le mot de passe
     * @param utilisateur - utilisateur à modifier
     * @return l'utilisateur modifié
     */
    @PutMapping("/utilisateur/{id}/{cle}")
    public Utilisateur updateUtilisateur(@PathVariable("id") final Long id, @PathVariable("cle") String cle, @RequestBody Utilisateur utilisateur){
        // On recherche l'utilisateur enregistré qui est à modifier
        Optional<Utilisateur> u = utilisateurService.getUtilisateur(id);
		if(u.isPresent()) { // S'il existe alors on le modifie
			Utilisateur currentUtilisateur = u.get();
			
			String email = utilisateur.getEmail();
			if(email != null) {
				currentUtilisateur.setEmail(email);
			}
			String prenom = utilisateur.getPrenom();
			if(prenom != null) {
				currentUtilisateur.setPrenom(prenom);;
			}
			String nom = utilisateur.getNom();
			if(nom != null) {
				currentUtilisateur.setNom(nom);
			}
			String mdp = utilisateur.getMdp();
			if(mdp != null) {
				currentUtilisateur.setMdp(mdp);;
			}

			utilisateurService.saveUtilisateur(currentUtilisateur, cle);
			return currentUtilisateur;
		} else {
			return null;
		}
    }

    
	/**
	 * Delete - Suppression d'un utilisateur
	 * @param id - id de l'utilisateur à supprimer
	 */
	@DeleteMapping("/utilisateur/{id}")
	public void deleteUtilisateur(@PathVariable("id") final Long id) {
		utilisateurService.deleteUtilisateur(id);
	}


    /**
    * Read - Vérification que l'utilisateur peut se connecter
    * @param email - email de l'utilisateur
    * @param mdp - mot de passe saisi par l'utilisateur
    * @param cle - clé de cryptage pour le mot de passe
    * @return vrai si le mdp est correct
    */
    @GetMapping("/connexion/{email}/{mdp}/{cle}")
    public boolean verifConnexion(@PathVariable("email") String email, @PathVariable("mdp") String mdp, @PathVariable("cle") String cle) {
        return utilisateurService.verifConnexion(email, mdp, cle);
    }

}
