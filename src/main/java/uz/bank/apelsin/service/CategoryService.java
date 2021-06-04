package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Product;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.repository.CategoryRepository;
import uz.bank.apelsin.repository.ProductRepository;
import uz.bank.apelsin.utils.Converter;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Converter converter;

    public ApiResponse save(Category category){
        try {

            if (!categoryRepository.existsByName(category.getName())){
                if (category.getName()!=null) category.setId(null);
                Category save = categoryRepository.save(category);
                return converter.apiSuccess("Saved",save);
            }
            return converter.apiError();


        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse edit(Category category){
        try {
                if (category.getName()==null||category.getName().isEmpty()) return converter.apiError();
                Category edit = categoryRepository.save(category);
                return converter.apiSuccess("edited",edit);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse getById(Integer id){
        try {
            Optional<Category> byId = categoryRepository.findById(id);
            if (byId.isPresent())
            return converter.apiSuccess(byId.get());
            return converter.apiError();
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse getByName(String name){
        try {
            Optional<Category> byName = categoryRepository.findByName(name);
            if (byName.isPresent())
                return converter.apiSuccess(byName.get());
            return converter.apiError();
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse getAll(){
        try {
            return converter.apiSuccess(categoryRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse getCategoryByProductId(Integer product_id){
        try {
            Optional<Product> productOptional = productRepository.findById(product_id);
            if (productOptional.isPresent())
                return converter.apiSuccess(productOptional.get().getCategory());
            return converter.apiError();
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }



}
