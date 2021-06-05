package uz.bank.apelsin.model;

import lombok.*;
import uz.bank.apelsin.model.template.AbsEntityInteger;
import uz.bank.apelsin.model.template.AbsEntityUUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Customer extends AbsEntityInteger {

    @Column(length = 14)
    private String name;
    @Column(length = 3)
    private String country;

    @NotNull
    @Column(columnDefinition = "text")
    private String address;

    @NotNull
    @Column(length = 50)
    private String phone;


}
