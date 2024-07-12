package org.myProject.crm.controller;

import org.junit.jupiter.api.Test;
import org.myProject.crm.dtos.ClientInputDTO;
import org.myProject.crm.entities.Client;
import org.myProject.crm.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testGetClients() throws Exception {
        mockMvc.perform(get("/client"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize((int) clientRepository.count())));
    }

    @Test
    void testAddClient() throws Exception {
        ClientInputDTO clientInputDTO = new ClientInputDTO();
        clientInputDTO.setFullName("John Doe");

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\":\"John Doe\"}"))
                .andExpect(status().isCreated());

        assertTrue(clientRepository.findByFullName("John Doe").isPresent());
    }

    @Test
    void testGetClientById() throws Exception {
        Client client = new Client();
        client.setFullName("John Doe");
        client = clientRepository.save(client);

        mockMvc.perform(get("/client/" + client.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));
    }

    @Test
    void testDeleteClient() throws Exception {
        Client client = new Client();
        client.setFullName("John Doe");
        client = clientRepository.save(client);

        mockMvc.perform(delete("/client/" + client.getFullName()))
                .andExpect(status().isNoContent());

        assertFalse(clientRepository.findByFullName("John Doe").isPresent());
    }
}
