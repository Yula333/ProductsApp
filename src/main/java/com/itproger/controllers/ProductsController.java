package com.itproger.controllers;

import com.itproger.dao.ProductDao;
import com.itproger.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import javax.validation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductDao productDao;

    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }


//    @GetMapping()
//    public String index(Model model) {
//        //Получим все продукты из DAO и передадим на отображение в представление
//        model.addAttribute("products", productDao.index());
////        model.addAttribute("products", productDao.showName("name"));     //под ключом "products" будет лежать список из продуктов
//        return "products/index";
//    }

    @GetMapping()
    public String index(Model model, @RequestParam(defaultValue ="") String name, @RequestParam(defaultValue = "0")int id) {
        //Получим все продукты из DAO и передадим на отображение в представление
        model.addAttribute("products", productDao.index());  //под ключом "products" будет лежать список из продуктов
            if(id != 0)
                model.addAttribute("products", productDao.show(id));
//        if(!name.equals(""))
//        model.addAttribute("products", productDao.showName(name));
        return "products/index";
    }


//    @GetMapping()
//    public String index(Model model) {
//        //Получим все продукты из DAO и передадим на отображение в представление
//
//        model.addAttribute("products", productDao.index());     //под ключом "products" будет лежать список из продуктов
//            return "products/index";
//    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //Получим один продукт из DAO и передадим на отображение в представление
        model.addAttribute("product", productDao.show(id));
        return "products/show";
    }

        @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product){
        return "products/new";      //название таймлиф шаблона, где будет лежать форма для создания нового продукта
    }

    @PostMapping()
    //метод принимает пост запрос и будет создавать новый продукт и добавлять в БД
    public String create(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult){
        //должны создать новый продукт, положить в него данные, которые пришли из формы и добавить продукт в БД

        if(bindingResult.hasErrors())
            return "products/new";

        productDao.save(product);
        return "redirect:/products";        //совершаем переход (редирект) на другую страницу
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productDao.show(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "products/edit";

        productDao.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productDao.delete(id);
        return "redirect:/products";
    }

}
