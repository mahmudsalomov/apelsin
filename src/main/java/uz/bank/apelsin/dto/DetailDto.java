package uz.bank.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailDto {
    private Integer id;
    private Integer order_id;
    private Integer product_id;
    private Short quantity;
    private String product_name;
}
