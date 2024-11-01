package com.medhead.api.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Hopital;
import com.medhead.api.service.HopitalService;

@RestController
public class HopitalController {

    @Autowired
    private HopitalService hopitalService;

    /**
	 * Read - Récupérer les hopitaux à partir d'ids
	 * @param ids Liste des ids des hopitaux
	 * @return - Liste des hopitaux
	 */
	@GetMapping("/hopitaux/{ids}")
	public Iterable<Hopital> getHopitaux(@PathVariable("ids") String ids) {
        Iterable<Long> idsList = Arrays.stream(ids.split(","))
                                  .map(Long::parseLong)
                                  .collect(Collectors.toList());
		return hopitalService.getHopitaux(idsList);
	}

}
