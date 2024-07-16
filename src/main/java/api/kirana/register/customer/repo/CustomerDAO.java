package api.kirana.register.customer.repo;
import api.kirana.register.customer.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class CustomerDAO  {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerDAO(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;

    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     */
    public List<Customer> getAllCustomers() {
        List<Customer> customerList= new ArrayList<>();
        customerList = customerRepo.findAll();
        return customerList;

    }


    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    public Optional<Customer> getCustomerById(String id) {
        Optional<Customer> customer;
        customer = customerRepo.findById(id);
        return customer;

    }


    /**
     * Retrieves a customer by their name.
     *
     * @param customerName the name of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    public Optional<Customer> getCustomerByName(String customerName) {
        Optional<Customer> customer;
        customer= customerRepo.findByCustomerName(customerName);
        return customer;
    }


    /**
     * Saves a new customer.
     *
     * @param customerRequest the customer to be saved
     * @return the saved customer
     */
    public Customer saveCustomer(Customer customerRequest) {
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
