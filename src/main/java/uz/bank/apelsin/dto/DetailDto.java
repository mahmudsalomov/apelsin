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
    private OrderDto orderDto;
    private ProductDto productDto;
    private Short quantity;
}
