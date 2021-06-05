package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Invoice;
import uz.bank.apelsin.payload.special_payloads.WrongDateInvoiceSummary;

import java.sql.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findAllByDueIsLessThan(Date date);

    @Query(value = "select id as invoice_id, issued as issued_date, order_id, (select date from orders where invoice.order_id=orders.id) as order_date from invoice where invoice.issued<(select orders.date from orders where invoice.order_id=orders.id)",nativeQuery = true)
    List<WrongDateInvoiceSummary> wrong_date_invoices();
}
