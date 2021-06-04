package uz.bank.apelsin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.bank.apelsin.model.template.RoleName;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
