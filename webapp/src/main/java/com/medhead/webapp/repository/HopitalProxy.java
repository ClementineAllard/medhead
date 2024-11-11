package com.medhead.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.medhead.webapp.CustomProperties;
import com.medhead.webapp.model.Hopital;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HopitalProxy {

    
	@Autowired
	private CustomProperties props;    
    
	/**
	 * Réservation d'un lit d'hôpital via l'API
     * @param id - Id de l'hôpital à modifier
	 * @return hopital modifié
	 */
	public Hopital updateReservationLit(final Long id) {
		// Appel à l'API
		String baseApiUrl = props.getApiUrl();
		String putReservationUrl = baseApiUrl + "/hopital/reservation/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Hopital> response = restTemplate.exchange(
                putReservationUrl, 
				HttpMethod.PUT, 
				null,
				Hopital.class
			);
		
		log.debug("Réservation d'un lit d'hopital : " + response.getStatusCode().toString());
		
		return response.getBody();
	}

}
