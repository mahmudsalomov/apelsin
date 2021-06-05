package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.dto.OrderDto;
import uz.bank.apelsin.model.*;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.payload.special_payloads.OrderResponse;
import uz.bank.apelsin.payload.StatusString;
import uz.bank.apelsin.payload.special_payloads.OrderWithoutInvoicesSummary;
import uz.bank.apelsin.repository.*;
import uz.bank.apelsin.utils.Converter;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private Converter converter;

    @Value("${default.due.limit}")
    private long due;

    @Value("${task.order.date}")
    private Date taskOrderDate;

    public ApiResponse order(
            Integer customer_id,
            Integer product_id,
            Short quantity)
    {
        Optional<Customer> customer = customerRepository.findById(customer_id);

        Optional<Product> product = productRepository.findById(product_id);
        if (!customer.isPresent()) return converter.apiError("Customer not found");
        if (!product.isPresent()) return converter.apiError("Product not found");

        try {
            Order order = orderRepository.save(
                    Order
                            .builder()
                            .customer(customer.get())
                            .date(new Date(new java.util.Date().getTime()))
                            .build()
            );

            Detail detail = detailRepository.save(
                    Detail
                            .builder()
                            .order(order)
                            .product(product.get())
                            .quantity(quantity)
                            .build()
            );

            Invoice invoice = invoiceRepository.save(
                    Invoice
                            .builder()
                            .order(order)
                            .amount(product.get().getPrice() * quantity)
                            .issued(new Date(new java.util.Date().getTime()))
                            .due(new Date(new java.util.Date().getTime() + due))
                            .build()
            );
            return new OrderResponse(StatusString.SUCCESS,true,invoice.getId());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse getOrder(Integer order_id){
        try {
            Optional<Order> order = orderRepository.findById(order_id);
            if (!order.isPresent()) return converter.apiError("Order not found!");
            List<Detail> details = detailRepository.findByOrder(order.get());
//            if (detail.get(0).isEmpty()) return converter.apiError();
            OrderDto orderDto = converter.orderToDto(order.get());
            orderDto.setDetails(details.stream().map(item->converter.detailToDto(item)).collect(Collectors.toList()));
            return converter.apiSuccess(orderDto);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse orders_without_details() {
        try {
            List<OrderDto> orders_without_details = orderRepository.orders_without_details(taskOrderDate).stream().map(item -> converter.orderToDto(item)).collect(Collectors.toList());
            if (detailRepository.countAllBy()==0||orders_without_details.size()==0)
                return converter.apiSuccess("No orders without details and before "+taskOrderDate+"",orders_without_details);
            return converter.apiSuccess("Orders without details and before "+taskOrderDate+"",orders_without_details);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse orders_without_invoices() {
        try {

            List<OrderWithoutInvoicesSummary> orders_without_invoices = orderRepository.orders_without_invoices();

            if (orders_without_invoices.size()==0)
                return converter.apiSuccess("No orders with details",orders_without_invoices);
            return converter.apiSuccess(orders_without_invoices);

        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse getAll() {
        try {
            return converter.apiSuccess(orderRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
