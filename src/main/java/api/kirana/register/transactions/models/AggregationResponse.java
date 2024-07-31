package api.kirana.register.transactions.models;

import lombok.Data;

@Data
public class AggregationResponse {
    double netFlow;
    double credit;
    double debit;
}
