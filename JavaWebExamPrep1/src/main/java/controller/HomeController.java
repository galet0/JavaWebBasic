package controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {

    @GetMapping("/")
    public String details(){
        return "home";
    }
}
