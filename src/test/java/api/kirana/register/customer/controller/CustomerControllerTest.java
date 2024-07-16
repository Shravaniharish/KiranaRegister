package api.kirana.register.customer.controller;

import api.kirana.register.customer.controllers.CustomerController;
import api.kirana.register.customer.models.Customer;
import api.kirana.register.customer.models.CustomerDTO;
import api.kirana.register.customer.service.CustomerServiceImpl;
import api.kirana.register.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void getAllCustomersShouldReturnSuccess() {
        List<Customer> customerList = new ArrayList<>();
        Mockito.when(customerService.getAllCustomers()).thenReturn( customerList);
        ResponseEntity<Response> responseEntity = customerController.getAllCustomers();
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(customerList, responseEntity.getBody().getData());
        Assertions.assertEquals("list of all customers", responseEntity.getBody().getMessage());
    }

    @Test
    public void getCustomerByIdShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerId("test");
        Optional<Customer> expectedCustomer = Optional.of(customer);
        Mockito.when(customerService.getCustomerById(customer.getCustomerId())).thenReturn(expectedCustomer);
        ResponseEntity<Response> responseEntity = customerController.getCustomerById(customer.getCustomerId());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedCustomer, responseEntity.getBody().getData());

    }

    @Test
    public void getCustomerByNameShouldReturnSuccess() {
        Customer customer = new Customer();
        customer.setCustomerName("abc");
        Optional<Customer> expectedCustomer = Optional.of(customer);
        Mockito.when(customerService.getCustomerByName(customer.getCustomerName())).thenReturn(expectedCustomer);
        ResponseEntity<Response> responseEntity = customerController.getCustomerByName(customer.getCustomerName());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedCustomer, responseEntity.getBody().getData());
        Assertions.assertEquals("customer name match", responseEntity.getBody().getMessage());
    }

    @Test
    public void saveCustomerShouldReturnSuccess() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("xyz");
        Customer customer = new Customer();
        customer.setCustomerName("xyz");
        Mockito.when(customerService.saveCustomer(Mockito.any(CustomerDTO.class))).thenReturn(customer);
        ResponseEntity<Response> responseEntity = customerController.saveCustomer(customerDTO);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(customer, responseEntity.getBody().getData());
    }

    @Test
    public void deleteCustomerShouldReturnSuccess() {
        String customerId = "test_id";
        Mockito.when(customerService.deleteCustomer(customerId)).thenReturn("customer is deleted");
        ResponseEntity<Response> responseEntity = customerController.deleteCustomer(customerId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

