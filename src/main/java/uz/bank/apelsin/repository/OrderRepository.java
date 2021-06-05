package uz.bank.apelsin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Order;
import uz.bank.apelsin.payload.special_payloads.CustomerLastOrderSummary;
import uz.bank.apelsin.payload.special_payloads.OrderWithoutInvoicesSummary;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from orders where (select count(detail.id) from detail where detail.order_id=orders.id)=0 and orders.date<=:vaqt",nativeQuery = true)
    List<Order> orders_without_details(@Param("vaqt") Date vaqt);

    @Query(value = "select * from orders where  orders.date<=:vaqt",nativeQuery = true)
    List<Order> orders_by_date(@Param("vaqt") Date date);

    int countAllBy();




    @Query(value = "select orders.customer_id, (select name from customer where orders.customer_id=customer.id ) as customer_name, orders.created_at as customer_last_order_date from orders where orders.id = (SELECT  subOrders.id FROM orders subOrders WHERE subOrders.customer_id = orders.customer_id ORDER BY subOrders.created_at DESC limit 1)",nativeQuery = true)
    List<CustomerLastOrderSummary> customers_last_orders();

    @Query(value = "select d.order_id, (select date from orders where orders.id=d.order_id), sum(d.quantity*p.price) as total_price from detail d, product p where d.product_id=p.id group by d.order_id order by d.order_id",nativeQuery = true)
    List<OrderWithoutInvoicesSummary> orders_without_invoices();
}
