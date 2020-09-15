package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;
import service.ProductService;

import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "categories/search", method = RequestMethod.GET)
    public List<Category> getAllCategoriesByName(@RequestParam String s){
        return categoryService.findCategories(s);
    }

    @RequestMapping(path = "categories", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(path = "categories", method = RequestMethod.POST)
    public Category addCategories(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @RequestMapping(path = "categories", method = RequestMethod.PUT)
    public Category editCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @RequestMapping(path = "categories/{id}", method = RequestMethod.GET)
    public Category getCategoryByID(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @RequestMapping(path = "categories/{id}/products", method = RequestMethod.GET)
    public List<Product> getProductsByID(@PathVariable int id) {
        return categoryService.getProductsByID(id);
    }
}
