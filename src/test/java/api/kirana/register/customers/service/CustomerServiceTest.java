package api.kirana.register.customers.service;

import api.kirana.register.customers.entity.Customers;
import api.kirana.register.customers.models.CustomersDTO;
import api.kirana.register.customers.repo.CustomersDAO;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {


    @Mock
    private CustomersDAO customerDAO;

    @InjectMocks
    private CustomersServiceImpl customerService;

    @Test
    public void getAllCustomersShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Customers> customers = Arrays.asList(
                new Customers(), new Customers(), new Customers(), new Customers()
        );
        Page<Customers> customerList = new PageImpl<>(customers);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(customerDAO.getAllCustomers(pageable)).thenReturn(customerList);
        Page<Customers> actualList = customerService.getAllCustomers(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(customerList, actualList);
    }

    @Test
    public void getCustomerByIdShouldReturnSuccess() {
        Customers customer = new Customers();
        customer.setId("test_id");
        Optional<Customers> expectedCustomer = Optional.of(customer);
        Mockito.when(customerDAO.getCustomerById("test_id")).thenReturn(expectedCustomer);
        Optional<Customers> actualCustomer = customerService.getCustomerById("test_id");
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertTrue(actualCustomer.isPresent());
        Assertions.assertEquals(expectedCustomer.get(), actualCustomer.get());
    }

    @Test
    public void getCustomerByNameShouldReturnSuccess() {
        Customers customer = new Customers();
        customer.setName("test_name");
        Optional<Customers> expectedCustomer = Optional.of(customer);
        Mockito.when(customerDAO.getCustomerByName("test_name")).thenReturn(expectedCustomer);
        Optional<Customers> actualCustomer = customerService.getCustomerByName("test_name");
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer.get(), actualCustomer.get());
    }

    @Test
    public void saveCustomerShouldReturnSuccess() {
        Customers customer = new Customers();
        customer.setName("test_name");
        customer.setEmail("test_email");
        customer.setStoreName("test_store");
        customer.setPhoneNumber("test_phone");

        CustomersDTO customerDTO = new CustomersDTO();
        customerDTO.setName("test_name");
        customerDTO.setEmail("test_email");
        customerDTO.setStoreName("test_store");
        customerDTO.setPhoneNumber("test_phone");
        Mockito.when(customerDAO.saveCustomer(Mockito.any(Customers.class))).thenReturn(customer);
        Customers actualCustomer = customerService.saveCustomer(customerDTO);
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(customer.getName(), actualCustomer.getName());
        Assertions.assertEquals(customer.getEmail(), actualCustomer.getEmail());
    }

    @Test
    public void deleteCustomerShouldReturnSuccess() {
        String customerId = "test_id";
        Mockito.doNothing().when(customerDAO).deleteCustomer(customerId);
        String response = customerService.deleteCustomer(customerId);
        Assertions.assertEquals("test_id", response);
        Mockito.verify(customerDAO, Mockito.times(1)).deleteCustomer(customerId);
    }
}
