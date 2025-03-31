package com.example.twitter.example;

import com.example.twitter.controller.ClientController;
import com.example.twitter.core.SpringControllerBaseTest;
import com.example.twitter.dto.ClientDTO;
import com.example.twitter.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest extends SpringControllerBaseTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void updateClient() throws Exception {
        Long id = 1L;
        ClientDTO clientDTO = new ClientDTO();
        ClientDTO updatedClientDTO = new ClientDTO();

        when(clientService.updateClientData(id, clientDTO)).thenReturn(updatedClientDTO);

        mockMvc.perform(put("/clients/update/{id}", id)
                        .contentType("application/json")
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedClientDTO.getId()));

        verify(clientService, times(1)).updateClientData(id, clientDTO);
    }

    @Test
    void updateClientNotFound() throws Exception {
        Long id = 1L;
        ClientDTO clientDTO = new ClientDTO();  // Assuming clientDTO is suitably populated

        when(clientService.updateClientData(id, clientDTO)).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(put("/clients/update/{id}", id)
                        .contentType("application/json")
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).updateClientData(id, clientDTO);
    }

    @Test
    void deleteClient() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/clients/delete/{id}", id))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteClient(id);
    }

    @Test
    void deleteClientNotFound() throws Exception {
        Long id = 1L;

        doThrow(new RuntimeException("Not found")).when(clientService).deleteClient(id);

        mockMvc.perform(delete("/clients/delete/{id}", id))
                .andExpect(status().isNotFound());

        verify(clientService, times(1)).deleteClient(id);
    }
}
