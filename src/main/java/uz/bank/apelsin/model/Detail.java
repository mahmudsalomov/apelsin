package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Detail extends AbsEntityInteger {

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    private Short quantity;

}
