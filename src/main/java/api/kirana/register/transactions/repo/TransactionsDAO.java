package api.kirana.register.transactions.repo;

import api.kirana.register.transactions.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionsDAO {

    private final TransactionsRepo transactionRepo;

    @Autowired
    public TransactionsDAO(TransactionsRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    /**
     * Retrieves all transactions from the repository.
     *
     * @return a list of all transactions
     */
    public Page<Transactions> getAllTransactions(Pageable pageable) {
        return transactionRepo.findAll(pageable);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return an Optional containing the transaction if found, or empty if not found
     */
    public Optional<Transactions> getTransactionById(String id) {
        return transactionRepo.findById(id);
    }

    /**
     * Retrieves transactions by their type.
     *
     * @param Type the type of transactions to retrieve
     * @return a list of transactions with the specified type
     */
    public List<Transactions> getTransactionsByType(String Type) {
        return transactionRepo.findByType(Type);
    }

    /**
     * Retrieves transactions by their status
     * @param status
     * @return
     */
    public List<Transactions> getTransactionByStatus(String status) {
        return transactionRepo.findByStatus(status);
    }

//    /**
//     * Retrieves transactions by their date
//     * @param date
//     * @return
//     */
//    public List<Transactions> getTransactionByDate(Date date) {
//        return transactionRepo.findByDate(date);
//    }


    /**
     * Retrieves transactions that occurred between two dates
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Transactions> getTransactionByDateBetween(Date startDate, Date endDate) {
        return transactionRepo.findByDateBetween(startDate, endDate);
    }

    /**
     * Saves a new transaction to the repository.
     *
     * @param transactionRequest the transaction to save
     * @return the saved transaction
     */
    public Transactions saveTransaction(Transactions transactionRequest) {
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
