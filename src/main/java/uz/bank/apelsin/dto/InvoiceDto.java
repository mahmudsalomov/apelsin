package uz.bank.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDto {
    private Integer id;
    private OrderDto order;
    private Double amount;
    private Date issued;
    private Date due;


}
