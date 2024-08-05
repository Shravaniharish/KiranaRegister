package api.kirana.register.customers.controllers;

import api.kirana.register.customers.models.CustomersDTO;
import api.kirana.register.customers.service.CustomersServiceImpl;
import api.kirana.register.response.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {


    private final CustomersServiceImpl customerService;

    @Autowired
    public CustomersController(CustomersServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a ResponseEntity containing the list of all customers and HTTP status
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "2") int size) {
        ApiResponse response = new ApiResponse();
        Pageable pageable = PageRequest.of(page, size);
        response.setData(customerService.getAllCustomers(pageable));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer
     * @return a ResponseEntity containing the customer data and HTTP status
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse> getCustomer(
            @NotNull
           @Valid @RequestParam(required = false) String id,
            @RequestParam(required = false) String name) {
        ApiResponse response = new ApiResponse();
        if (id == null && name == null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if (name != null)  {
            response.setData(customerService.getCustomerByName(name));
        }
        response.setData(customerService.getCustomerById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Saves a new customer.
     *
     * @param customer the customer data transfer object
     * @return a ResponseEntity containing the saved customer data and HTTP status
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<ApiResponse> saveCustomer ( @Valid @RequestBody CustomersDTO customer) {
        ApiResponse response = new ApiResponse();
        response.setData(customerService.saveCustomer(customer));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer
     * @return a ResponseEntity containing the result of the delete operation and HTTP status
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteCustomer (@NotNull @Valid @RequestParam String id) {
        ApiResponse response = new ApiResponse();
        response.setData(customerService.deleteCustomer(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
