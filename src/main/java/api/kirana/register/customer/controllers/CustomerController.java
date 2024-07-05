package api.kirana.register.customer.controllers;

import api.kirana.register.customer.models.CustomerDTO;
import api.kirana.register.customer.service.CustomerServiceImpl;
import api.kirana.register.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {


    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<Response> getAllCustomers() {
        Response response = new Response();
        response.setData(customerService.getAllCustomers());
        response.setMessage("list of all customers");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCustomerById")
    public ResponseEntity<Response> getCustomerById(@RequestParam String id) {
        Response response = new Response();
        response.setData(customerService.getCustomerById(id));
        response.setMessage("customer id match");
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @GetMapping("/getCustomerByName")
    public ResponseEntity<Response> getCustomerByName (@RequestParam String name) {
        Response response = new Response();
        response.setData(customerService.getCustomerByName(name));
        response.setMessage("customer name match");
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<Response> saveCustomer (@RequestBody CustomerDTO customer) {
        Response response = new Response();
        response.setData(customerService.saveCustomer(customer));
        response.setMessage("customer is saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<Response> deleteCustomer (@RequestParam String id) {
        Response response = new Response();
        response.setData(customerService.deleteCustomer(id));
        response.setMessage("!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
