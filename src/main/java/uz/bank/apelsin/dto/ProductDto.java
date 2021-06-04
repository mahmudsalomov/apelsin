package uz.bank.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.model.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private Category category;
    private String description;
    private double price;
    private String photo;
}
