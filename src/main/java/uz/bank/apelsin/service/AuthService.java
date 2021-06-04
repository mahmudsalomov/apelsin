package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.repository.CustomerRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return customerRepository.findByName(s);
    }
}
