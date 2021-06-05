package uz.bank.apelsin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bank.apelsin.payload.ApiResponse;
import uz.bank.apelsin.repository.DetailRepository;
import uz.bank.apelsin.utils.Converter;

@Service
public class DetailService {
    @Autowired
    private Converter converter;
    @Autowired
    private DetailRepository detailRepository;
    public ApiResponse getAll() {
        try {
            return converter.apiSuccess(detailRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return converter.apiError();
        }
    }
}
