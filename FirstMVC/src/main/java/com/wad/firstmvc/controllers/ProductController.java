package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.Product;
import com.wad.firstmvc.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String viewProducts(Model model){
        model.addAttribute("products", productService.findAll());
        return "products"; // Make sure a Thymeleaf template called 'products.html' exists.
    }

    @GetMapping("/new")
    public String showAddProductForm(Model model){
        model.addAttribute("product", new Product());
        return "addproducts"; // Make sure a Thymeleaf template called 'addproducts.html' exists.
    }

    @PostMapping("/new")
    public String addProduct(Product product){
        if(product.getId() == null)
            product.setId(new Random().nextLong());
        productService.save(product);
        return "redirect:/products"; // Redirects to the viewProducts method after saving the product.
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "searchProducts"; // Make sure a Thymeleaf template called 'searchProducts.html' exists.
    }

    @PostMapping("/search")
    public String searchProducts(@RequestParam(required = false) String category,
                                 @RequestParam(required = false) Double minPrice,
                                 @RequestParam(required = false) Double maxPrice,
                                 Model model) {
        List<Product> searchResults = productService.findProductsByCriteria(category, minPrice, maxPrice);
        model.addAttribute("products", searchResults); // 'products' is used in your 'products.html' Thymeleaf template.
        return "products"; // Use 'products.html' to display the search results.
    }

}
