package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
