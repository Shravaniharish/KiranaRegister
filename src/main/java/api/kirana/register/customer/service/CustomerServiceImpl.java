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


    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public Optional<Customer> getCustomerByName(String customerName) {
        return customerDAO.getCustomerByName(customerName);
    }

    @Override
    public Customer saveCustomer(CustomerDTO customerRequest) {
        Customer customer = new Customer();
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setEmail(customerRequest.getEmail());
        customer.setStoreName(customerRequest.getStoreName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        return customerDAO.saveCustomer(customer);
    }

    @Override
    public String deleteCustomer(String id) {
       customerDAO.deleteCustomer(id);
       return "customer is deleted";
    }
}
