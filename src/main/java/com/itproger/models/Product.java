package com.itproger.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Product {

    private final String create = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());

    private int id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    private String specification;

    @Min(value = 1, message = "Price should be greater than 0")
    private int price;
    private String dateOfCreate;
    private String dateOfChange;

    public Product() {
    }

    public Product(int id, String name, String specification, int price, String dateOfCreate, String dateOfChange) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.dateOfCreate = dateOfCreate;
        this.dateOfChange = dateOfChange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(String create) {
        this.dateOfCreate = create;
    }

    public String getDateOfChange() {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void setDateOfChange(String timeStamp) {
        timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        this.dateOfCreate = timeStamp;
    }
}
