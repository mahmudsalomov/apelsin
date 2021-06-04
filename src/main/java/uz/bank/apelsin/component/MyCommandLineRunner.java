package uz.bank.apelsin.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.bank.apelsin.model.Customer;
import uz.bank.apelsin.model.Role;
import uz.bank.apelsin.model.template.RoleName;
import uz.bank.apelsin.repository.CustomerRepository;
import uz.bank.apelsin.repository.RoleRepository;

import java.util.Collections;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {



        Role manager= Role.builder()
                .roleName(RoleName.ROLE_MANAGER)
                .build();
        roleRepository.save(manager);
        if (!customerRepository.existsByName("admin")){
            Customer user=Customer.builder()
                    .name("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles(Collections.singleton(manager))
                    .address("Samarkand")
                    .country("UZB")
                    .phone("+998991234567")
                    .build();
            System.out.println(customerRepository.save(user));
        }


        Role customer= Role.builder()
                .roleName(RoleName.ROLE_CUSTOMER)
                .build();
        roleRepository.save(customer);
        if (!customerRepository.existsByName("customer")){
            Customer user1=Customer.builder()
                    .name("customer")
                    .password(passwordEncoder.encode("customer"))
                    .roles(Collections.singleton(customer))
                    .address("Samarkand")
                    .country("UZB")
                    .phone("+998991234568")
                    .build();
            System.out.println(customerRepository.save(user1));
        }
    }
}
