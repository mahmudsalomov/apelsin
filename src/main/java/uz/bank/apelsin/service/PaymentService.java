package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.model.Invoice;
import uz.bank.apelsin.model.Payment;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.payload.special_payloads.PaymentResponse;
import uz.bank.apelsin.payload.StatusString;
import uz.bank.apelsin.repository.InvoiceRepository;
import uz.bank.apelsin.repository.PaymentRepository;
import uz.bank.apelsin.utils.Converter;

import java.util.Date;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private Converter converter;

    public ApiResponse payment(Integer invoice_id) {
        try {
            Optional<Invoice> invoice = invoiceRepository.findById(invoice_id);
            if (invoice.isEmpty())
                return converter.apiError(
                        "Invoice not found!",new PaymentResponse(StatusString.FAILED,null)
                );

            if(invoice.get().getDue().getTime()<new Date().getTime())
                return converter.apiError(
                        "Expired!",new PaymentResponse(StatusString.FAILED,null)
                );

            Payment payment = paymentRepository.save(Payment
                    .builder()
                    .amount(invoice.get().getAmount())
                    .invoice(invoice.get())
                    .build()
            );

            return converter.apiSuccess(
                    new PaymentResponse(StatusString.SUCCESS,converter.paymentToDto(payment))
            );

        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError(new PaymentResponse(StatusString.FAILED,null));
        }
    }

    public ApiResponse getPayment(Integer payment_details_id){
        try {
            Optional<Payment> payment = paymentRepository.findById(payment_details_id);
            if (payment.isEmpty()) return converter.apiError("Payment not found!");
            return converter.apiSuccess(converter.paymentToDto(payment.get()));
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse getAll() {
        try {
            return converter.apiSuccess(paymentRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
