package uz.bank.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPostDto {
    private Integer customer_id;
    private Integer product_id;
    private Double quantity;
}
