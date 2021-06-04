package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends AbsEntityInteger {

    @Column(updatable = false)
    private Timestamp time;

    @Column(precision = 10,scale = 2)
    private Double amount;

    @ManyToOne
    private Invoice invoice;

}
