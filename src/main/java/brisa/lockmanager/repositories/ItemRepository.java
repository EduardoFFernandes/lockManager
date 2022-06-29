package brisa.lockmanager.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import brisa.lockmanager.models.Item;

@Repository
public interface ItemRepository extends BaseRepository<Item, Long> {

    @Override
    List<Item> findAll();

    List<Item> findAllByPurchaseIsNull();
    
    List<Item> findAllByPurchaseId(Long purchaseId);

}
