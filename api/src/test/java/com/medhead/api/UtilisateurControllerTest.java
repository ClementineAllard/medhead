package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    private String cleCryptage = "MEDHEAD_API_TEST";

    @Test
    public void testGetUtilisateur() throws Exception {
        mockMvc.perform(get("/utilisateur/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email", is("admin")));
    }

    @Test
    public void testGetUtilisateurs() throws Exception {
        mockMvc.perform(get("/utilisateurs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].email", is("admin")));
    }

    
    @Test
    public void testConnexionByEmailAndMdp() throws Exception {
        mockMvc.perform(get("/utilisateur/laurentgina@mail.com/laurent/"+cleCryptage))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    @Test
    public void testConnexionMdpIncorrect() throws Exception {
        mockMvc.perform(get("/utilisateur/laurentgina@mail.com/incorrect/"+cleCryptage))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
    }

    @Test
    public void testConnexionEmailInexistant() throws Exception {
        mockMvc.perform(get("/utilisateur/laurentginaa@mail.com/laurent/"+cleCryptage))
            .andExpect(status().isOk())
            .andExpect(content().string("false"));
    }


}
