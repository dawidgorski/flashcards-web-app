package com.dawidgorski.controller;

import com.dawidgorski.model.MappingNames;
import com.dawidgorski.model.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(MappingNames.HOME_MAPPING)
    public String getIndex() {
        return ViewNames.HOME;
    }
}
