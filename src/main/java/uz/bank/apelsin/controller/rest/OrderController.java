package uz.bank.apelsin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.apelsin.dto.OrderPostDto;
import uz.bank.apelsin.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /** Extra **/

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }


    /** For the task **/
    @PostMapping
    public HttpEntity<?> order(
            @RequestParam Integer customer_id,
            @RequestParam Integer product_id,
            @RequestParam(defaultValue = "0") Short quantity
    )
    {
        return ResponseEntity.ok(orderService.order(customer_id,product_id,quantity));
    }


    @GetMapping("/details")
    public HttpEntity<?> details(@RequestParam Integer order_id){
        return ResponseEntity.ok(orderService.getOrder(order_id));
    }

}
