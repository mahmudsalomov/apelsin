package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntityInteger {

    @NotNull
    @Column(length = 10)
    private String name;
    @ManyToOne
    private Category category;

    @NotNull
    @Column(length = 20)
    private String description;

    @NotNull
    @Column(precision = 8,scale = 2)
    private double price;

    @NotNull
    @Column(length = 1024)
    private String photo;
}
