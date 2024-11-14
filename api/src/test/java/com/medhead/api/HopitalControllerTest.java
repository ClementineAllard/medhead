package com.medhead.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.api.model.Hopital;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class) 
public class HopitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Hôpital fictif pour les tests
    private static Hopital hopital = new Hopital();
    
    //-------------------- Test d'insertion --------------------
    @Test
    @Order(1)
    public void testInsertionHopital() throws Exception {
        // Hôpital fictif pour le test
        hopital.setNom("Hôpital de test");
        hopital.setAdresse("30 rue du test");
        hopital.setCp("90000");
        hopital.setVille("Test");
        hopital.setPays("FR");
        hopital.setTelephone("0000000000");
        hopital.setNbLit(100);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String hopitalJson = objectMapper.writeValueAsString(hopital);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/hopital")
                .contentType(MediaType.APPLICATION_JSON).content(hopitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(hopital.getNom()))
                .andExpect(jsonPath("$.adresse").value(hopital.getAdresse()))
                .andExpect(jsonPath("$.cp").value(hopital.getCp()))
                .andExpect(jsonPath("$.ville").value(hopital.getVille()))
                .andExpect(jsonPath("$.pays").value(hopital.getPays()))
                .andExpect(jsonPath("$.telephone").value(hopital.getTelephone()))
                .andExpect(jsonPath("$.nbLit").value(hopital.getNbLit()))
                .andReturn();

        // Récupération de l'id du nouvel hôpital
        String jsonResponse = result.getResponse().getContentAsString();
        Hopital hopitalResponse = objectMapper.readValue(jsonResponse, Hopital.class);
        hopital.setId(hopitalResponse.getId());
    }
    //-------------------- Test d'insertion --------------------


    //-------------------- Test de consultation --------------------
    @Test
    @Order(2)
    public void testGetHopital() throws Exception {
        mockMvc.perform(get("/hopital/" + hopital.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nom").value(hopital.getNom()))
            .andExpect(jsonPath("$.adresse").value(hopital.getAdresse()))
            .andExpect(jsonPath("$.cp").value(hopital.getCp()))
            .andExpect(jsonPath("$.ville").value(hopital.getVille()))
            .andExpect(jsonPath("$.pays").value(hopital.getPays()))
            .andExpect(jsonPath("$.telephone").value(hopital.getTelephone()))
            .andExpect(jsonPath("$.nbLit").value(hopital.getNbLit()));
    }

    @Test
    @Order(3)
    public void testGetHopitaux() throws Exception {
        mockMvc.perform(get("/hopitaux"))
            .andExpect(status().isOk());
    }
    //-------------------- Test de consultation --------------------


    //-------------------- Test de modification --------------------
    @Test
    @Order(4)
    public void testModificationHopital() throws Exception {
        // Hôpital fictif pour le test
        hopital.setNom("Hôpital modifié");
        hopital.setAdresse("30 rue modifiée");
        hopital.setCp("90001");
        hopital.setVille("Modification");
        hopital.setPays("FM");
        hopital.setTelephone("0000000001");
        hopital.setNbLit(120);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String hopitalJson = objectMapper.writeValueAsString(hopital);

        // Exécution de la requête
        mockMvc.perform(put("/hopital/" + hopital.getId())
                .contentType(MediaType.APPLICATION_JSON).content(hopitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(hopital.getNom()))
                .andExpect(jsonPath("$.adresse").value(hopital.getAdresse()))
                .andExpect(jsonPath("$.cp").value(hopital.getCp()))
                .andExpect(jsonPath("$.ville").value(hopital.getVille()))
                .andExpect(jsonPath("$.pays").value(hopital.getPays()))
                .andExpect(jsonPath("$.telephone").value(hopital.getTelephone()))
                .andExpect(jsonPath("$.nbLit").value(hopital.getNbLit()));
    }

    @Test
    @Order(5)
    public void testReservationLit() throws Exception {
        mockMvc.perform(put("/hopital/reservation/" + hopital.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nbLit").value(hopital.getNbLit()-1));
    }
    //-------------------- Test de modification --------------------


    //-------------------- Test de suppression --------------------
    @Test
    @Order(6)
    public void testSuppressionHopital() throws Exception {
        mockMvc.perform(delete("/hopital/" + hopital.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/hopital/" + hopital.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }
    //-------------------- Test de suppression --------------------


    
}
