package com.example.twitter.example;

import com.example.twitter.dto.ClientDTO;
import com.example.twitter.entity.Client;
import com.example.twitter.mapper.ClientMapper;
import com.example.twitter.mapper.UserMapper;
import com.example.twitter.repository.ClientRepository;

import com.example.twitter.service.ClientService;
import com.example.twitter.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Test
    void updateClientData() {
        // Arrange
        Long clientId = 1L;
        ClientDTO newClientData = new ClientDTO();
        newClientData.setBio("New bio");

        Client existingClient = new Client();
        existingClient.setId(clientId);
        existingClient.setBio("Old bio");

        Client updatedClient = new Client();
        updatedClient.setId(clientId);
        updatedClient.setBio("New bio");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);
        when(clientMapper.EntityToDTO(any(Client.class))).thenReturn(new ClientDTO());


        ClientDTO result = clientService.updateClientData(clientId, newClientData);


        assertNotNull(result);
        verify(clientRepository).save(existingClient);
        assertEquals("New bio", updatedClient.getBio());
    }

    @Test
    void deleteClient() {

        Long clientId = 1L;


        clientService.deleteClient(clientId);


        verify(clientRepository).deleteById(clientId);
    }
}
