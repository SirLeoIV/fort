package com.fort.controller;

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
     * Create a new client with a password
     * @param client CreateClientDTO (basically only a password)
     * @return ClientDTO (client id, password)
     */
    @PostMapping("/client")
    public ResponseEntity<ClientDTO> create(@RequestBody CreateClientDTO client) {
        ClientDTO clientDTO = clientService.createAndSave(client.password);
        return ResponseEntity.ok().body(clientDTO);
    }

    /**
     * Get client and their counter via a valid id and password
     * @param client GetClientDTO
     * @return ClientDTO
     */
    @PostMapping("/counter")
    public ResponseEntity<ClientDTO> getClient(@RequestBody GetClientDTO client) {
        try {
            ClientDTO clientDTO = clientService.getClient(client.id, client.password);
            return ResponseEntity.ok().body(clientDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
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
            ClientDTO clientDTO = clientService.addAction(action.clientID,
                    action.password, action.delay, action.increase);
            return ResponseEntity.ok().body(clientDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
