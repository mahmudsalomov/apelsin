package uz.bank.apelsin.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /** Apis for Manager **/

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.edit(category));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getById(id));
    }


//    @DeleteMapping("/{id}")
//    public HttpEntity<?> deleteById(@PathVariable Integer id){
//        return ResponseEntity.ok(categoryService.getById(id));
//    }



    /** For the task **/



    @GetMapping("/list")
    public HttpEntity<?> category_list(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/")
    public HttpEntity<?> category_by_product_id(@RequestParam Integer product_id){
        return ResponseEntity.ok(categoryService.getCategoryByProductId(product_id));
    }
}
