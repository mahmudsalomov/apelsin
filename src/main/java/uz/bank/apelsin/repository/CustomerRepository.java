package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByName(String name);
    boolean existsByName(String name);
}
