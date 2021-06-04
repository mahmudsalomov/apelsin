package uz.bank.apelsin.model.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

// Short entity template
@Data
@MappedSuperclass
public abstract class AbsEntityShort implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

}
