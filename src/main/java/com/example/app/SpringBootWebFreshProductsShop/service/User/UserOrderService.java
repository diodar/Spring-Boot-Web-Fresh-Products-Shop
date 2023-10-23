package com.example.app.SpringBootWebFreshProductsShop.service.User;

import com.example.app.SpringBootWebFreshProductsShop.entity.Order;
import com.example.app.SpringBootWebFreshProductsShop.exceptions.UseOrderException;
import com.example.app.SpringBootWebFreshProductsShop.repository.OrderRepository;
import com.example.app.SpringBootWebFreshProductsShop.utils.Constants;
import com.example.app.SpringBootWebFreshProductsShop.utils.ResponseMessage;
import com.example.app.SpringBootWebFreshProductsShop.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserOrderService {

    @Autowired
    OrderRepository repository;

    public ResponseEntity<?> makeOrder(String[] data) {
        Map<String, String> errors = validateData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UseOrderException("Check inputs", errors);
            } catch (UseOrderException e) {
                return new ResponseEntity<>(new ResponseMessage(false,
                        e.getErrors(errors)), HttpStatus.OK);
            }
        } else {
            String orderCode = StringGenerator.genStr();
            Order order = new Order();
            order.setUserName(data[0]);
            order.setUserPhone(data[1]);
            order.setUserEmail(data[2]);
            order.setContent(data[3]);
            order.setOrderCode(orderCode);
            order.setStatus(Constants.ORDER_DEFAULT_STATUS);
            repository.save(order);
            return new ResponseEntity<>(new ResponseMessage(true,
                    "Your order code is " + orderCode + ". Do not lost it, please."),
                    HttpStatus.OK);
        }
    }

    private Map<String, String> validateData(String[] data) {
        Map<String, String> errors = new HashMap<>();
        if (data[0].isEmpty() | data[0].equals(" "))
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (data[1].isEmpty() | data[1].equals(" "))
            errors.put("phone", Constants.INPUT_REQ_MSG);
        if (data[2].isEmpty() | data[2].equals(" "))
            errors.put("email", Constants.INPUT_REQ_MSG);
        if (data[3].isEmpty() | data[3].equals(" "))
            errors.put("content", Constants.INPUT_REQ_MSG);
        return errors;
    }
}
