package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
}
