package com.medhead.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.medhead.webapp.CustomProperties;
import com.medhead.webapp.model.Specialite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpecialiteProxy {

    
	@Autowired
	private CustomProperties props;

    
	/**
	 * Récupération, via l'API, de la liste des groupes de spécialités
	 * @return liste de groupes
	 */
	public Iterable<Specialite> getGroupes() {
		// Appel à l'API
		String baseApiUrl = props.getApiUrl();
		String getGroupesUrl = baseApiUrl + "/groupes";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Specialite>> response = restTemplate.exchange(
                getGroupesUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Specialite>>() {}
			);
		
		log.debug("Récupération des groupes : " + response.getStatusCode().toString());
		
		return response.getBody();
	}

    /**
	 * Récupération, via l'API, de la liste des spécialités d'un groupe
	 * @param parent id du groupe parent
	 * @return liste de spécialités
	 */
	public Iterable<Specialite> getSpecialites(Long parent) {
		// Appel à l'API
		String baseApiUrl = props.getApiUrl();
		String getSpecialitesUrl = baseApiUrl + "/specialites/" + parent;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Specialite>> response = restTemplate.exchange(
				getSpecialitesUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Specialite>>() {}
			);
		
		log.debug("Récupération des spécialités : " + response.getStatusCode().toString());
		
		return response.getBody();
	}
}
