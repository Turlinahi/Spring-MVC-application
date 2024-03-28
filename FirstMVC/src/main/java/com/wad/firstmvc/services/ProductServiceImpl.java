package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    List<Product> products = new ArrayList(List.of(
            new Product(13L, "ice cream", 4.99, "Food"),
            new Product(15L, "bike", 200.00, "Transportation"),
            new Product(19L, "car", 15000.00, "Transportation")
           ));


    @Override
    public List<Product> findProductsByCriteria(String category, Double minPrice, Double maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            boolean matchesCategory = true; // Assume match if criterion is not provided
            boolean matchesMinPrice = true; // Assume match if criterion is not provided
            boolean matchesMaxPrice = true; // Assume match if criterion is not provided

            if (category != null && !category.isEmpty()) {
                matchesCategory = product.getCategory().equalsIgnoreCase(category);
            }
            if (minPrice != null) {
                matchesMinPrice = product.getPrice() >= minPrice;
            }
            if (maxPrice != null) {
                matchesMaxPrice = product.getPrice() <= maxPrice;
            }

            if (matchesCategory && matchesMinPrice && matchesMaxPrice) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product p) {
        products.add(p);
    }
}
