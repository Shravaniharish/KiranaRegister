package api.kirana.register.customers.service;

import api.kirana.register.customers.entity.Customers;
import api.kirana.register.customers.models.CustomersDTO;
import api.kirana.register.customers.repo.CustomersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomersServiceImpl implements CustomersService {
    private final CustomersDAO customerDAO;
    @Autowired
    public CustomersServiceImpl(CustomersDAO customerDOA) {
        this.customerDAO = customerDOA;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers
     */
    @Override
    public Page<Customers> getAllCustomers(Pageable pageable) {
        return customerDAO.getAllCustomers(pageable);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    @Override
    public Optional<Customers> getCustomerById(String id) {
        return customerDAO.getCustomerById(id);
    }


    /**
     * Retrieves a customer by their name.
     *
     * @param customerName the name of the customer
     * @return an Optional containing the customer if found, or empty if not found
     */
    @Override
    public Optional<Customers> getCustomerByName(String customerName) {
        return customerDAO.getCustomerByName(customerName);
    }


    /**
     * Saves a new customer.
     *
     * @param customerRequest the customer data transfer object
     * @return the saved customer
     */
    @Override
    public Customers saveCustomer(CustomersDTO customerRequest) {
        Customers customer = new Customers();
        customer.setName(customerRequest.getName());
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
       return id;
    }
}
