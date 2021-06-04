package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsEntityInteger {
    @NotNull
    private String name;
}
