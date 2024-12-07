package com.fort.service;

import com.fort.conf.TooManyLoginAttempts;
import com.fort.model.dto.ActionDTO;
import com.fort.model.dto.ClientDTO;
import com.fort.model.entities.Action;
import com.fort.model.entities.Client;
import com.fort.model.entities.FailedLogIn;
import com.fort.model.repositories.ActionRepository;
import com.fort.model.repositories.ClientRepository;
import com.fort.model.repositories.FailedLogInRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    ClientRepository clientRepository;
    ActionRepository actionRepository;
    ActionService actionService;
    FailedLogInRepository failedLogInRepository;

    public ClientService(
            ClientRepository clientRepository,
            ActionRepository actionRepository,
            ActionService actionService,
            FailedLogInRepository failedLogInRepository) {
        this.clientRepository = clientRepository;
        this.actionRepository = actionRepository;
        this.actionService = actionService;
        this.failedLogInRepository = failedLogInRepository;
    }

    public void createAndSave(String name, String password) {
        if (name == null || password == null ||
            name.length() < 3 || password.length() < 3 ||
            name.length() > 25 || password.length() > 25) {
            System.out.println("Invalid credentials");
            throw new IllegalArgumentException("Invalid credentials");
        }
        if (clientRepository.findFirstByName(name).isPresent()) {
            System.out.println("Client with this name already exists");
            throw new RuntimeException("Client with this name already exists");
        }
        Client client = new Client();
        client.name = name;
        client.password = password;
        clientRepository.save(client);
    }

    public ClientDTO getClient(String name, String password) throws RuntimeException {
        Client client = getClientAndVerify(name, password);
        List<ActionDTO> actionDTOS = actionService.getActions(client.getId());
        ClientDTO clientDTO = new ClientDTO(client.name, client.password, actionDTOS);
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

    public ClientDTO addAction(String name, String password, int delay, int increase) {
        Client client = getClientAndVerify(name, password);
        Action action = new Action();
        action.timestamp = System.currentTimeMillis();
        action.client = client;
        action.delay = delay;
        if (delay < 0 || Math.abs(increase) > 2000 || Math.abs(delay) > 2000) {
            System.out.println("Invalid delay");
            throw new RuntimeException("Invalid delay");
        }
        action.increase = increase;
        System.out.println("Add action for client: " + name);
        actionRepository.save(action);
        System.out.println("Action added");
        return getClient(name, password);
    }

    private Client getClientAndVerify(String name, String password) {
        try {
            if (!allowedToLogin(name)) {
                System.out.println("Too many failed logins");
                saveFailedLoginAttempt(name);
                throw new TooManyLoginAttempts("Too many failed logins");
            }
            System.out.println("Get client: " + name);
            Client client = clientRepository.findFirstByNameAndPassword(name, password).orElseThrow();
            System.out.println("Client verified");
            return client;
        } catch (NoSuchElementException e) {
            System.out.println("Client with these credentials not found");
            saveFailedLoginAttempt(name);
            throw new NoSuchElementException("Invalid credentials");
        }
    }

    private void saveFailedLoginAttempt(String client) {
        FailedLogIn failedLogIn = new FailedLogIn();
        failedLogIn.clientName = client;
        failedLogIn.timestamp = System.currentTimeMillis();
        failedLogInRepository.save(failedLogIn);

        // remove old failed login attempts
        List<FailedLogIn> oldLogins = failedLogInRepository.findAllByClientNameAndTimestampBefore(client, System.currentTimeMillis() - 120000);
        failedLogInRepository.deleteAll(oldLogins);
    }

    private boolean allowedToLogin(String client) {
        return failedLogInRepository.findAllByClientNameAndTimestampAfter(
                client, System.currentTimeMillis() - 60000).size() < 3;
    }
}
