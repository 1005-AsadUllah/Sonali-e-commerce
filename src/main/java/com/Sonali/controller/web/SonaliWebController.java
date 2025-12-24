package com.Sonali.controller.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping
public class SonaliWebController {


    @GetMapping
    public String print() {
        return "Hello Sonali";
    }

    @GetMapping("api/admin/products")
    public String getProductsForAdmin() {
        return "Products for Admin";
    }
}
