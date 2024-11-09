package com.fort.controller;

import com.fort.model.dto.AddActionDTO;
import com.fort.model.dto.ClientDTO;
import com.fort.model.dto.CreateClientDTO;
import com.fort.model.dto.GetClientDTO;
import com.fort.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@CrossOrigin(
        origins = {"http://localhost:4200"},
        allowCredentials = "true",
        allowedHeaders = {"X-Custom-Header"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
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

    @PostMapping("/client")
    public ResponseEntity<ClientDTO> create(@RequestBody CreateClientDTO client) {
        ClientDTO clientDTO = clientService.createAndSave(client.password);
        return ResponseEntity.ok().body(clientDTO);
    }

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
