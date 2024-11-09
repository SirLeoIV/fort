package com.fort.service;

import com.fort.model.dto.ActionDTO;
import com.fort.model.entities.Action;
import com.fort.model.repositories.ActionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public List<ActionDTO> getActions(long clientId) {
        List<Action> actions = actionRepository.findByClientId(clientId);
        return actions.stream()
                .map(action -> new ActionDTO(action.delay, action.increase, action.timestamp)).toList();
    }
}
