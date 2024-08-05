package api.kirana.register.transactions.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public abstract class DateAudit implements Serializable {

    @CreatedDate private Date createdAt;

    @LastModifiedDate private Date updatedAt;
}
