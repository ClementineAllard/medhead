package com.medhead.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.medhead.webapp.model.Utilisateur;
import com.medhead.webapp.model.AjaxResponseBodySpecialite;
import com.medhead.webapp.model.AjaxResponseBodyHopital;
import com.medhead.webapp.model.Specialite;
import com.medhead.webapp.model.Hopital;
import com.medhead.webapp.service.SpecialiteService;
import com.medhead.webapp.service.UtilisateurService;
import com.medhead.webapp.service.SpecialisationService;
import com.medhead.webapp.service.HopitalService;

@Controller
public class controller {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private SpecialiteService specialiteService;

	@Autowired
	private SpecialisationService specialisationService;

	@Autowired
	private HopitalService hopitalService;

	/**
	 * Ouverture de la page de connexion
	 * @param model Modèle de la page
	 * @return - Page avec un objet utilisateur pour le formulaire
	 */
    @GetMapping("/")
    public String connexion(Model model) {
		// Création d'un objet utilisateur pour permettre la saisie dans le formulaire de connexion
		Utilisateur u = new Utilisateur();
		model.addAttribute("utilisateur", u);

        return "connexion";
    }

	/**
	 * Connexion au système
	 * @param utilisateur informations de connexion
	 * @param model Modèle de la page
	 * @return - Page de recherche avec la liste des groupes de spécialités disponibles
	 */
    @GetMapping("/connexion")
	public String connexion(@ModelAttribute Utilisateur utilisateur, Model model) {

		// Vérification que le mot de passe saisi est correct
		if(utilisateurService.connecteUtilisateurByEmailMdp(utilisateur.getEmail(), utilisateur.getMdp())) { // Si connexion OK
			// Récupération de la liste des groupes de spécialités
            Iterable<Specialite> listeGroupes = specialiteService.getGroupes();
		    model.addAttribute("groupes", listeGroupes);

			// Envoi à la page de recherche d'hopitaux
			return "research";
		}

		// Si connexion KO -> renvoi à la page de connexion
		model.addAttribute("message", "Email ou mot de passe incorrect...");
		model.addAttribute("messageType", "error");
		return "connexion";
	}

	 /**
	 * Récupération de la liste des specialités d'un groupe
	 * @param parent code du groupe parent
	 * @return - Liste des specialités du groupe
	 */
	@GetMapping("/specialites/{parent}")
	public ResponseEntity<?> getSpecialites(@PathVariable("parent") Long parent) {
		// Création de la réponse AJAX
		AjaxResponseBodySpecialite result = new AjaxResponseBodySpecialite();

		// Récupération de la liste de spécialités
        Iterable<Specialite> specialites = specialiteService.getSpecialites(parent);
        result.setResult(specialites);
		result.setMsg("success");

        return ResponseEntity.ok(result);

    }
	
    /**
	 * Récupération des spécialisations à partir d'une spécialité
	 * @param id Id de la spécialité
	 * @return - Liste des spécialisations
	 */
	@GetMapping("/hopital/{id}/{adresse}")
	public ResponseEntity<?> getHopitauxBySpecialite(@PathVariable("id") final Long id, @PathVariable("adresse") String adresse) {
		// Création de la réponse AJAX
		AjaxResponseBodyHopital result = new AjaxResponseBodyHopital();

		// Récupération de la liste de spécialisations
        Hopital hopital = specialisationService.getHopitalBySpecialiteAndDistance(id,adresse);
        result.setResult(hopital);
		result.setMsg("success");

        return ResponseEntity.ok(result);

    }

	/**
	 * Réservation d'un lit d'hôpital
     * @param id - Id de l'hôpital à modifier
	 * @return hopital modifié
	 */
	@PutMapping("/reservation/{id}")
	public ResponseEntity<?> upDateReservationLit(@PathVariable("id") final Long id) {
		// Création de la réponse AJAX
		AjaxResponseBodyHopital result = new AjaxResponseBodyHopital();

		// Récupération de la liste de spécialisations
        Hopital hopital = hopitalService.updateReservationLit(id);
        result.setResult(hopital);
		result.setMsg("success");

        return ResponseEntity.ok(result);

    }



}
