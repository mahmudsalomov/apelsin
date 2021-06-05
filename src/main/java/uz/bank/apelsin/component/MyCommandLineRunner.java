package uz.bank.apelsin.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.bank.apelsin.model.*;
import uz.bank.apelsin.repository.*;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernate_status;

    @Override
    public void run(String... args) throws Exception {

        /** Test data **/
//        if (hibernate_status.equals("create")){


            // Categories
            Category a = categoryCreator("A");
            Category b = categoryCreator("B");
            Category c = categoryCreator("C");
            Category d = categoryCreator("D");


            // Products
            Product pen = productCreator("pen", a, "a", "a", 3.12);
            Product eraser = productCreator("eraser", a, "a", "a", 4.33);

            Product apple = productCreator("apple", b, "apple", "apple", 5.5);
            Product orange = productCreator("orange", b, "orange", "orange", 6.05);
            Product banana = productCreator("banana", b, "banana", "banana", 4);
            Product cherry = productCreator("cherry", b, "cherry", "cherry", 3.1);


            Product phone = productCreator("phone", c, "phone", "phone", 100);

            Product bmw = productCreator("bmw", d, "bmw", "bmw", 3000);
            Product audi = productCreator("audi", d, "audi", "audi", 2800);

            Product shoes = productCreator("shoes", null, "shoes", "shoes", 25.35);


            // Customers
            Customer anvar = customerCreator("Anvar", "UZB", "Tashkent", "+998991234567");
            Customer shavkat = customerCreator("Shavkat", "UZB", "Tashkent", "+998991234567");
            Customer mahmud = customerCreator("Mahmud", "UZB", "Samarkand", "+998991234567");

            Customer amit = customerCreator("Amit", "IND", "Delhi", "+998991234567");
            Customer akash = customerCreator("Akash", "IND", "Delhi", "+998991234567");

            Customer john = customerCreator("John", "USA", "Dallas", "+998991234567");
            Customer garry = customerCreator("Garry", "USA", "California", "+998991234567");

            Customer michael = customerCreator("Michael", "GBR", "London", "+998991234567");
            Customer sara = customerCreator("Sara", "GBR", "Manchester", "+998991234567");

            Customer sergiy = customerCreator("Sergiy", "RUS", "Moscow", "+998991234567");


            /** Orders **/

            // Normal
            Order order1 = orderCreator(convert("20160612"), anvar);
            Detail detail1 = detailCreator(order1, pen, (short) 3);
            Invoice invoice1 = invoiceCreator(order1, detail1.getQuantity() * pen.getPrice(), convert("20160612"), convert("20160711"));
            paymentCreator(invoice1,invoice1.getAmount(),new Timestamp(convert("20160613").getTime()));

            // Wrong date invoices
            Order order2 = orderCreator(convert("20160219"), john);
            Detail detail2 = detailCreator(order2, phone, (short) 1);
            Invoice invoice2 = invoiceCreator(order2, detail2.getQuantity() * phone.getPrice(), convert("20160218"), convert("20160711"));

            // Orders without details and before 2016-9-6
            Order order3 = orderCreator(convert("20160309"), anvar);
//            Detail detail3 = detailCreator(order1, pen, (short) 2);
            Invoice invoice3 = invoiceCreator(order3, 0, convert("20160309"), convert("20160409"));


            // Overpaid
            Order order4 = orderCreator(convert("20160524"), garry);
            Detail detail4 = detailCreator(order4, shoes, (short) 2);
            Invoice invoice4 = invoiceCreator(order4, detail4.getQuantity() * shoes.getPrice(), convert("20160524"), convert("20160624"));
            paymentCreator(invoice4,invoice4.getAmount(),new Timestamp(convert("20160526").getTime()));
            paymentCreator(invoice4,invoice4.getAmount(),new Timestamp(convert("20160517").getTime()));


            // High remand product
            Order order5 = orderCreator(convert("20170612"), sergiy);
            Detail detail5 = detailCreator(order5, apple, (short) 12);
            Invoice invoice5 = invoiceCreator(order5, detail5.getQuantity() * apple.getPrice(), convert("20170612"), convert("20170712"));
            paymentCreator(invoice5,invoice5.getAmount(),new Timestamp(convert("20170618").getTime()));


            // Bulk products
            Order order6 = orderCreator(convert("20170912"), sergiy);
            Detail detail6 = detailCreator(order6, apple, (short) 10);
            Invoice invoice6 = invoiceCreator(order6, detail6.getQuantity() * apple.getPrice(), convert("20170912"), convert("20171012"));
            paymentCreator(invoice6,invoice6.getAmount(),new Timestamp(convert("20170913").getTime()));




//        }


    }

    public Date convert(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = format.parse(date);
        return new Date(parsed.getTime());
    }

    public Category categoryCreator(String name){

            return categoryRepository.save(Category
                    .builder()
                    .name(name)
                    .build()
            );

    }

    public Customer customerCreator(String name,String country, String address, String phone){
            Customer user=Customer.builder()
                    .name(name)
                    .address(address)
                    .country(country)
                    .phone(phone)
                    .build();
            System.out.println(customerRepository.save(user));
            return user;
    }

    public Order orderCreator(Date date,Customer customer){
        return orderRepository.save(Order
                .builder()
                .date(date)
                .customer(customer)
                .build());
    }

    public Detail detailCreator(Order order,Product product,Short quantity){
        return detailRepository.save(Detail
                .builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .build());
    }

    public Product productCreator(String name,Category category, String description, String photo, double price){
        return productRepository.save(
                Product
                        .builder()
                        .name(name)
                        .category(category)
                        .description(description)
                        .photo(photo)
                        .price(price)
                        .build()
        );
    }

    public Invoice invoiceCreator(Order order, double amount,Date issued, Date due){
        return invoiceRepository.save(
                Invoice
                        .builder()
                        .order(order)
                        .amount(amount)
                        .issued(issued)
                        .due(due)
                        .build()
        );
    }

    private Payment paymentCreator(Invoice invoice, double amount, Timestamp time){
        return paymentRepository.save(
                Payment
                        .builder()
                        .invoice(invoice)
                        .amount(amount)
                        .time(time)
                        .build()
        );
    }


}
