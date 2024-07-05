package api.kirana.register.store.service;

import api.kirana.register.store.models.Store;
import api.kirana.register.store.models.StoreDTO;

import java.util.List;
import java.util.Optional;

public interface StoreService {

    List<Store> getAllStore ();

    Optional<Store> getStoreById(String id);

    Store saveStore(StoreDTO store);

    String deleteStore(String id);
}
