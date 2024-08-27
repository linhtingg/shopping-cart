package com.mnb.controller;

import com.mnb.entity.Product;
import com.mnb.service.ProductService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {
    final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProduct(Model theModel) {
        // get employees from db
        List<Product> theProduct = productService.findAll();
        // add to the spring model
        theModel.addAttribute("product", theProduct);
        return "list-product";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Product theProduct = new Product();
        theModel.addAttribute("product", theProduct);
        return "product-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int theID, Model theModel) {
        //get the book from the service
        Product theProduct = productService.findById(theID);
        //set book as a model attribute to pre-populate the form
        theModel.addAttribute("product", theProduct);
        return "product-form";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product theProduct) {
        // save the book
        productService.save(theProduct);
        // use a redirect to prevent duplicate submissions
        return "redirect:/product/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int theId) {
        // delete the book
        productService.deleteById(theId);
        return "redirect:/product/list";
    }

    @GetMapping("/search")
    public String findProductByName(Model model, @Param("keyword") String keyword){
        model.addAttribute("product", productService.findProductByName(keyword));
        return "list-product";
    }

    @GetMapping("/catalog")
    public String findProductByCatalog(Model model, @Param("id") int id){
        model.addAttribute("product", productService.findProductByCatalog(id));
        return "list-product";
    }
}
