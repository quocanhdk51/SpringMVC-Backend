package controller;

import model.Product;
import model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductCategoryService;
import service.ProductService;

import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(path = "product_categories", method = RequestMethod.GET)
    public List<ProductCategory> getAllStudent() {
        return productCategoryService.getAllProductCategories();
    }

    @RequestMapping(path = "product_categories", method = RequestMethod.POST)
    public ProductCategory addProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.saveProductCategory(productCategory);
    }

    @RequestMapping(path = "product_categories", method = RequestMethod.PUT)
    public ProductCategory editProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.updateProductCategory(productCategory);
    }

    @RequestMapping(path = "product_categories/{id}", method = RequestMethod.GET)
    public ProductCategory getProductCategoryByID(@PathVariable int id) {
        return productCategoryService.getProductCategory(id);
    }
}