package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.model.Category;
import uz.bank.apelsin.model.Customer;
import uz.bank.apelsin.model.Order;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.payload.special_payloads.CustomerLastOrderSummary;
import uz.bank.apelsin.repository.CustomerRepository;
import uz.bank.apelsin.repository.OrderRepository;
import uz.bank.apelsin.utils.Converter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Converter converter;

    @Value("${default.date.starting}")
    private Date starting;

    @Value("${default.date.ending}")
    private Date ending;

    public ApiResponse save(Customer customer){
        try {

            if (!customerRepository.existsByName(customer.getName())){
                if (customer.getName()!=null) customer.setId(null);
                Customer save = customerRepository.save(customer);
                return converter.apiSuccess("Saved",save);
            }
            return converter.apiError();



        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse customers_without_orders() {
        try {
            if (orderRepository.countAllBy()==0)
                return converter.apiSuccess(
                        "Customers without orders "+starting+" : "+ending+" ",
                        customerRepository.customer_by_date(starting)
                                .stream()
                                .map(item->(converter
                                        .customerToDto(item)))
                                .collect(Collectors
                                        .toList()));

            return converter.apiSuccess("Customers without orders "+starting+" : "+ending+" ",
                    customerRepository
                            .customers_without_orders(starting,ending)
                            .stream()
                            .map(item->(converter
                                    .customerToDto(item)))
                            .collect(Collectors
                                    .toList())
            );
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }


    public ApiResponse customers_last_orders() {
        try {
            List<CustomerLastOrderSummary> customers_last_orders=orderRepository.customers_last_orders();
            if (customers_last_orders.size()==0)
                return converter.apiSuccess("No last orders!",customers_last_orders);
            return converter.apiSuccess("Customer last orders",customers_last_orders);
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }

    public ApiResponse getAll() {
        try {
            return converter.apiSuccess(customerRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
