package api.kirana.register.transaction.service;

import api.kirana.register.transaction.models.Transaction;
import api.kirana.register.transaction.models.TransactionDTO;
import api.kirana.register.transaction.repo.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionServiceImpl(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getAllTransactions() {
        try{
            return transactionDAO.getAllTransactions();
        }
        catch (Exception e){
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    @Cacheable(value = "Transaction")
    @Override
    public Optional<Transaction> getTransactionById(String id) {
        return transactionDAO.getTransactionById(id);
    }

    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionsByType(String Type) {
        return transactionDAO.getTransactionsByType(Type);
    }

    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionByStatus(String status) {
        return transactionDAO.getTransactionByStatus(status);
    }

    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionByDate(String date) {
        return transactionDAO.getTransactionByDate(date);
    }

    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionByDateBetween(String startDate, String endDate) {
        return transactionDAO.getTransactionByDateBetween(startDate, endDate);
    }

    @CachePut(value = "Transaction")
    @Override
    public Transaction saveTransaction(TransactionDTO transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(transactionRequest.getTransactionAmount());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setTransactionStatus(transactionRequest.getTransactionStatus());
        transaction.setCustomerId(transactionRequest.getCustomerId());
        transaction.setPaymentMethod(transactionRequest.getPaymentMethod());
        transaction.setTransactionTime(String.valueOf(LocalTime.parse(LocalTime.now().toString())));
        transaction.setTransactionDate(String.valueOf(LocalDate.parse(LocalDate.now().toString())));
        return transactionDAO.saveTransaction(transaction);
    }

    @CacheEvict(value = "Transaction")
    @Override
    public String deleteTransaction(String id) {
        transactionDAO.deleteTransaction(id);
        return "Transaction deleted";
    }
}
