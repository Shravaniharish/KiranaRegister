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

    /**
     * lists all transactions along with their specific details
     * @return
     */
    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getAllTransactions() {
        try{
            return (List<Transaction>) transactionDAO.getAllTransactions();
        }
        catch (Exception e){
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    /**
     * displays details of transaction on specifying transaction details
     * @param id
     * @return
     */
    @Cacheable(value = "Transaction")
    @Override
    public Optional<Transaction> getTransactionById(String id) {
        return transactionDAO.getTransactionById(id);
    }

    /**
     * displays list of transactions for a specified transaction type
     * @param type
     * @return
     */
    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionsByType(String type) {
        return transactionDAO.getTransactionsByType(type);
    }

    /**
     * displays list of transactions for a specified transaction status
     * @param status
     * @return
     */
    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionByStatus(String status) {
        return transactionDAO.getTransactionByStatus(status);
    }

    /**
     * displays list of transactions for a specified transaction date
     * @param date
     * @return
     */
    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionByDate(String date) {
        return transactionDAO.getTransactionByDate(date);
    }

    /**
     * displays all transaction that were carried out between the startDate and endDate
     * @param startDate
     * @param endDate
     */
    @Cacheable(value = "Transaction")
    @Override
    public List<Transaction> getTransactionByDateBetween(String startDate, String endDate) {
        return transactionDAO.getTransactionByDateBetween(startDate, endDate);
    }

    /**
     * saves transaction into the database
     * @param transactionRequest
     */
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

    /**
     * deletes transaction for the specified transaction id
     * @param id
     */
    @CacheEvict(value = "Transaction")
    @Override
    public String deleteTransaction(String id) {
        transactionDAO.deleteTransaction(id);
        return "Transaction deleted";
    }
}
