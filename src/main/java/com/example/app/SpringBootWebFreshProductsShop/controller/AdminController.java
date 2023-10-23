package com.example.app.SpringBootWebFreshProductsShop.controller;

import com.example.app.SpringBootWebFreshProductsShop.service.Admin.AdminFruitService;
import com.example.app.SpringBootWebFreshProductsShop.service.Admin.AdminOrderService;
import com.example.app.SpringBootWebFreshProductsShop.service.Admin.AdminVegetService;
import com.example.app.SpringBootWebFreshProductsShop.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER =
            Logger.getLogger(AdminController.class.getName());

    @Autowired
    AdminVegetService adminVegetService;
    @Autowired
    AdminFruitService adminFruitService;
    @Autowired
    AdminOrderService adminOrderService;

    @GetMapping
    public String getHome(Model model) {
        model.addAttribute("title", "Web Shop");
        model.addAttribute("fragmentName", "home");
        return "Admin/layout";
    }

    @GetMapping("/vegets")
    public String getVegets(Model model) {
        model.addAttribute("title", "Vegetables");
        model.addAttribute("vegets", adminVegetService.getAll());
        model.addAttribute("fragmentName", "vegets");
        model.addAttribute("endpoint", "vegets");
        return "Admin/layout";
    }

    @GetMapping("/fruits")
    public String getFruits(Model model) {
        model.addAttribute("title", "Fruits");
        model.addAttribute("fruits", adminFruitService.getAll());
        model.addAttribute("fragmentName", "fruits");
        model.addAttribute("endpoint", "fruits");
        return "Admin/layout";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        model.addAttribute("title", "Orders");
        model.addAttribute("orders", adminOrderService.getAll());
        model.addAttribute("fragmentName", "orders");
        return "Admin/layout";
    }

    @PostMapping("/create-fruit")
    public ResponseEntity<?> createFruit(
            @RequestParam("fruitName") String fruitName,
            @RequestParam("fruitArt") String fruitArt,
            @RequestParam("fruitDescr") String fruitDescr,
            @RequestParam("fruitPrice") String fruitPrice,
            @RequestParam("fruitFile") MultipartFile fruitFile) {
        String fileName = fruitFile.getOriginalFilename();
        String[] data = new String[]{fruitName, fruitArt, fruitDescr,
                fruitPrice, fileName};

        LOGGER.info("Admin add fruit data: " + Arrays.toString(data));
        ResponseEntity<?> response;
        try {
            response = adminFruitService.addFruit(data, fruitFile);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(false,
                    "Error :("), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/create-veget")
    public ResponseEntity<?> createVeget(
            @RequestParam("vegetName") String vegetName,
            @RequestParam("vegetArt") String vegetArt,
            @RequestParam("vegetDescr") String vegetDescr,
            @RequestParam("vegetPrice") String vegetPrice,
            @RequestParam("vegetFile") MultipartFile vegetFile) {
        String fileName = vegetFile.getOriginalFilename();
        String[] data = new String[]{vegetName, vegetArt, vegetDescr,
                vegetPrice, fileName};

        LOGGER.info("Admin add vegetable data: " + Arrays.toString(data));
        ResponseEntity<?> response;
        try {
            response = adminVegetService.addVeget(data, vegetFile);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(false,
                    "Error :("), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
