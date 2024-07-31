package api.kirana.register.customers.service;

import api.kirana.register.customers.entity.Customers;
import api.kirana.register.customers.models.CustomersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomersService {
    Page<Customers> getAllCustomers(Pageable pageable);

    Optional<Customers> getCustomerById(String id);

    Optional<Customers> getCustomerByName (String customerName);

    Customers saveCustomer (CustomersDTO customer);

    String deleteCustomer(String id);

}
