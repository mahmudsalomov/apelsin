package uz.bank.apelsin.payload.special_payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


public interface WrongDateInvoiceSummary {
    Integer getInvoice_id();
    Date getIssued_date();
    Integer getOrder_id();
    Date getOrder_date();
}
