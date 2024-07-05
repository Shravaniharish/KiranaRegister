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

    public List<Customer> getAllCustomers() {
        List<Customer> customerList= new ArrayList<>();
        customerList = customerRepo.findAll();
        return customerList;

    }


    public Optional<Customer> getCustomerById(String id) {
        Optional<Customer> customer;
        customer = customerRepo.findById(id);
        return customer;

    }


    public Optional<Customer> getCustomerByName(String customerName) {
        Optional<Customer> customer;
        customer= customerRepo.findByCustomerName(customerName);
        return customer;
    }


    public Customer saveCustomer(Customer customerRequest) {
       return customerRepo.save(customerRequest);

    }


    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }
}
