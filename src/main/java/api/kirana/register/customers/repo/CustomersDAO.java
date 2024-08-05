package api.kirana.register.customers.repo;

import api.kirana.register.customers.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CustomersDAO {
    private final CustomersRepo customerRepo;

    @Autowired
    public CustomersDAO(CustomersRepo customerRepo) {
        this.customerRepo = customerRepo;

    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     */

    public Page<Customers> getAllCustomers(Pageable pageable) {
        return customerRepo.findAll(pageable);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    public Optional<Customers> getCustomerById(String id) {
        return customerRepo.findById(id);

    }


    /**
     * Retrieves a customer by their name.
     *
     * @param customerName the name of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    public Optional<Customers> getCustomerByName(String customerName) {
        return customerRepo.findByName(customerName);

    }


    /**
     * Saves a new customer.
     *
     * @param customerRequest the customer to be saved
     * @return the saved customer
     */
    public Customers saveCustomer(Customers customerRequest) {
       return customerRepo.save(customerRequest);

    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to be deleted
     */
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }
}
