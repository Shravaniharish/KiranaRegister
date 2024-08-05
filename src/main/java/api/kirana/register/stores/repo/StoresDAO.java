package api.kirana.register.stores.repo;

import api.kirana.register.stores.entity.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StoresDAO {

    public final StoresRepository storeRepository;

    @Autowired
    public StoresDAO(StoresRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return a list of Store entities
     */
    public Page<Stores> getAllStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param id the ID of the store to retrieve
     * @return an Optional containing the store if found, or empty if not found
     */
    public Optional<Stores> getStoreById(String id) {
        return storeRepository.findById(id);
    }

    /**
     * Saves a store.
     *
     * @param storeRequest the Store entity to save
     * @return the saved Store entity
     */
    public Stores saveStore(Stores storeRequest) {

        return storeRepository.save(storeRequest);
    }

    /**
     * Deletes a store by its ID.
     *
     * @param id the ID of the store to delete
     */
    public String deleteStore(String id) {
        storeRepository.deleteById(id);
        return id;
    }
}
