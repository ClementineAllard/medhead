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
import com.medhead.api.model.Specialite;
import com.medhead.api.model.Specialisation;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class) 
public class SpecialisationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Spécialisation fictive pour les tests
    private static Specialisation specialisation = new Specialisation();
    // Hôpitaux fictifs pour les tests
    private static Hopital hopital = new Hopital();
    private static Hopital hopital2 = new Hopital();
    // Spécialités fictives pour les tests
    private static Specialite specialite = new Specialite();
    private static Specialite specialite2 = new Specialite();

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

    @Test
    @Order(2)
    public void testInsertionHopital2() throws Exception {
        // Hôpital fictif pour le test
        hopital2.setNom("Hôpital 2 de test");
        hopital2.setAdresse("31 rue du test");
        hopital2.setCp("90002");
        hopital2.setVille("Test2");
        hopital2.setPays("F2");
        hopital2.setTelephone("0000000002");
        hopital2.setNbLit(102);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String hopitalJson = objectMapper.writeValueAsString(hopital2);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/hopital")
                .contentType(MediaType.APPLICATION_JSON).content(hopitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value(hopital2.getNom()))
                .andExpect(jsonPath("$.adresse").value(hopital2.getAdresse()))
                .andExpect(jsonPath("$.cp").value(hopital2.getCp()))
                .andExpect(jsonPath("$.ville").value(hopital2.getVille()))
                .andExpect(jsonPath("$.pays").value(hopital2.getPays()))
                .andExpect(jsonPath("$.telephone").value(hopital2.getTelephone()))
                .andExpect(jsonPath("$.nbLit").value(hopital2.getNbLit()))
                .andReturn();

        // Récupération de l'id du nouvel hôpital
        String jsonResponse = result.getResponse().getContentAsString();
        Hopital hopitalResponse = objectMapper.readValue(jsonResponse, Hopital.class);
        hopital2.setId(hopitalResponse.getId());
    }

    @Test
    @Order(3)
    public void testInsertionSpecialite() throws Exception{
        // Spécialité fictive pour le test
        specialite.setLibelle("Spécialité de test");
        specialite.setParent(null);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String specialiteJson = objectMapper.writeValueAsString(specialite);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/specialite")
                .contentType(MediaType.APPLICATION_JSON).content(specialiteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value(specialite.getLibelle()))
                .andExpect(jsonPath("$.parent").value(specialite.getParent()))
                .andReturn();

        // Récupération de l'id de la nouvelle spécialité
        String jsonResponse = result.getResponse().getContentAsString();
        Specialite specialiteResponse = objectMapper.readValue(jsonResponse, Specialite.class);
        specialite.setId(specialiteResponse.getId());
    }

    @Test
    @Order(4)
    public void testInsertionSpecialite2() throws Exception{
        // Spécialité fictive pour le test
        specialite2.setLibelle("Spécialité 2 de test");
        specialite2.setParent(null);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String specialiteJson = objectMapper.writeValueAsString(specialite2);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/specialite")
                .contentType(MediaType.APPLICATION_JSON).content(specialiteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value(specialite2.getLibelle()))
                .andExpect(jsonPath("$.parent").value(specialite2.getParent()))
                .andReturn();

        // Récupération de l'id de la nouvelle spécialité
        String jsonResponse = result.getResponse().getContentAsString();
        Specialite specialiteResponse = objectMapper.readValue(jsonResponse, Specialite.class);
        specialite2.setId(specialiteResponse.getId());
    }

    @Test
    @Order(5)
    public void testInsertionSpecialisation() throws Exception{
        // Spécialisation fictive pour le test
        specialisation.setHopital(hopital);
        specialisation.setSpecialite(specialite);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String specialisationJson = objectMapper.writeValueAsString(specialisation);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/specialisation")
                .contentType(MediaType.APPLICATION_JSON).content(specialisationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hopital.nom").value(hopital.getNom()))
                .andExpect(jsonPath("$.specialite.libelle").value(specialite.getLibelle()))
                .andReturn();

        // Récupération de l'id de la nouvelle spécialisation
        String jsonResponse = result.getResponse().getContentAsString();
        Specialisation specialisationResponse = objectMapper.readValue(jsonResponse, Specialisation.class);
        specialisation.setId(specialisationResponse.getId());
    }
    //-------------------- Test d'insertion --------------------

    //-------------------- Test de consultation --------------------
    @Test
    @Order(6)
    public void testGetSpecialisation() throws Exception {
        mockMvc.perform(get("/specialisation/" + specialisation.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hopital.nom").value(hopital.getNom()))
            .andExpect(jsonPath("$.specialite.libelle").value(specialite.getLibelle()));
    }

    @Test
    @Order(7)
    public void testGetSpecialisations() throws Exception {
        mockMvc.perform(get("/specialisations"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void testGetSpecialisationSpecialite() throws Exception {
        mockMvc.perform(get("/specialisation/recherche/"+ specialite.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].hopital.nom").value(hopital.getNom()))
            .andExpect(jsonPath("$[0].specialite.libelle").value(specialite.getLibelle()));
    }
    //-------------------- Test de consultation --------------------
    
    //-------------------- Test de modification --------------------
    @Test
    @Order(9)
    public void testModificationSpecialisation() throws Exception {
        // Spécialisation fictive pour le test
        specialisation.setHopital(hopital2);
        specialisation.setSpecialite(specialite2);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String specialisationJson = objectMapper.writeValueAsString(specialisation);

        // Exécution de la requête
        mockMvc.perform(put("/specialisation/" + specialisation.getId())
                .contentType(MediaType.APPLICATION_JSON).content(specialisationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hopital.nom").value(hopital2.getNom()))
                .andExpect(jsonPath("$.specialite.libelle").value(specialite2.getLibelle()))
                .andReturn();
    }
    //-------------------- Test de modification --------------------

    
    //-------------------- Test de suppression --------------------
    @Test
    @Order(10)
    public void testSuppressionSpecialisation() throws Exception {
        mockMvc.perform(delete("/specialisation/" + specialisation.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/specialisation/" + specialisation.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Order(11)
    public void testSuppressionHopital() throws Exception {
        mockMvc.perform(delete("/hopital/" + hopital.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/hopital/" + hopital.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Order(12)
    public void testSuppressionHopital2() throws Exception {
        mockMvc.perform(delete("/hopital/" + hopital2.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/hopital/" + hopital2.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Order(13)
    public void testSuppressionSpecialite() throws Exception {
        mockMvc.perform(delete("/specialite/" + specialite.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/specialite/"+specialite.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Order(14)
    public void testSuppressionSpecialite2() throws Exception {
        mockMvc.perform(delete("/specialite/" + specialite2.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/specialite/"+specialite2.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }


    
    //-------------------- Test de suppression --------------------

}
