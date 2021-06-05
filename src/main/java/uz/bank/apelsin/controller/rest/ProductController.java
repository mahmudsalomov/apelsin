package uz.bank.apelsin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.apelsin.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /** Extra **/



    /** For the task **/
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/details")
    public HttpEntity<?> category_by_product_id(@RequestParam Integer product_id){
        return ResponseEntity.ok(productService.getProductById(product_id));
    }
}
