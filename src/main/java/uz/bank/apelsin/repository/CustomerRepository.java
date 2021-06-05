package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Customer;
import uz.bank.apelsin.model.Order;

import java.sql.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByName(String name);
    boolean existsByName(String name);


    @Query(value = "select * from customer where (select count(orders.id) from orders where orders.date between :starting and :ending and orders.customer_id=customer.id )=0",nativeQuery = true)
    List<Customer> customers_without_orders(@Param("starting") Date starting, @Param("ending") Date ending);

    @Query(value = "select * from customer where customer.created_at>:starting",nativeQuery = true)
    List<Customer> customer_by_date(@Param("starting") Date starting);

}
