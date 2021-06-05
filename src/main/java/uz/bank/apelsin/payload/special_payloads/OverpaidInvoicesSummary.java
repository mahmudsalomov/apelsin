package uz.bank.apelsin.payload.special_payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface OverpaidInvoicesSummary {
    Integer getInvoice_id();

    float getReimbursed_amount();
}
