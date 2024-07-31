package api.kirana.register.stores.service;

import api.kirana.register.stores.entity.Stores;
import api.kirana.register.stores.models.StoresDTO;
import api.kirana.register.stores.repo.StoresDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StoresServiceImpl implements StoresService {

    private final StoresDAO storeDAO;

    @Autowired
    public StoresServiceImpl(StoresDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    /**
     * Retrieves a list of all stores.
     *
     * @return a list of Store entities
     */
    @Override
    public Page<Stores> getAllStore(Pageable pageable) {
        return storeDAO.getAllStores(pageable);
    }

    /**
     * Retrieves a store by its ID.
     *
     * @param id the ID of the store to retrieve
     * @return an Optional containing the store if found, or empty if not found
     */
    @Override
    public Optional<Stores> getStoreById(String id) {
        return storeDAO.getStoreById(id);
    }

    /**
     * Saves a store.
     *
     * @param storeRequest the StoreDTO containing store information to save
     * @return the saved Store entity
     */
    @Override
    public Stores saveStore(StoresDTO storeRequest) {
        Stores store = new Stores();
        store.setName(storeRequest.getName());
        store.setPhoneNumber(storeRequest.getPhoneNumber());
        store.setAddress(store.getAddress());
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
        return id;
    }
}
