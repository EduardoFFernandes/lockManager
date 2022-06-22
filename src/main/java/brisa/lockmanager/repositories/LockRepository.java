package brisa.lockmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Lock;

@Repository
public interface LockRepository extends BaseRepository<Lock, Long> {

    Lock findFirstBySerialNumber(String serialNumber);

    boolean existsBySerialNumberIgnoreCase(String serialNumber);

    Lock findFirstByOrderByIdAsc();

    @Override
    List<Lock> findAll();

}
