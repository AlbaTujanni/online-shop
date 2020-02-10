package com.example.alba.controller;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.Product;
import com.example.alba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping
    public String getAllProducts(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "products";
    }

    @RequestMapping(path = {"/edit", "/edit{id}"})
    public String editProductByID(Model model, @PathVariable("id") Optional<Integer> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Product product = productService.getProductByID(id.get());
            model.addAttribute("product", product);

        } else {
            model.addAttribute("product", new Product());
        }
        return "add-edit-product";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createOrUpdateProduct(Product product) {
        productService.createOrUpdateProduct(product);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteEmployeeById(Model model, @PathVariable("id") Integer id) throws RecordNotFoundException {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
