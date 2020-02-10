package com.example.alba.service;

import com.example.alba.RecordNotFoundException;
import com.example.alba.entity.Product;
import com.example.alba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> resultProducts = (List<Product>) productRepository.findAll();
        if (resultProducts.size() > 0) {
            return resultProducts;
        } else {
            return new ArrayList<>();
        }
    }

    public Product getProductByID(Integer id) throws RecordNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else throw new RecordNotFoundException("No products found");
    }

    public Product createOrUpdateProduct(Product product) {
        if (product.getProductId() == null) {
            product = productRepository.save(product);
            return product;

        } else {
            Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
            if (optionalProduct.isPresent()) {
                Product newEntity = optionalProduct.get();
                newEntity.setName(product.getName());
                newEntity.setPrice(product.getPrice());

                newEntity = productRepository.save(newEntity);
                return newEntity;
            } else {
                product = productRepository.save(product);
                return product;
            }
        }
    }

    public void deleteProduct(Integer id) throws RecordNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No product found");
        }
    }
}
