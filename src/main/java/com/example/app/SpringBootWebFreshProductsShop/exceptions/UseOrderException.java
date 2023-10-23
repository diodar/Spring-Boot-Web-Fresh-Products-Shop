package com.example.app.SpringBootWebFreshProductsShop.exceptions;

import java.util.HashMap;
import java.util.Map;

public class UseOrderException extends RuntimeException {

    Map<String, String> errors;

    public UseOrderException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public String getErrors(Map<String, String> errors) {
        this.errors = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        errors.forEach((key, value) ->
                stringBuilder.append(key)
                        .append(": ")
                        .append(value)
                        .append("<br>")
        );
        return stringBuilder.toString();
    }
}
