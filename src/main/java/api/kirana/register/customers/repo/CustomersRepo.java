package api.kirana.register.customers.repo;

import api.kirana.register.customers.entity.Customers;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomersRepo extends MongoRepository<Customers, String> {

    Optional<Customers> findByName(String Name);

    Page<Customers> findAll(Pageable pageable);
}
