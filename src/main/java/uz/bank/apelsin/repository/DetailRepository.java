package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Detail;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Integer> {
}
