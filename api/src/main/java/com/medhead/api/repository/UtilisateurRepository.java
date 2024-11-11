package com.medhead.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.api.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long>  {

    // Rechercher par mail et mot de passe
    List<Utilisateur> findByEmailAndMdp(String email, String mdp);

}
