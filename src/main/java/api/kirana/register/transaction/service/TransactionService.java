package api.kirana.register.transaction.service;

import api.kirana.register.transaction.models.Transaction;
import api.kirana.register.transaction.models.TransactionDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(String id);

    List<Transaction> getTransactionsByType(String Type);

    List<Transaction> getTransactionByStatus(String status);

    List<Transaction> getTransactionByDate(String date);

    List<Transaction> getTransactionByDateBetween(String startDate, String endDate);

    Transaction saveTransaction(TransactionDTO transactionRequest);

    String deleteTransaction(String id);

}
