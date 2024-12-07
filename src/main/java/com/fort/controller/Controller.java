package com.fort.controller;

import com.fort.conf.TooManyLoginAttempts;
import com.fort.model.dto.AddActionDTO;
import com.fort.model.dto.ClientDTO;
import com.fort.model.dto.CreateClientDTO;
import com.fort.model.dto.GetClientDTO;
import com.fort.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@RestController
public class Controller {

    ClientService clientService;

    public Controller(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "{\"response\": \"Hello, World!\"}";
    }

    /**
     * Create a new client with a name and password. name has to be unique
     * @param client CreateClientDTO
     * @return ClientDTO (client name, password)
     */
    @PostMapping("/client")
    public ResponseEntity<String> create(@RequestBody CreateClientDTO client) {
        try {
            clientService.createAndSave(client.name, client.password);
            return ResponseEntity.ok().body("Success!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid credentials. Name and password must be between 3 and 25 characters long.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Client with this name already exists");
        }
    }

    /**
     * Get client and their counter via a valid id and password
     * @param client GetClientDTO
     * @return ClientDTO
     */
    @PostMapping("/counter")
    public ResponseEntity<ClientDTO> getClient(@RequestBody GetClientDTO client) {
        try {
            ClientDTO clientDTO = clientService.getClient(client.name, client.password);
            return ResponseEntity.ok().body(clientDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (TooManyLoginAttempts e) {
            return ResponseEntity.status(429).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Add an action to a client
     * @param action AddActionDTO
     * @return ClientDTO (client id, password, actions, counter)
     */
    @PostMapping("/action")
    public ResponseEntity<ClientDTO> addAction(@RequestBody AddActionDTO action) {
        try {
            ClientDTO clientDTO = clientService.addAction(action.clientName,
                    action.password, action.delay, action.increase);
            return ResponseEntity.ok().body(clientDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (TooManyLoginAttempts e) {
            return ResponseEntity.status(429).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
