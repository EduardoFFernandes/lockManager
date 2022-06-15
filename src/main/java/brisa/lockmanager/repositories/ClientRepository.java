package brisa.lockmanager.repositories;


import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Client;


@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {

    @Override
    List<Client> findAll();


}
