package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Product;
import uz.bank.apelsin.payload.special_payloads.BulkProductsSummary;
import uz.bank.apelsin.payload.special_payloads.HighDemandProductsSummary;
import uz.bank.apelsin.payload.special_payloads.NumberOfProductsInYearSummary;

import java.sql.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "select id as product_code,(select sum(quantity) from detail where detail.product_id=product.id) as total_number from product where (select sum(quantity) from detail where detail.product_id=product.id)>=:value",nativeQuery = true)
    List<HighDemandProductsSummary> high_demand_products(@Param("value") Integer value);

    @Query(value = "select id as product_code, price from product where (select avg(quantity) from detail where detail.product_id=product.id)>=:bulkValue",nativeQuery = true)
    List<BulkProductsSummary> bulk_products(@Param("bulkValue") Integer bulkValue);


    @Query(value = "SELECT b.country, count(a.id) AS order_count FROM customer b LEFT JOIN (select * from orders where orders.date between :starting and :ending) a ON b.id = a.customer_id GROUP BY b.country",nativeQuery = true)
    List<NumberOfProductsInYearSummary> number_of_products_in_year(@Param("starting") Date starting,@Param("ending") Date ending);
}
