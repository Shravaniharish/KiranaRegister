package api.kirana.register.customers.controller;

import api.kirana.register.customers.controllers.CustomersController;
import api.kirana.register.customers.entity.Customers;
import api.kirana.register.customers.models.CustomersDTO;
import api.kirana.register.customers.service.CustomersServiceImpl;
import api.kirana.register.response.ApiResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Mock private CustomersServiceImpl customerService;

    @InjectMocks private CustomersController customerController;

    @Test
    public void getAllCustomersShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Customers> customers =
                Arrays.asList(new Customers(), new Customers(), new Customers(), new Customers());
        Page<Customers> customerList = new PageImpl<>(customers);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(customerService.getAllCustomers(pageable)).thenReturn(customerList);
        ResponseEntity<ApiResponse> responseEntity = customerController.getAllCustomers(page, size);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(customerList, responseEntity.getBody().getData());
    }

    @Test
    public void getCustomerShouldReturnSuccess() {
        Customers customer = new Customers();
        customer.setId("test");
        customer.setName("xyz");
        Optional<Customers> expectedCustomer = Optional.of(customer);
        Mockito.when(customerService.getCustomerById(customer.getId()))
                .thenReturn(expectedCustomer);
        ResponseEntity<ApiResponse> responseEntity =
                customerController.getCustomer(customer.getId(), customer.getName());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedCustomer, responseEntity.getBody().getData());
    }

    @Test
    public void saveCustomerShouldReturnSuccess() {
        CustomersDTO customerDTO = new CustomersDTO();
        customerDTO.setName("xyz");
        Customers customer = new Customers();
        customer.setName("xyz");
        Mockito.when(customerService.saveCustomer(Mockito.any(CustomersDTO.class)))
                .thenReturn(customer);
        ResponseEntity<ApiResponse> responseEntity = customerController.saveCustomer(customerDTO);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(customer, responseEntity.getBody().getData());
    }

    @Test
    public void deleteCustomerShouldReturnSuccess() {
        String customerId = "test_id";
        Mockito.when(customerService.deleteCustomer(customerId)).thenReturn("test_id");
        ResponseEntity<ApiResponse> responseEntity = customerController.deleteCustomer(customerId);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
