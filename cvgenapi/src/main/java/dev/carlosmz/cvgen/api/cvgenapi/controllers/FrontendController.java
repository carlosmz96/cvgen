package dev.carlosmz.cvgen.api.cvgenapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping(value = {"/{path:^(?!api|v3|swagger).*}", "/**/{path:^(?!api|v3|swagger).*}"})
    public String redirect() {
        return "forward:/index.html";
    }
}