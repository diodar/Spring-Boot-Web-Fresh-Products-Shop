package com.example.app.SpringBootWebFreshProductsShop.controller;

import com.example.app.SpringBootWebFreshProductsShop.service.User.UserFruitService;
import com.example.app.SpringBootWebFreshProductsShop.service.User.UserOrderService;
import com.example.app.SpringBootWebFreshProductsShop.service.User.UserVegetsService;
import com.example.app.SpringBootWebFreshProductsShop.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.logging.Logger;

@Controller
public class UserController {

    private static final Logger LOGGER =
            Logger.getLogger(UserController.class.getName());

    @Autowired
    UserVegetsService userVegetsService;
    @Autowired
    UserFruitService userFruitService;
    @Autowired
    UserOrderService userOrderService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("title", "Web Shop");
        model.addAttribute("fragmentName", "home");
        return "User/layout";
    }

    @GetMapping("/vegets")
    public String getVegets(Model model) {
        model.addAttribute("title", "Vegetables");
        model.addAttribute("vegets", userVegetsService.getAll());
        model.addAttribute("fragmentName", "vegets");
        return "User/layout";
    }

    @GetMapping("/fruits")
    public String getFruits(Model model) {
        model.addAttribute("title", "Fruits");
        model.addAttribute("fruits", userFruitService.getAll());
        model.addAttribute("fragmentName", "fruits");
        return "User/layout";
    }

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        model.addAttribute("title", "Contacts");
        model.addAttribute("fragmentName", "contacts");
        return "User/layout";
    }

    @PostMapping("/order")
    public ResponseEntity<?> makeOrder(
            @RequestParam("userName") String userName,
            @RequestParam("userPhone") String userPhone,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("userOrderMsg") String userOrderMsg) {
        String[] data = new String[]{userName, userPhone,
                userEmail, userOrderMsg};

        LOGGER.info("User order data: " + Arrays.toString(data));
        ResponseEntity<?> response;
        try {
            response = userOrderService.makeOrder(data);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(false,
                    "Error :("), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
