package uz.bank.apelsin.model;

import lombok.*;
import uz.bank.apelsin.model.template.AbsEntityInteger;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Category extends AbsEntityInteger {
    @NotNull
    private String name;
}
