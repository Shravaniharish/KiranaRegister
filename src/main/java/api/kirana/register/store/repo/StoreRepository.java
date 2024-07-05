package api.kirana.register.store.repo;

import api.kirana.register.store.models.Store;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StoreRepository extends MongoRepository<Store , String> {

}
