package api.kirana.register.customer.service;

import api.kirana.register.customer.models.Customer;
import api.kirana.register.customer.models.CustomerDTO;
import api.kirana.register.customer.repo.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerDAO customerDAO;
    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDOA) {
        this.customerDAO = customerDOA;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerDAO.getCustomerById(id);
    }


    /**
     * Retrieves a customer by their name.
     *
     * @param customerName the name of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    @Override
    public Optional<Customer> getCustomerByName(String customerName) {
        return customerDAO.getCustomerByName(customerName);
    }


    /**
     * Saves a new customer.
     *
     * @param customerRequest the customer data transfer object
     * @return the saved customer
     */
    @Override
    public Customer saveCustomer(CustomerDTO customerRequest) {
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setEmail(customerRequest.getEmail());
        customer.setStoreName(customerRequest.getStoreName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        return customerDAO.saveCustomer(customer);
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to be deleted
     * @return a message indicating the result of the delete operation
     */
    @Override
    public String deleteCustomer(String id) {
       customerDAO.deleteCustomer(id);
       return "customer is deleted";
    }
}
