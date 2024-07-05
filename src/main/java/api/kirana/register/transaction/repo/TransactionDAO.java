package api.kirana.register.transaction.repo;

import api.kirana.register.transaction.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionDAO {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionDAO(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepo.findById(id);
    }

    public List<Transaction> getTransactionsByType(String Type) {
        return transactionRepo.findByTransactionType(Type);
    }

    public List<Transaction> getTransactionByStatus(String status) {
        return transactionRepo.findByTransactionStatus(status);
    }

    public List<Transaction> getTransactionByDate(String date) {
        return transactionRepo.findByTransactionDate(date);
    }

    public List<Transaction> getTransactionByDateBetween(String startDate, String endDate) {
        return transactionRepo.findByTransactionDateBetween(startDate, endDate);
    }

    public Transaction saveTransaction(Transaction transactionRequest) {
        return transactionRepo.save(transactionRequest);
    }

    public void deleteTransaction(String id){
        transactionRepo.deleteById(id);
    }
}
