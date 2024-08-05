package api.kirana.register.stores.service;

import api.kirana.register.stores.entity.Stores;
import api.kirana.register.stores.models.StoresDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoresService {

    Page<Stores> getAllStore(Pageable pageable);

    Optional<Stores> getStoreById(String id);

    Stores saveStore(StoresDTO store);

    String deleteStore(String id);
}
