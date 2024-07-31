package api.kirana.register.stores.repo;

import api.kirana.register.stores.entity.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StoresRepository extends MongoRepository<Stores, String> {

    Page<Stores> findAll (Pageable pageable);
}
