package com.medhead.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.model.Hopital;
import com.medhead.api.repository.HopitalRepository;

import lombok.Data;

@Data
@Service
public class HopitalService {
    
    @Autowired
    private HopitalRepository hopitalRepository;

    public Iterable<Hopital> getHopitaux(Iterable<Long> ids) {
        return hopitalRepository.findAllById(ids);
    }

}
