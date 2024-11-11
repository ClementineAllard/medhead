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
import com.medhead.api.model.Specialite;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class) 
public class SpecialiteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    // Spécialité fictive pour les tests
    private static Specialite groupe = new Specialite();
    private static Specialite specialite = new Specialite();


    //-------------------- Test d'insertion --------------------
    @Test
    @Order(1)
    public void testInsertionGroupe() throws Exception {
        // Groupe fictif pour le test
        groupe.setLibelle("Groupe de test");
        groupe.setParent(null);

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String groupeJson = objectMapper.writeValueAsString(groupe);

        // Exécution de la requête
        MvcResult result = mockMvc.perform(post("/specialite")
                .contentType(MediaType.APPLICATION_JSON).content(groupeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value(groupe.getLibelle()))
                .andExpect(jsonPath("$.parent").value(groupe.getId()))
                .andReturn();

        // Récupération de l'id du nouveau groupe
        String jsonResponse = result.getResponse().getContentAsString();
        Specialite groupeResponse = objectMapper.readValue(jsonResponse, Specialite.class);
        groupe.setId(groupeResponse.getId());
    }

    @Test
    @Order(2)
    public void testInsertionSpecialite() throws Exception{
        // Spécialité fictive pour le test
        specialite.setLibelle("Spécialité de test");
        specialite.setParent(groupe.getId());

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
    //-------------------- Test d'insertion --------------------


    //-------------------- Test de consultation --------------------
    @Test
    @Order(3)
    public void testGetSpecialite() throws Exception {
        mockMvc.perform(get("/specialite/" + specialite.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.libelle").value(specialite.getLibelle()))
            .andExpect(jsonPath("$.parent").value(specialite.getParent()));
    }

    @Test
    @Order(4)
    public void testGetSpecialites() throws Exception {
        mockMvc.perform(get("/specialites"))
            .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testGetSpecialiteByParent() throws Exception {
        mockMvc.perform(get("/specialites/" + groupe.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(specialite.getId()));
    }

    @Test
    @Order(6)
    public void testGetGroupes() throws Exception {
        mockMvc.perform(get("/specialite/groupes"))
            .andExpect(status().isOk());
    }
    //-------------------- Test de consultation --------------------


    //-------------------- Test de modification --------------------
    @Test
    @Order(7)
    public void testModificationSpecialite() throws Exception {
        // Spécialité fictive pour le test
        specialite.setLibelle("Spécialité modifiée");

        // Conversion de l'objet en Json
        ObjectMapper objectMapper = new ObjectMapper();
        String specialiteJson = objectMapper.writeValueAsString(specialite);

        // Exécution de la requête
        mockMvc.perform(put("/specialite/" + specialite.getId())
                .contentType(MediaType.APPLICATION_JSON).content(specialiteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle").value(specialite.getLibelle()))
                .andExpect(jsonPath("$.parent").value(specialite.getParent()));
    }
    //-------------------- Test de modification --------------------


    //-------------------- Test de suppression --------------------
    @Test
    @Order(8)
    public void testSuppressionSpecialite() throws Exception {
        mockMvc.perform(delete("/specialite/" + specialite.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/specialite/"+specialite.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Order(9)
    public void testSuppressionGroupe() throws Exception {
        mockMvc.perform(delete("/specialite/" + groupe.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/specialite/"+groupe.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").doesNotExist());
    }
    //-------------------- Test de suppression --------------------

}
