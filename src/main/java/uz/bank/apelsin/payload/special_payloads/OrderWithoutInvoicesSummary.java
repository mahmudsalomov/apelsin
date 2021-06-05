package uz.bank.apelsin.payload.special_payloads;

import java.sql.Date;

public interface OrderWithoutInvoicesSummary {
    Integer getOrder_id();
    Date getDate();
    float getTotal_price();
}
