package com.medhead.webapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WebappApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@Test
    public void testPageConnexion() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("connexion"))
            .andExpect(model().attributeExists("utilisateur"));
    }

	@Test
    public void testConnexionOK() throws Exception {
        mockMvc.perform(get("/connexion")
            .param("email", "admin")
            .param("mdp", "admin"))
            .andExpect(status().isOk())
            .andExpect(view().name("research"))
            .andExpect(model().attributeExists("groupes"));
    }

	@Test
    public void testConnexionKO() throws Exception {
        mockMvc.perform(get("/connexion")
            .param("email", "admin")
            .param("mdp", "adminKO"))
            .andExpect(status().isOk())
            .andExpect(view().name("connexion"))
            .andExpect(model().attribute("messageType", "error"));
    }

	@Test
    public void testGetSpecialites() throws Exception {
        mockMvc.perform(get("/specialites/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("success"))
            .andExpect(jsonPath("$.result[0].libelle").value("Anesthésie"));
    }

	@Test
    public void testGetHopitauxBySpecialite() throws Exception {
        mockMvc.perform(get("/hopital/1/Toulouse"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("success"))
            .andExpect(jsonPath("$.result.nom").value("Hôpital Saint-Vincent"));
    }

	@Test
    public void testUpDateReservationLit() throws Exception {
        mockMvc.perform(put("/reservation/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.msg").value("success"));
    }



}
