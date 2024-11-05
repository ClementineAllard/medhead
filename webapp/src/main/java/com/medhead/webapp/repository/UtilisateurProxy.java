package com.medhead.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.medhead.webapp.CustomProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UtilisateurProxy {

    
	@Autowired
	private CustomProperties props;

    
	/**
	 * Vérification que l'utilisateur peut se connecter
     * @param email email de l'utilisateur
     * @param mdp mdp saisi par l'utilisateur
	 * @return vrai si le mdp est correct
	 */
	public boolean connecteUtilisateurByEmailMdp(String email, String mdp) {
		// Appel à l'API
		String baseApiUrl = props.getApiUrl();
		String connecteUtilisateurUrl = baseApiUrl + "/utilisateur/" + email + "/" + mdp;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Boolean> response = restTemplate.exchange(
            connecteUtilisateurUrl, 
				HttpMethod.GET, 
				null,
				Boolean.class
			);
		
		log.debug("Connexion utilisateur : " + response.getStatusCode().toString());
		
		return response.getBody();
	}
}
