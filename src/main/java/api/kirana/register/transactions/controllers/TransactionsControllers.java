package api.kirana.register.transactions.controllers;

import api.kirana.register.response.ApiResponse;
import api.kirana.register.transactions.models.TransactionsDTO;
import api.kirana.register.transactions.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/transactions")
public class TransactionsControllers {
    private final TransactionsService transactionService;

    @Autowired
    public TransactionsControllers(TransactionsService transactionService) {
        this.transactionService = transactionService;
    }


    /**
     * lists all transactions along with their specific details
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTransactions(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "2") int size) {
        ApiResponse response = new ApiResponse();
        Pageable pageable = PageRequest.of(page, size);
        response.setData(transactionService.getAllTransactions(pageable));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * displays details of transaction on specifying transaction details
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse> getTransactionById(@Valid @RequestParam String id) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.getTransactionById(id));
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }
    /**
     * displays list of transactions for a specified status
     * @param status
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<ApiResponse>  getTransactionByStatus(@RequestParam String status) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.getTransactionByStatus(status));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * displays list of transactions for a specified transaction status
     * @param type
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/type")
    public ResponseEntity<ApiResponse> getTransactionByType(@RequestParam String type) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.getTransactionsByType(type));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * displays list of transactions for a specified transaction date
     * @param date
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/date")
    public ResponseEntity<ApiResponse> getTransactionByDate(@RequestParam String date) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.getTransactionByDate(date));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * displays list of transactions that lie between the startDate and endDate
     * @param startDate
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/dates")
    public ResponseEntity<ApiResponse> getTransactionByDateBetween(@RequestParam String startDate , String endDate) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.getTransactionByDateBetween(startDate, endDate));
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * saves transaction into the database
     * @param request
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<ApiResponse> saveTransactions(@Valid @RequestBody TransactionsDTO request) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.saveTransaction(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * gives weekly monthly
     * @param reportType
     * @param startDate
     * @param endDate
     *
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reports")
    public ResponseEntity<ApiResponse> getReports(@RequestParam String reportType, @RequestParam String startDate, @RequestParam String endDate) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.getReports(reportType, startDate, endDate));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * deletes transaction for the specified transaction id
     * @param id
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteTransaction(@Valid @RequestParam String id) {
        ApiResponse response = new ApiResponse();
        response.setData(transactionService.deleteTransaction(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

