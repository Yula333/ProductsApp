package com.itproger.dao;

import com.itproger.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> index() {              //возвращаем наш список продуктов из БД
        // используем BeanPropertyRowMapper для перевода строк таблицы в объекты нашего класса
        return jdbcTemplate.query("SELECT * FROM Product", new BeanPropertyRowMapper<>(Product.class));
    }

    public Product show(int id) {               // возвращаем один продукт по id
        return jdbcTemplate.query("SELECT * FROM Product WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny().orElse(null);       //дополнить ВЫВЕСТИ ПУСТОЙ РЕЗУЛЬТАТ СО СТАТУСОМ 404!!!!
    }


    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO Product(name, specification, price, dateofcreate) VALUES(?, ?, ?, ?)",
                product.getName(), product.getSpecification(), product.getPrice(), product.getDateOfCreate());
    }

    public void update(int id, Product updateProduct){
        jdbcTemplate.update("UPDATE Product SET name=?, specification=?, price=?, dateofchange=? WHERE id=?",
                updateProduct.getName(), updateProduct.getSpecification(), updateProduct.getPrice(),
                updateProduct.getDateOfChange(), id);
    }

    public void delete(int id){
       jdbcTemplate.update("DELETE FROM Product WHERE id=?", id);

    }

}
