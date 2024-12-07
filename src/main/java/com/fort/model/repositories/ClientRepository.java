package com.fort.model.repositories;


import com.fort.model.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findFirstByNameAndPassword(String name, String password);

    Optional<Client> findFirstByName(String name);
}
