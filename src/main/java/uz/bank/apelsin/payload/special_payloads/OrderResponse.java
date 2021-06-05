package uz.bank.apelsin.payload.special_payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.payload.ApiResponse;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrderResponse extends ApiResponse {

    private Integer invoice_number;

    public OrderResponse(Integer invoice_number) {
        this.invoice_number = invoice_number;
    }

    public OrderResponse(String status, boolean success, Integer invoice_number) {
        super(status, success);
        this.invoice_number = invoice_number;
    }
}
