package api.kirana.register.customer.service;

import api.kirana.register.customer.models.Customer;
import api.kirana.register.customer.models.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(String id);

    Optional<Customer> getCustomerByName (String customerName);

    Customer saveCustomer (CustomerDTO customer);

    String deleteCustomer(String id);

}
