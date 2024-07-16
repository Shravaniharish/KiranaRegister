package api.kirana.register.customer.service;

import api.kirana.register.customer.models.Customer;
import api.kirana.register.customer.models.CustomerDTO;
import api.kirana.register.customer.repo.CustomerDAO;
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
public class CustomerServiceTest {


    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void getAllCustomersShouldReturnSuccess() {
        Customer customer = new Customer();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        Mockito.when(customerDAO.getAllCustomers()).thenReturn(customerList);
        List<Customer> actualList = customerService.getAllCustomers();
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(customerList, actualList);
    }

    @Test
    public void getCustomerByIdShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerId("test_id");
        Optional<Customer> expectedCustomer = Optional.of(customer);
        Mockito.when(customerDAO.getCustomerById("test_id")).thenReturn(expectedCustomer);
        Optional<Customer> actualCustomer = customerService.getCustomerById("test_id");
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertTrue(actualCustomer.isPresent());
        Assertions.assertEquals(expectedCustomer.get(), actualCustomer.get());
    }

    @Test
    public void getCustomerByNameShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerName("test_name");
        Optional<Customer> expectedCustomer = Optional.of(customer);
        Mockito.when(customerDAO.getCustomerByName("test_name")).thenReturn(expectedCustomer);
        Optional<Customer> actualCustomer = customerService.getCustomerByName("test_name");
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer.get(), actualCustomer.get());
    }

    @Test
    public void saveCustomerShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerName("test_name");
        customer.setEmail("test_email");
        customer.setStoreName("test_store");
        customer.setPhoneNumber("test_phone");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("test_name");
        customerDTO.setEmail("test_email");
        customerDTO.setStoreName("test_store");
        customerDTO.setPhoneNumber("test_phone");

        Mockito.when(customerDAO.saveCustomer(Mockito.any(Customer.class))).thenReturn(customer);
        Customer actualCustomer = customerService.saveCustomer(customerDTO);
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(customer.getCustomerName(), actualCustomer.getCustomerName());
        Assertions.assertEquals(customer.getEmail(), actualCustomer.getEmail());
    }

    @Test
    public void deleteCustomerShouldReturnSuccess() {
        String customerId = "test_id";
        Mockito.doNothing().when(customerDAO).deleteCustomer(customerId);
        String response = customerService.deleteCustomer(customerId);
        Assertions.assertEquals("customer is deleted", response);
        Mockito.verify(customerDAO, Mockito.times(1)).deleteCustomer(customerId);
    }
}
