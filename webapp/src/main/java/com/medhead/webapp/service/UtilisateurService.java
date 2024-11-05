package com.medhead.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.webapp.repository.UtilisateurProxy;

import lombok.Data;

@Data
@Service
public class UtilisateurService {

    
    @Autowired
	private UtilisateurProxy utilisateurProxy;

    /**
	 * Vérification que l'utilisateur peut se connecter
     * @param email email de l'utilisateur
     * @param mdp mdp saisi par l'utilisateur
	 * @return vrai si le mdp est correct
	 */
	public boolean connecteUtilisateurByEmailMdp(String email, String mdp) {
		return utilisateurProxy.connecteUtilisateurByEmailMdp(email, mdp);
	}
}
