package uz.bank.apelsin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bank.apelsin.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /** Extra **/
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }
}
