package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.dto.InvoiceDto;
import uz.bank.apelsin.model.Invoice;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.payload.special_payloads.OverpaidInvoicesSummary;
import uz.bank.apelsin.payload.special_payloads.WrongDateInvoiceSummary;
import uz.bank.apelsin.repository.InvoiceRepository;
import uz.bank.apelsin.repository.PaymentRepository;
import uz.bank.apelsin.utils.Converter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private Converter converter;
    public ApiResponse expired_invoices() {
        try {
            List<Invoice> expired_invoices = invoiceRepository.findAllByDueIsLessThan(new Date(new java.util.Date().getTime()));
            if (expired_invoices.size()==0)
                return converter.apiSuccess("No expired invoices");
            List<InvoiceDto> invoiceDtoList=expired_invoices
                    .stream()
                    .map(item->(converter.invoiceToDto(item)))
                    .collect(Collectors.toList());
            return converter.apiSuccess(invoiceDtoList);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse wrong_date_invoices() {
        try {
            List<WrongDateInvoiceSummary> wrong_date_invoices=invoiceRepository.wrong_date_invoices();
            if (wrong_date_invoices.size()==0)
                return converter.apiSuccess("No wrong date invoices!",wrong_date_invoices);
            return converter.apiSuccess(wrong_date_invoices);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse overpaid_invoices() {

        try {
            List<OverpaidInvoicesSummary> overpaid_invoices = paymentRepository.overpaid_invoices();
            if (overpaid_invoices.size()==0)
                return converter.apiSuccess("No overpaid invoices!",overpaid_invoices);
            return converter.apiSuccess(overpaid_invoices.stream().filter(item->item.getReimbursed_amount()!=0));
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }

    }

    public ApiResponse getAll() {
        try {
            return converter.apiSuccess(invoiceRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
