package com.example.app.SpringBootWebFreshProductsShop.service.User;

import com.example.app.SpringBootWebFreshProductsShop.entity.Veget;
import com.example.app.SpringBootWebFreshProductsShop.repository.VegetRepository;
import com.example.app.SpringBootWebFreshProductsShop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserVegetsService {

    @Autowired
    VegetRepository repository;

    public List<Veget> getAll() {
        Iterable<Veget> iterable = repository.findAll();
        List<Veget> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .map(veget -> new Veget(veget.getId(),
                                Constants.BASE_URL +
                                        Constants.URL_IMAGES_UPLOADS + veget.getImg(),
                                veget.getName(),
                                veget.getArticle(),
                                veget.getDescr(),
                                veget.getPrice()))
                        .toList();
        return new ArrayList<>(list);
    }
}
