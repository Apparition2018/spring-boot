package com.ljh.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author Arsenal
 * created on 2020/1/2 23:44
 */
@RestController
public class SecurityController {
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * postman Authorization → Type: Basic Auth → Username: demo & Password: demo
     */
    @RequestMapping("/common")
    public String commonAccess() {
        return "only login can view";
    }

    /**
     * postman Authorization → Type: Basic Auth → Username: admin & Password: admin
     */
    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "only admin can access";
    } 
    
}
