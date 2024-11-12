package com.medhead.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medhead.api.model.Specialisation;

@Repository
public interface SpecialisationRepository  extends CrudRepository<Specialisation, Long>  {

    @Query("SELECT s FROM Specialisation s JOIN s.specialite sp JOIN s.hopital h WHERE sp.id = :specialite AND h.nbLit > 0")
    Iterable<Specialisation> findSpecialisationBySpecialite(@Param("specialite") Long specialite);

}
