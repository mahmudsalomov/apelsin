package uz.bank.apelsin.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/all")
    public String all(){
        return "all";
    }

}
