package api.kirana.register.customers.repo;

import api.kirana.register.customers.entity.Customers;
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
public class CustomerDAOTest {
    @Mock
    private CustomersRepo customerRepo;

    @InjectMocks
    private CustomersDAO customerDAO;

    @Test
    public void getAllCustomersShouldReturnSuccess() {
        int page = 2;
        int size = 4;
        List<Customers> customers = Arrays.asList(
                new Customers(), new Customers(), new Customers(), new Customers()
        );
        Page<Customers> customerList = new PageImpl<>(customers);
        Pageable pageable = PageRequest.of(page, size);
        Mockito.when(customerRepo.findAll(pageable)).thenReturn(customerList);
        Page<Customers> actualList = customerDAO.getAllCustomers(pageable);
        Assertions.assertNotNull(actualList);
        Assertions.assertEquals(customerList, actualList);
    }


    @Test
    public void getCustomerByIdShouldReturnSuccess(){
        Customers customer = new Customers();
        customer.setId("test");
        Optional<Customers> expectedCustomer = Optional.of(customer);
        Mockito.when(customerRepo.findById(customer.getId())).thenReturn(expectedCustomer);
        Optional<Customers> actualCustomer = customerDAO.getCustomerById(customer.getId());
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer, actualCustomer);

    }

    @Test
    public void getCustomerByNameShouldReturnSuccess() {
        Customers customer = new Customers();
        customer.setName("abc");
        Optional<Customers> expectedCustomer = Optional.of(customer);
        Mockito.when(customerRepo.findByName(customer.getName())).thenReturn(expectedCustomer);
        Optional<Customers> actualCustomer = customerDAO.getCustomerByName(customer.getName());
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    public void saveCustomerShouldReturnSuccess() {
        Customers customer = new Customers();
        customer.setName("abc");
        customer.setEmail("abc@xyz.in");
        customer.setPhoneNumber("0987654321");
        customer.setStoreName("pqr");
        Mockito.when(customerRepo.save(customer)).thenReturn(customer);
        Customers actualCustomer = customerDAO.saveCustomer(customer);
        Assertions.assertNotNull(actualCustomer);
        Assertions.assertEquals(customer.getName(), actualCustomer.getName());
    }

    @Test
    public void deleteCustomerShouldReturnSuccess() {
        String id = "test";
        Mockito.doNothing().when(customerRepo).deleteById(id);
        customerDAO.deleteCustomer(id);
        Mockito.verify(customerRepo,Mockito.times(1)).deleteById(id);
    }

}
