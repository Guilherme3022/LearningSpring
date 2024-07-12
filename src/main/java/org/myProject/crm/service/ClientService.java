package org.myProject.crm.service;

import org.modelmapper.ModelMapper;
import org.myProject.crm.dtos.ClientInputDTO;
import org.myProject.crm.entities.Client;
import org.myProject.crm.exceptions.ResourceNotFoundException;
import org.myProject.crm.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    public Iterable<Client> getClients() {
        return this.clientRepository.findAll();
    }

    public void addClient(ClientInputDTO client) {
        Client clientToPersist = modelMapper.map(client, Client.class);
        this.clientRepository.save(clientToPersist);
    }

    public Client getClientById(int idClient) {
        Optional<Client> client = this.clientRepository.findById(idClient);
        if (client.isEmpty()){
            throw new ResourceNotFoundException("Client do not exist");
        }

        return client.get();
    }

    public void deleteByFullName(String fullName) {
        this.clientRepository.deleteByFullName(fullName);
    }
}
