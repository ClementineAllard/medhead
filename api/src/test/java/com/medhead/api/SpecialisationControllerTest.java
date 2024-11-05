package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecialisationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetSpecialisationHopital() throws Exception {
        mockMvc.perform(get("/specialisation/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.hopital.nom", is("Hôpital Saint-Jean")));
    }

    @Test
    public void testGetSpecialisationSpecialite() throws Exception {
        mockMvc.perform(get("/specialisation/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.specialite.libelle", is("Anesthésie")));
    }

    @Test
    public void testGetHopitauxSpecialite() throws Exception {
        mockMvc.perform(get("/hopitaux/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].specialite.libelle", is("Anesthésie")))
            .andExpect(jsonPath("$[0].hopital.nom", is("Hôpital Saint-Vincent")));
    }
}
