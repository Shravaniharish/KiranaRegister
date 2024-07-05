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

    public List<Store> getAllStore() {
        return storeRepository.findAll() ;
    }

    public Optional<Store> getStoreById(String id) {
        return storeRepository.findById(id);
    }

    public Store saveStore(Store storeRequest) {

        return storeRepository.save(storeRequest);

    }

    public void deleteStore(String id) {
        storeRepository.deleteById(id);

    }
}
