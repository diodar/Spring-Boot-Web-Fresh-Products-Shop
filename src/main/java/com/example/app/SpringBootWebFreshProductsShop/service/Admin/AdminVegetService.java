package com.example.app.SpringBootWebFreshProductsShop.service.Admin;

import com.example.app.SpringBootWebFreshProductsShop.entity.Veget;
import com.example.app.SpringBootWebFreshProductsShop.exceptions.UseOrderException;
import com.example.app.SpringBootWebFreshProductsShop.repository.VegetRepository;
import com.example.app.SpringBootWebFreshProductsShop.utils.Constants;
import com.example.app.SpringBootWebFreshProductsShop.utils.ResponseMessage;
import com.example.app.SpringBootWebFreshProductsShop.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

@Service
public class AdminVegetService {

    private static final Logger LOGGER =
            Logger.getLogger(AdminVegetService.class.getName());
    @Autowired
    VegetRepository repository;

    public ResponseEntity<?> addVeget(String[] data, MultipartFile file) throws IOException {
        Map<String, String> errors = validateData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UseOrderException("Check inputs", errors);
            } catch (UseOrderException e) {
                return new ResponseEntity<>(new ResponseMessage(false,
                        e.getErrors(errors)), HttpStatus.OK);
            }
        } else {
            String imgPrefix = StringGenerator.genStr();
            Veget veget = new Veget();
            veget.setName(data[0]);
            veget.setArticle(data[1]);
            veget.setDescr(data[2]);
            veget.setPrice(Double.parseDouble(data[3]));
            String fileUpload = imgPrefix + "-" + data[4];

            String directory = Constants.URL_FILE_UPLOADS;
            String filepath = Paths.get(directory, fileUpload).toString();

            LOGGER.info("Admin filepath: " + filepath);

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(filepath));
            stream.write(file.getBytes());
            stream.close();

            veget.setImg(fileUpload);
            repository.save(veget);

            return new ResponseEntity<>(new ResponseMessage(true,
                    Constants.SAVED_MSG), HttpStatus.OK);
        }
    }

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

    private Map<String, String> validateData(String[] data) {
        Map<String, String> errors = new HashMap<>();
        if (data[0].isEmpty() | data[0].equals(" "))
            errors.put("name", Constants.INPUT_REQ_MSG);
        if (data[1].isEmpty() | data[1].equals(" "))
            errors.put("article", Constants.INPUT_REQ_MSG);
        if (data[2].isEmpty() | data[2].equals(" "))
            errors.put("description", Constants.INPUT_REQ_MSG);
        if (data[3].isEmpty() | data[3].equals(" "))
            errors.put("price", Constants.INPUT_REQ_MSG);
        if (data[4].isEmpty() | data[4].equals(" "))
            errors.put("file", Constants.ADD_FILE_MSG);
        return errors;
    }
}
