package uz.bank.apelsin.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.bank.apelsin.dto.*;
import uz.bank.apelsin.model.*;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.payload.ApiResponseObject;
import uz.bank.apelsin.payload.ApiResponseObjectByPageable;
import uz.bank.apelsin.payload.StatusString;
import uz.bank.apelsin.repository.DetailRepository;
import uz.bank.apelsin.repository.ProductRepository;

import java.util.stream.Collectors;

@Component
public class Converter {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DetailRepository detailRepository;

    /** For responses **/

    public ApiResponse api(String status, boolean success){
        return new ApiResponse(status,success);
    }

    public ApiResponse api(String status, boolean success,Object object){
        return new ApiResponseObject(status,success,object);
    }

    public ApiResponse api(String status, boolean success,Object object,long totalElements, Integer totalPages){
        return new ApiResponseObjectByPageable(status,success,object,totalElements,totalPages);
    }

    public ApiResponse apiError(){
        return api(StatusString.FAILED,false);
    }

    public ApiResponse apiError(String status){
        return api(status,false);
    }

    public ApiResponse apiError(Object object){
        return api(StatusString.FAILED,false,object);
    }

    public ApiResponse apiError(String status, Object object){
        return api(status,false,object);
    }


    public ApiResponse apiSuccess(){
        return api(StatusString.SUCCESS,true);
    }

    public ApiResponse apiSuccess(String status){
        return api(status,true);
    }

    public ApiResponse apiSuccess(Object object){
        return api(StatusString.SUCCESS,true,object);
    }

    public ApiResponse apiSuccess(String status, Object object){
        return api(status,true,object);
    }

    public ApiResponse apiSuccess(Object object,long totalElements, Integer totalPages){
        return api(StatusString.SUCCESS,true, object, totalElements, totalPages);
    }

    public ApiResponse apiSuccess(String status, Object object,long totalElements, Integer totalPages){
        return api(status,true, object, totalElements, totalPages);
    }

    public ApiResponse apiSuccessObject(Object object){
        return api(StatusString.SUCCESS,true,object);
    }

    public ApiResponse apiSuccessObject(Object object,long totalElements, Integer totalPages){
        return api(StatusString.SUCCESS,true, object, totalElements, totalPages);
    }




    /** For data transfer objects (Dto) **/

    public ProductDto productToDto(Product product){
        return ProductDto
                .builder()
                .category(product.getCategory())
                .description(product.getDescription())
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .build();
    }

    public CustomerDto customerToDto(Customer customer){
        return CustomerDto
                .builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .country(customer.getCountry())
                .phone(customer.getPhone())
                .build();
    }


    public OrderDto orderToDto(Order order){
        return OrderDto
                .builder()
                .id(order.getId())
                .cust_id(order.getCustomer().getId())
                .date(order.getDate())
                .details(detailRepository.findByOrder(order).stream().map(this::detailToDto).collect(Collectors.toList()))
                .build();
    }


    public InvoiceDto invoiceToDto(Invoice invoice){
        return InvoiceDto
                .builder()
                .id(invoice.getId())
                .order(orderToDto(invoice.getOrder()))
                .amount(invoice.getAmount())
                .issued(invoice.getIssued())
                .due(invoice.getDue())
                .build();
    }



    public PaymentDto paymentToDto(Payment payment){
        return PaymentDto
                .builder()
                .id(payment.getId())
                .time(payment.getTime())
                .amount(payment.getAmount())
                .invoice(invoiceToDto(payment.getInvoice()))
                .build();
    }


    public DetailDto detailToDto(Detail detail){
        return DetailDto
                .builder()
                .id(detail.getId())
                .quantity(detail.getQuantity())
                .order_id(detail.getOrder().getId())
                .product_id(detail.getProduct().getId())
                .product_name(detail.getProduct().getName())
                .build();
    }

}
