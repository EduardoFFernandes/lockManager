package brisa.lockmanager.configurations;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brisa.lockmanager.commons.utils.DateUtil;
import brisa.lockmanager.models.Client;
import brisa.lockmanager.models.Item;
import brisa.lockmanager.models.Lock;
import brisa.lockmanager.models.LockModel;
import brisa.lockmanager.models.Purchase;
import brisa.lockmanager.models.Warehouse;
import brisa.lockmanager.models._BaseModel.ItemStatus;
import brisa.lockmanager.repositories.ClientRepository;
import brisa.lockmanager.repositories.ItemRepository;
import brisa.lockmanager.repositories.LockModelRepository;
import brisa.lockmanager.repositories.LockRepository;
import brisa.lockmanager.repositories.PurchaseRepository;
import brisa.lockmanager.repositories.WarehouseRepository;

@Configuration
public class StartupConfig {

    // private static final long DEFAULT_ID = NumberUtils.LONG_ZERO;
    private static final String DEFAULT_SERIAL_NUMBER = "00000";
    private static final String DEFAULT_MESSAGE_STRING = "INFORMACAO TESTE";

    @Bean
    public CommandLineRunner initData(
            final LockModelRepository lockModelRepository,
            final ClientRepository clientRepository,
            final WarehouseRepository warehouseRepository,
            final LockRepository lockRepository,
            final PurchaseRepository purchaseRepository,
            final ItemRepository itemRepository) {

        return runner -> {

            insertLockModels(lockModelRepository);
            insertClients(clientRepository);
            insertWarehouses(warehouseRepository);
            insertLocks(lockRepository);
            insertPurchases(purchaseRepository, clientRepository);
            insertItems(itemRepository, lockRepository, purchaseRepository);
        };
    }

    private static void insertLockModels(final LockModelRepository repository) {

        if (repository.count() == 0) {
            final LockModel object = new LockModel();

            object.setName(DEFAULT_MESSAGE_STRING);

            repository.save(object);

        }
    }

    private static void insertClients(final ClientRepository repository) {

        if (repository.count() == 0) {
            final Client object = new Client();

            final Timestamp now = DateUtil.getCurrentTimestamp();

            object.setName(DEFAULT_MESSAGE_STRING);
            object.setCellphone("6198147-3090");
            object.setEmail(DEFAULT_MESSAGE_STRING);
            object.setIdentifier(DEFAULT_MESSAGE_STRING);
            object.setRegistryDate(now);
            object.setAddress(DEFAULT_MESSAGE_STRING);

            repository.save(object);

        }
    }

    private static void insertWarehouses(final WarehouseRepository repository) {

        if (repository.count() == 0) {
            final Warehouse object = new Warehouse();

            final Timestamp now = DateUtil.getCurrentTimestamp();

            object.setName(DEFAULT_MESSAGE_STRING);
            object.setAddress(DEFAULT_MESSAGE_STRING);
            object.setRegistryDate(now);
            object.setUpdateDate(now);
            repository.save(object);

        }
    }

    private static void insertLocks(final LockRepository repository) {

        if (repository.count() == 0) {
            final Lock object = new Lock();

            final Timestamp now = DateUtil.getCurrentTimestamp();

            object.setRegistryDate(now);
            object.setSerialNumber(DEFAULT_SERIAL_NUMBER);
            object.setFirmwareVersion("0.0.0.00");
            // object.setClient(client);
            // object.setWarehouse(warehouse);
            // object.setModel(model);

            repository.save(object);

        }
    }

    private static void insertPurchases(PurchaseRepository repository, ClientRepository clientRepository) {

        if (repository.count() == 0) {

            final Purchase object = new Purchase();

            final Timestamp now = DateUtil.getCurrentTimestamp();

            object.setRegistryDate(now);
            object.setDueDate(now);
            object.setPurchaseDate(now);
            object.setClient(clientRepository.findFirstByOrderByIdAsc());
            // object.setClient(client);
            // object.setWarehouse(warehouse);
            // object.setModel(model);

            repository.save(object);

        }
    }

    private static void insertItems(final ItemRepository repository, LockRepository lockRepository,
            PurchaseRepository purchaseRepository) {

        if (repository.count() == 0) {

            final Item object = new Item();

            final Timestamp now = DateUtil.getCurrentTimestamp();

            object.setRegistryDate(now);
            object.setInstallationLocation(DEFAULT_MESSAGE_STRING);
            object.setStatus(ItemStatus.OFF);
            object.setPurchase(purchaseRepository.findFirstByOrderByIdAsc());
            object.setPrice(BigDecimal.TEN);
            object.setLock(lockRepository.findFirstByOrderByIdAsc());
            object.setSensor(true);
            // object.setClient(client);
            // object.setWarehouse(warehouse);
            // object.setModel(model);

            repository.save(object);

        }
    }
}
