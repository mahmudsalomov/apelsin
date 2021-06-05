package uz.bank.apelsin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.apelsin.service.PaymentService;

@RestController
@CrossOrigin
@RequestMapping("api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    /** Extra **/

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(paymentService.getAll());
    }




    /** For the task **/
    @PostMapping
    public HttpEntity<?> payment(@RequestParam Integer invoice_id){
        return ResponseEntity.ok(paymentService.payment(invoice_id));
    }

    @GetMapping("/details")
    public HttpEntity<?> details(@RequestParam Integer payment_details_id){
        return ResponseEntity.ok(paymentService.getPayment(payment_details_id));
    }

}
