package api.kirana.register.store.repo;

import api.kirana.register.store.models.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StoreDAO{

    public final StoreRepository storeRepository;

    @Autowired
    public StoreDAO(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return a list of Store entities
     */
    public List<Store> getAllStore() {
        return storeRepository.findAll() ;
    }


    /**
     * Retrieves a store by its ID.
     *
     * @param id the ID of the store to retrieve
     * @return an Optional containing the store if found, or empty if not found
     */
    public Optional<Store> getStoreById(String id) {
        return storeRepository.findById(id);
    }


    /**
     * Saves a store.
     *
     * @param storeRequest the Store entity to save
     * @return the saved Store entity
     */
    public Store saveStore(Store storeRequest) {

        return storeRepository.save(storeRequest);

    }


    /**
     * Deletes a store by its ID.
     *
     * @param id the ID of the store to delete
     */
    public void deleteStore(String id) {
        storeRepository.deleteById(id);

    }
}
