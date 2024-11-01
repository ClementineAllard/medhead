package com.medhead.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.api.model.Specialite;

@Repository
public interface SpecialiteRepository extends CrudRepository<Specialite, Long> {

    // Rechercher par code parent
    List<Specialite> findByParent(String parent);
    
}
