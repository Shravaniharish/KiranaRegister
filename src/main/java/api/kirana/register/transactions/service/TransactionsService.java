package api.kirana.register.transactions.service;

import api.kirana.register.transactions.entity.Transactions;
import api.kirana.register.transactions.enums.ReportType;
import api.kirana.register.transactions.models.AggregationResponse;
import api.kirana.register.transactions.models.TransactionsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TransactionsService {
   // List<Transactions> getAllTransactions();
    Page<Transactions> getAllTransactions(Pageable pagable);

    Optional<Transactions> getTransactionById(String id);

    List<Transactions> getTransactionsByType(String type);

    List<Transactions> getTransactionByStatus(String status);

    List<Transactions> getTransactionByDate(String date);

    List<Transactions> getTransactionByDateBetween(String startDate, String endDate);

    Transactions saveTransaction(TransactionsDTO transactionRequest);

    Map<String, AggregationResponse> getReports(ReportType reportType, String startDate, String endDate );

    String deleteTransaction(String id);

}
