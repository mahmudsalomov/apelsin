package uz.bank.apelsin.model;

import lombok.*;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Detail extends AbsEntityInteger {

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    private Short quantity;

}
