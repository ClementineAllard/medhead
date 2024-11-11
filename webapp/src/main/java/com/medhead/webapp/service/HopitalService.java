package com.medhead.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.webapp.model.Hopital;
import com.medhead.webapp.repository.HopitalProxy;

import lombok.Data;

@Data
@Service
public class HopitalService {

    @Autowired
	private HopitalProxy hopitalProxy;

	/**
	 * Réservation d'un lit d'hôpital
     * @param id - Id de l'hôpital à modifier
	 * @return hopital modifié
	 */
	public Hopital updateReservationLit(final Long id) {
		return hopitalProxy.updateReservationLit(id);
	}
}
