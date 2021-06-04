package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order extends AbsEntityInteger {
    @ManyToOne
    private Customer customer;
    private Date date;
}
