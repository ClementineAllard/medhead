package com.medhead.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.medhead.api.model.Utilisateur;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class) 
public class UtilisateurControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    // Clé de cryptage pour les tests
    private String cleCryptage = "MEDHEAD_API_TEST";
    // Utilisateur fictif pour les tests
    private static Utilisateur utilisateur = new Utilisateur();


    //-------------------- Test d'insertion --------------------
    @Test
    @Order(1)
    public void testInsertionUtilisateur() throws Exception {
        // Utilisateur fictif pour le test
        utilisateur.setEmail("utilisateurTest@mail.com");
        utilisateur.setPrenom("Utilisateur");
        utilisateur.setNom("Test");
        utilisateur.setMdp("mdptest");

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String utilisateurJson = objectMapper.writeValueAsString(utilisateur);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/utilisateur/" + cleCryptage)
                .contentType(MediaType.APPLICATION_JSON).content(utilisateurJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(utilisateur.getEmail()))
                .andExpect(jsonPath("$.prenom").value(utilisateur.getPrenom()))
                .andExpect(jsonPath("$.nom").value(utilisateur.getNom()))
                .andReturn();

        // Récupération de l'id du nouvel utilisateur
        String jsonResponse = result.getResponse().getContentAsString();
        Utilisateur utilisateurResponse = objectMapper.readValue(jsonResponse, Utilisateur.class);
        utilisateur.setId(utilisateurResponse.getId());
    }
    //-------------------- Test d'insertion --------------------


    //-------------------- Test de consultation --------------------
    @Test
    @Order(2)
    public void testGetUtilisateur() throws Exception {
        mockMvc.perform(get("/utilisateur/"+utilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value(utilisateur.getEmail()))
            .andExpect(jsonPath("$.prenom").value(utilisateur.getPrenom()))
            .andExpect(jsonPath("$.nom").value(utilisateur.getNom()));
    }

    @Test
    @Order(3)
    public void testGetUtilisateurs() throws Exception {
        mockMvc.perform(get("/utilisateurs"))
            .andExpect(status().isOk());
    }
    //-------------------- Test de consultation --------------------


    //-------------------- Test de modification --------------------
    @Test
    @Order(4)
    public void testModificationUtilisateur() throws Exception {
        // Utilisateur fictif pour le test
        utilisateur.setEmail("utilisateurTestModif@mail.com");
        utilisateur.setPrenom("UtilisateurModif");
        utilisateur.setNom("TestModif");
        utilisateur.setMdp("mdptestmodif");

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String utilisateurJson = objectMapper.writeValueAsString(utilisateur);

        // Exécution de la requête
        mockMvc.perform(put("/utilisateur/" + utilisateur.getId() + "/" + cleCryptage)
                .contentType(MediaType.APPLICATION_JSON).content(utilisateurJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(utilisateur.getEmail()))
                .andExpect(jsonPath("$.prenom").value(utilisateur.getPrenom()))
                .andExpect(jsonPath("$.nom").value(utilisateur.getNom()));
    }
    //-------------------- Test de modification --------------------


    //-------------------- Test de connexion --------------------

    @Test
    @Order(5)
    public void testConnexionOK() throws Exception {
        mockMvc.perform(get("/utilisateur/connexion/" + utilisateur.getEmail() + "/" + utilisateur.getMdp() +"/"+ cleCryptage))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    @Test
    @Order(6)
    public void testConnexionMdpIncorrect() throws Exception {
        mockMvc.perform(get("/utilisateur/connexion/" + utilisateur.getEmail() + "/" + utilisateur.getMdp() +"incorrect/"+cleCryptage))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
    }

    @Test
    @Order(7)
    public void testConnexionMauvaiseCle() throws Exception {
        mockMvc.perform(get("/utilisateur/connexion/" + utilisateur.getEmail() + "/" + utilisateur.getMdp() +"/MAUVAIS_CLE"))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
    }

    //-------------------- Test de connexion --------------------


    //-------------------- Test de suppression --------------------
    @Test
    @Order(8)
    public void testSuppressionUtilisateur() throws Exception {
        mockMvc.perform(delete("/utilisateur/"+utilisateur.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/utilisateur/"+utilisateur.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }
    //-------------------- Test de suppression --------------------

}
