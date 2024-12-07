package com.fort.model.repositories;


import com.fort.model.entities.Client;
import com.fort.model.entities.FailedLogIn;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FailedLogInRepository extends CrudRepository<FailedLogIn, Long> {

    List<FailedLogIn> findAllByClientNameAndTimestampAfter(String client, long timestamp);

    List<FailedLogIn> findAllByClientNameAndTimestampBefore(String client, long timestamp);
}
