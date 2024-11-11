package com.medhead.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.medhead.webapp.CustomProperties;
import com.medhead.webapp.model.Specialisation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpecialisationProxy {
    
	@Autowired
	private CustomProperties props;    
    
	/**
	 * Récupération, via l'API, de la liste des hopitaux spécialisés
	 * @return liste d'hopitaux
	 */
	public Iterable<Specialisation> getHopitauxBySpecialite(final Long id) {
		// Appel à l'API
		String baseApiUrl = props.getApiUrl();
		String getHopitauxUrl = baseApiUrl + "/specialisation/recherche/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Specialisation>> response = restTemplate.exchange(
				getHopitauxUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Specialisation>>() {}
			);
		
		log.debug("Récupération des hopitaux : " + response.getStatusCode().toString());
		
		return response.getBody();
	}
}
