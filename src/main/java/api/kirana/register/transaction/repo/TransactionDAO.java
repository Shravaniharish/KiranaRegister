package api.kirana.register.transaction.repo;

import api.kirana.register.transaction.models.Transaction;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TransactionDAO {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionDAO(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    /**
     * Retrieves all transactions from the repository.
     *
     * @return a list of all transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return an Optional containing the transaction if found, or empty if not found
     */
    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepo.findById(id);
    }

    /**
     * Retrieves transactions by their type.
     *
     * @param Type the type of transactions to retrieve
     * @return a list of transactions with the specified type
     */
    public List<Transaction> getTransactionsByType(String Type) {
        return transactionRepo.findByTransactionType(Type);
    }

    /**
     * Retrieves transactions by their status
     * @param status
     * @return
     */
    public List<Transaction> getTransactionByStatus(String status) {
        return transactionRepo.findByTransactionStatus(status);
    }

    /**
     * Retrieves transactions by their date
     * @param date
     * @return
     */
    public List<Transaction> getTransactionByDate(String date) {
        return transactionRepo.findByTransactionDate(date);
    }


    /**
     * Retrieves transactions that occurred between two dates
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Transaction> getTransactionByDateBetween(String startDate, String endDate) {
        return transactionRepo.findByTransactionDateBetween(startDate, endDate);
    }

    /**
     * Saves a new transaction to the repository.
     *
     * @param transactionRequest the transaction to save
     * @return the saved transaction
     */
    public Transaction saveTransaction(Transaction transactionRequest) {
        return transactionRepo.save(transactionRequest);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     */
    public void deleteTransaction(String id){
        transactionRepo.deleteById(id);
    }
}
