package brisa.lockmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.LockModel;

@Repository
public interface LockModelRepository extends BaseRepository<LockModel, Long> {

    @Override
    List<LockModel> findAll();

}
