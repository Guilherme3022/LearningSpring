package org.myProject.crm.repositories;

import org.myProject.crm.entities.Client;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findByFullName(String fullName);
    void deleteByFullName(String fullName);
}
