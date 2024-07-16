package api.kirana.register.customer.repo;

import api.kirana.register.customer.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerDAOTest {
    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerDAO customerDAO;

    @Test
    public void getAllCustomersShouldReturnSuccess() {

        List<Customer> customerList= new ArrayList<>();
        Mockito.when(customerRepo.findAll()).thenReturn(customerList);
        List<Customer> actualList = customerDAO.getAllCustomers();
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(customerList, actualList);
    }

    @Test
    public void getCustomerByIdShouldReturnSuccess(){
        Customer customer = new Customer();
        customer.setCustomerId("test");
        Optional<Customer> expectedCustomer = Optional.of(customer);
        Mockito.when(customerRepo.findById(customer.getCustomerId())).thenReturn(expectedCustomer);
        Optional<Customer> actualCustomer = customerDAO.getCustomerById(customer.getCustomerId());
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer, actualCustomer);

    }

    @Test
    public void getCustomerByNameShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerName("abc");
        Optional<Customer> expectedCustomer = Optional.of(customer);
        Mockito.when(customerRepo.findByCustomerName(customer.getCustomerName())).thenReturn(expectedCustomer);
        Optional<Customer> actualCustomer = customerDAO.getCustomerByName(customer.getCustomerName());
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void saveCustomerShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerName("abc");
        customer.setEmail("abc@xyz.in");
        customer.setPhoneNumber("0987654321");
        customer.setStoreName("pqr");
        Mockito.when(customerRepo.save(customer)).thenReturn(customer);
        Customer actualCustomer = customerDAO.saveCustomer(customer);
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(customer.getCustomerName(), actualCustomer.getCustomerName());
    }

    @Test
    public void deleteCustomerShouldReturnSuccess() {
        String id = "test";
        Mockito.doNothing().when(customerRepo).deleteById(id);
        customerDAO.deleteCustomer(id);
        Mockito.verify(customerRepo,Mockito.times(1)).deleteById(id);
    }

}
