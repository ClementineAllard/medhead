package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HopitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    
    @Test
    public void testGetHopitaux() throws Exception {
        mockMvc.perform(get("/hopital/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nom", is("Hôpital Saint-Jean")));
    }

    @Test
    public void testReservationLit() throws Exception {
        mockMvc.perform(post("/reservation/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nbLit", is(0)));
    }
}
