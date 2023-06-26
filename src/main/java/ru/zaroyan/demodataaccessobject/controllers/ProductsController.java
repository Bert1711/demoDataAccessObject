package ru.zaroyan.demodataaccessobject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zaroyan.demodataaccessobject.dao.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductRepository repository;

    @Autowired
    public ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/fetch-product")
    public List<String> getListProductName(String name) {
        return repository.getProductName(name);
    }
}
