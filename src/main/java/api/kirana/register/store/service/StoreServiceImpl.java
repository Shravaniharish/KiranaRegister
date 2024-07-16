package api.kirana.register.store.service;

import api.kirana.register.store.models.Store;
import api.kirana.register.store.models.StoreDTO;
import api.kirana.register.store.repo.StoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreDAO storeDAO;

    @Autowired
    public StoreServiceImpl(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return a list of Store entities
     */
    @Override
    public List<Store> getAllStore() {
        return storeDAO.getAllStore();
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param id the ID of the store to retrieve
     * @return an Optional containing the store if found, or empty if not found
     */
    @Override
    public Optional<Store> getStoreById(String id) {
        return storeDAO.getStoreById(id);
    }

    /**
     * Saves a store.
     *
     * @param storeRequest the StoreDTO containing store information to save
     * @return the saved Store entity
     */
    @Override
    public Store saveStore(StoreDTO storeRequest) {
        Store store = new Store();
        store.setStoreName(storeRequest.getStoreName());
        store.setStoreContactNo(storeRequest.getStoreContactNo());
        store.setStoreAddress(store.getStoreAddress());
        return storeDAO.saveStore(store);
    }

    /**
     * Deletes a store by its ID.
     *
     * @param id the ID of the store to delete
     * @return a message indicating the deletion status
     */
    @Override
    public String deleteStore(String id) {
        storeDAO.deleteStore(id);
        return "Store is deleted";
    }
}
