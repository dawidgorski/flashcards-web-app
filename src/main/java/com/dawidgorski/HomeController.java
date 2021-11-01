package com.dawidgorski;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(MappingNames.HOME_MAPPING)
    public String getIndex(){
        return ViewNames.HOME ;
    }
    @GetMapping("home_view")
    public String getHome(){
        return "home_view" ;
    }
}
