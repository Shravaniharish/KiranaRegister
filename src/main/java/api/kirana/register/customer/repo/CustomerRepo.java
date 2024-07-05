package api.kirana.register.customer.repo;

import api.kirana.register.customer.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepo extends MongoRepository<Customer, String> {

    Optional<Customer> findByCustomerName(String Name);
}

