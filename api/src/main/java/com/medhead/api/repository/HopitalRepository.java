package com.medhead.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.api.model.Hopital;

@Repository
public interface HopitalRepository extends CrudRepository<Hopital, Long>  {

}
