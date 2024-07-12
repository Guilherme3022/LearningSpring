package org.myProject.crm.rest;

import jakarta.validation.Valid;
import org.myProject.crm.dtos.ClientInputDTO;
import org.myProject.crm.entities.Client;
import org.myProject.crm.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") int idClient) {
            return ResponseEntity.ok(clientService.getClientById(idClient));
    }

    @GetMapping
    public ResponseEntity<Iterable<Client>> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PostMapping
    public ResponseEntity<Void> addClient(@Valid @RequestBody ClientInputDTO client) {
        this.clientService.addClient(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{fullName}")
    public ResponseEntity<Void> deleteClient(@PathVariable("fullName") String fullName) {
        this.clientService.deleteByFullName(fullName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
