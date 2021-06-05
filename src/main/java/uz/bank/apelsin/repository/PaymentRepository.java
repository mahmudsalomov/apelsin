package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Invoice;
import uz.bank.apelsin.model.Payment;
import uz.bank.apelsin.payload.special_payloads.OverpaidInvoicesSummary;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByInvoice(Invoice invoice);

    @Query(value = "SELECT invoice_id, sum(amount)-(select amount from invoice where invoice.id=invoice_id) as reimbursed_amount FROM payment GROUP BY invoice_id",nativeQuery = true)
    List<OverpaidInvoicesSummary> overpaid_invoices();
}
