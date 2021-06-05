package uz.bank.apelsin.payload.special_payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


public interface CustomerLastOrderSummary {
    Integer getCustomer_id();
    String getCustomer_name();
    Timestamp getCustomer_last_order_date();
}
