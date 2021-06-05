package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.dto.ProductDto;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Product;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.payload.special_payloads.BulkProductsSummary;
import uz.bank.apelsin.payload.special_payloads.HighDemandProductsSummary;
import uz.bank.apelsin.payload.special_payloads.NumberOfProductsInYearSummary;
import uz.bank.apelsin.repository.CategoryRepository;
import uz.bank.apelsin.repository.ProductRepository;
import uz.bank.apelsin.utils.Converter;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Converter converter;

    @Value("${high.demand.product.value}")
    private Integer value;

    @Value("${bulk.product.value}")
    private Integer bulkValue;


    @Value("${country.default.date.starting}")
    private Date starting;

    @Value("${country.default.date.ending}")
    private Date ending;

    public ApiResponse save(ProductDto dto){
        try {
            Optional<Category> category = categoryRepository.findById(dto.getId());

            if (category.isPresent()){

                Product product=Product
                        .builder()
                        .category(category.get())
                        .description(dto.getDescription())
                        .name(dto.getName())
                        .price(dto.getPrice())
                        .photo(dto.getPhoto())
                        .build();
                Product save = productRepository.save(product);
                return converter.apiSuccess(save);
            }
            return converter.apiError();


        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse getAll(){
        try {
            return converter.apiSuccess(productRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse getProductById(Integer product_id){
        try {
            Optional<Product> optional = productRepository.findById(product_id);
            if (optional.isPresent())
                return converter.apiSuccess(optional.get());
            return converter.apiError("Product not found!");
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse high_demand_products() {
        try {
            List<HighDemandProductsSummary> high_demand_products = productRepository.high_demand_products(value);
            if (high_demand_products.size()==0)
                return converter.apiSuccess("No high demand products",high_demand_products);
            return converter.apiSuccess("High demand products, more than "+value+" orders in total ",high_demand_products);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse bulk_products() {
        try {

            List<BulkProductsSummary> bulk_products = productRepository.bulk_products(bulkValue);
            if (bulk_products.size()==0)
                return converter.apiSuccess("No bulk products",bulk_products);
            return converter.apiSuccess("Products of average orders quantity greater than "+bulkValue+"",bulk_products);

        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse number_of_products_in_year() {
        try {

            List<NumberOfProductsInYearSummary> number_of_products_in_year = productRepository.number_of_products_in_year(starting, ending);
            number_of_products_in_year=number_of_products_in_year.stream().filter(item->item.getOrder_count()!=0).collect(Collectors.toList());
            if (number_of_products_in_year.size()==0)
                return converter.apiSuccess("No number of products in year "+starting+" : "+ending+"",number_of_products_in_year);
            return converter.apiSuccess("Number of products in year "+starting+" : "+ending+"",number_of_products_in_year);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
