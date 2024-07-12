package org.myProject.crm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.myProject.crm.dtos.ClientInputDTO;
import org.myProject.crm.entities.Client;
import org.myProject.crm.exceptions.ResourceNotFoundException;
import org.myProject.crm.repositories.ClientRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClients() {
        clientService.getClients();
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testAddClient() {
        ClientInputDTO clientInputDTO = new ClientInputDTO();
        Client client = new Client();
        when(modelMapper.map(clientInputDTO, Client.class)).thenReturn(client);

        clientService.addClient(clientInputDTO);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testGetClientById_ClientExists() {
        int clientId = 1;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(clientId);

        assertEquals(client, result);
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void testGetClientById_ClientNotExists() {
        int clientId = 1;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientById(clientId));
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void testDeleteByFullName() {
        String fullName = "John Doe";
        clientService.deleteByFullName(fullName);
        verify(clientRepository, times(1)).deleteByFullName(fullName);
    }
}
