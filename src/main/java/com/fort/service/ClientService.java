package com.fort.service;

import com.fort.model.dto.ActionDTO;
import com.fort.model.dto.ClientDTO;
import com.fort.model.entities.Action;
import com.fort.model.entities.Client;
import com.fort.model.repositories.ActionRepository;
import com.fort.model.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    ClientRepository clientRepository;
    ActionRepository actionRepository;
    ActionService actionService;

    public ClientService(ClientRepository clientRepository, ActionRepository actionRepository, ActionService actionService) {
        this.clientRepository = clientRepository;
        this.actionRepository = actionRepository;
        this.actionService = actionService;
    }

    public ClientDTO createAndSave(String password) {
        Client client = new Client();
        client.password = password;
        clientRepository.save(client);
        List<ActionDTO> actionDTOS = actionService.getActions(client.getId());
        return new ClientDTO(client.getId(), client.password, actionDTOS);
    }

    public ClientDTO getClient(long id, String password) throws RuntimeException {
        Client client = getClientAndVerify(id, password);
        List<ActionDTO> actionDTOS = actionService.getActions(client.getId());
        ClientDTO clientDTO = new ClientDTO(client.getId(), client.password, actionDTOS);
        clientDTO.setCounter(calculateCounter(actionDTOS));
        return clientDTO;
    }

    private int calculateCounter(List<ActionDTO> actionDTOS) {
        int counter = 0;
        for (ActionDTO actionDTO : actionDTOS) {
            if (System.currentTimeMillis() > (actionDTO.timestamp + actionDTO.delay * 1000L)) {
                counter += actionDTO.increase;
            }
        }
        return counter;
    }

    public ClientDTO addAction(long clientId, String password, int delay, int increase) {
        Client client = getClientAndVerify(clientId, password);
        Action action = new Action();
        action.timestamp = System.currentTimeMillis();
        action.client = client;
        action.delay = delay;
        if (delay < 0) {
            System.out.println("Invalid delay");
            throw new RuntimeException("Invalid delay");
        }
        action.increase = increase;
        System.out.println("Add action for client: " + clientId);
        actionRepository.save(action);
        System.out.println("Action added");
        return getClient(clientId, password);
    }

    private Client getClientAndVerify(long id, String password) throws RuntimeException {
        Client client = clientRepository.findById(id).orElseThrow();
        System.out.println("Get client: " + client.getId());
        if (!client.password.equals(password)) {
            System.out.println("Invalid password");
            throw new RuntimeException("Invalid password");
        }
        System.out.println("Client verified");
        return client;
    }
}
