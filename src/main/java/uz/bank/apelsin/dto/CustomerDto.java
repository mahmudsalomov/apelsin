package uz.bank.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Integer id;
    private String name;
    private String country;
    private String address;
    private String phone;
}
