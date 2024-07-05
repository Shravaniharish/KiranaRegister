package api.kirana.register.transaction.controllers;

import api.kirana.register.response.Response;
import api.kirana.register.transaction.models.TransactionDTO;
import api.kirana.register.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionControllers {
    private final TransactionService transactionService;

    @Autowired
    public TransactionControllers(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllTransactions() {
        Response response = new Response();
        response.setData(transactionService.getAllTransactions());
        response.setMessage("List of all transactions");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getTransactionById")
    public ResponseEntity<Response> getTransactionById(@RequestParam String id) {
        Response response = new Response();
        response.setData(transactionService.getTransactionById(id));
        response.setMessage(" transaction id match");
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping("/getTransactionByStatus")
    public ResponseEntity<Response>  getTransactionByStatus(@RequestParam String status) {
        Response response = new Response();
        response.setData(transactionService.getTransactionByStatus(status));
        response.setMessage("transaction status match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getTransactionByType")
    public ResponseEntity<Response> getTransactionByType(@RequestParam String type) {
        Response response = new Response();
        response.setData(transactionService.getTransactionsByType(type));
        response.setMessage("transaction type match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getTransactionByDate")
    public ResponseEntity<Response> getTransactionByDate(@RequestParam String date) {
        Response response = new Response();
        response.setData(transactionService.getTransactionByDate(date));
        response.setMessage("transaction Date match");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getTransactionBetweenDates")
    public ResponseEntity<Response> getTransactionByDateBetween(@RequestParam String startDate,@RequestParam String endDate) {
        Response response = new Response();
        response.setData(transactionService.getTransactionByDateBetween(startDate, endDate));
        response.setMessage("transactions that between the given dates");
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/saveTransactions")
    public ResponseEntity<Response> saveTransactions(@RequestBody TransactionDTO request) {
        Response response = new Response();
        response.setData(transactionService.saveTransaction(request));
        response.setMessage("transaction saved!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMapping")
    public ResponseEntity<Response> deleteTransaction(@RequestParam String id) {
        Response response = new Response();
        response.setData(transactionService.deleteTransaction(id));
        response.setMessage("!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

