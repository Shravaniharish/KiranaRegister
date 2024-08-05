package api.kirana.register.transactions.repo;

import api.kirana.register.transactions.entity.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TransactionsRepo extends MongoRepository<Transactions, String > {
    Page<Transactions> findAll(Pageable pageable);

    List<Transactions> findByStatus(String Status);

    List<Transactions> findByType(String transactionType);

    List<Transactions> findByDateBetween(Date startDate, Date endDate);

}


