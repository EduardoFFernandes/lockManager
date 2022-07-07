package brisa.lockmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Lock;

@Repository
public interface LockRepository extends BaseRepository<Lock, Long> {

    Lock findFirstBySerialNumber(String serialNumber);

    boolean existsBySerialNumberIgnoreCase(String serialNumber);

    boolean existsByLockModelId(Long lockModelId);

    boolean existsByWarehouseId(Long warehouseId);

    boolean existsByVersionId(Long versionId);

    Lock findFirstByOrderByIdAsc();

    @Override
    List<Lock> findAll();

    List<Lock> findAllByVersionIdNot(Long versionId);

}
