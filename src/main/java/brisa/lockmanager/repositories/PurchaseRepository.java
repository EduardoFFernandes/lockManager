package brisa.lockmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Purchase;

@Repository
public interface PurchaseRepository extends BaseRepository<Purchase, Long> {

    Purchase findFirstByOrderByIdAsc();

    @Override
    List<Purchase> findAll();

}
