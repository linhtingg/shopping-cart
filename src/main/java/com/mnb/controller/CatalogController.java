package com.mnb.controller;

import com.mnb.entity.Catalog;
import com.mnb.service.CatalogService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalog")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatalogController {
    final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @GetMapping("/list")
    public String listCatalog(Model theModel) {

        // get publisher from db
        List<Catalog> theCatalog = catalogService.findAll();

        // add to the spring model
        theModel.addAttribute("catalog", theCatalog);

        return "list-catalog";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Catalog theCatalog = new Catalog();

        theModel.addAttribute("catalog", theCatalog);

        return "catalog-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int theID, Model theModel) {
        //get the publisher from the service
        Catalog theCatalog = catalogService.findById(theID);
        //set publisher as a model attribute to pre-populate the form
        theModel.addAttribute("catalog", theCatalog);
        return "catalog-form";
    }
    @PostMapping("/save")
    public String saveCatalog(@ModelAttribute("catalog") Catalog theCatalog) {

        // save the publisher
        catalogService.save(theCatalog);

        // use a redirect to prevent duplicate submissions
        return "redirect:/catalog/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int theId) {

        // delete the book
        catalogService.deleteById(theId);

        // redirect to /publisher/list
        return "redirect:/catalog/list";

    }
}
