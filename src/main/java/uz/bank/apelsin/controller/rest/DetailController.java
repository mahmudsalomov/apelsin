package uz.bank.apelsin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.apelsin.service.DetailService;
import uz.bank.apelsin.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("api/detail")
public class DetailController {
    @Autowired
    private DetailService detailService;

    /** Extra **/

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(detailService.getAll());
    }




}
