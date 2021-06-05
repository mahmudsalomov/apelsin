package uz.bank.apelsin.payload.special_payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.bank.apelsin.dto.PaymentDto;
import uz.bank.apelsin.model.Payment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String payment_status;
    private PaymentDto payment;

}
