package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends AbsEntityInteger {
    @OneToOne
    private Order order;
    @Column(precision = 10,scale = 2)
    private Double amount;
    private Date issued;
    private Date due;
}
