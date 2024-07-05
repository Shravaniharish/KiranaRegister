package api.kirana.register.transaction.repo;

import api.kirana.register.transaction.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepo extends MongoRepository<Transaction, String > {

    List<Transaction> findByTransactionStatus(String Status);

    List<Transaction> findByTransactionType(String transactionType);

    List<Transaction> findByTransactionDate(String date);

    List<Transaction> findByTransactionDateBetween(String startDate, String endDate);

}


