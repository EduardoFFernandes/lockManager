package brisa.lockmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Version;

@Repository
public interface VersionRepository extends BaseRepository<Version, Long> {

    @Override
    List<Version> findAll();

}