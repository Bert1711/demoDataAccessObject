package ru.zaroyan.demodataaccessobject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private static final String scriptProductName = "returnProduct_nameScript.sql";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


//    Возвращаем лист, так как продуктов может быть много
    public List<String> getProductName(String name) {
        String scriptQuery = read(scriptProductName);
        return namedParameterJdbcTemplate.queryForList(scriptQuery, new MapSqlParameterSource("name", name), String.class);
    }


    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
