package com.fort.model.repositories;


import com.fort.model.entities.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionRepository extends CrudRepository<Action, Long> {

    List<Action> findByClientId(Long clientId);
}
