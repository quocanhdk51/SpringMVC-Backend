package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "products/search", method = RequestMethod.GET)
    public List<Product> getProductsByName(@RequestParam String s){
        return productService.findProductsByName(s);
    }

    @RequestMapping(path = "products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @RequestMapping(path = "products", method = RequestMethod.PUT)
    public Product editProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @RequestMapping(path = "products/{id}", method = RequestMethod.GET)
    public Product getProductByID(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @RequestMapping(path = "products/{id}/categories", method = RequestMethod.GET)
    public List<Category> getCategoriesByID(@PathVariable int id) {
        return productService.getCategoriesByID(id);
    }

    @RequestMapping(path = "products/{id}/order-details", method = RequestMethod.GET)
    public List<OrderDetail> getOrderDetailByID(@PathVariable int id) {
        return productService.getOrderDetailsByID(id);
    }

    @RequestMapping(path = "products/{id}/delivery-notes", method = RequestMethod.GET)
    public List<DeliveryNote> getDeliveryNotesByID(@PathVariable int id) {
        return productService.getDeliveryNotesByID(id);
    }

    @RequestMapping(path = "products/{id}/sale-invoices", method = RequestMethod.GET)
    public List<SaleInvoice> getSaleInvoicesByID(@PathVariable int id) {
        return productService.getSaleInvoicesByID(id);
    }

    //Please add the Date in the correct format: "dd-MM-yyy"
    @RequestMapping(path = "products/inventory", method = RequestMethod.GET)
    public String getInventoryBetween(@RequestParam String start, @RequestParam String end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);
            return productService.getInventoryBetween(startDate, endDate);
        } catch (ParseException e) {
            return "Wrong Date FormatX";
        }

    }
}
