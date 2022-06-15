package brisa.lockmanager.repositories;


import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Warehouse;


@Repository
public interface WarehouseRepository extends BaseRepository<Warehouse, Long> {

    @Override
    List<Warehouse> findAll();

}
