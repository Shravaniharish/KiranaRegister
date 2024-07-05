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

    @Override
    public List<Store> getAllStore() {
        return storeDAO.getAllStore();
    }

    @Override
    public Optional<Store> getStoreById(String id) {
        return storeDAO.getStoreById(id);
    }

    @Override
    public Store saveStore(StoreDTO storeRequest) {
        Store store = new Store();
        store.setStoreName(storeRequest.getStoreName());
        store.setStoreContactNo(storeRequest.getStoreContactNo());
        store.setStoreAddress(store.getStoreAddress());
        return storeDAO.saveStore(store);
    }

    @Override
    public String deleteStore(String id) {
        storeDAO.deleteStore(id);
        return "Store is deleted";
    }
}
