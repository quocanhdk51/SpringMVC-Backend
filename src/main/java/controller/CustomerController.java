package controller;

import model.Category;
import model.Customer;
import model.Product;
import model.SaleInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;
import service.CustomerService;
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
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(path = "customers/search", method = RequestMethod.GET)
    public List<Customer> getAllCustomersByName(@RequestParam String s){
        return customerService.findCustomersByName(s);
    }

    @RequestMapping(path = "customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(path = "customers", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @RequestMapping(path = "customers", method = RequestMethod.PUT)
    public Customer editCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @RequestMapping(path = "customers/{id}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @RequestMapping(path = "customers/{id}/sale-invoices", method = RequestMethod.GET)
    public List<SaleInvoice> getSaleInvoicesByID(@PathVariable int id) {
        return customerService.getSaleInvoicesByID(id);
    }

    @RequestMapping(path = "customers/{id}/revenue", method = RequestMethod.GET)
    public double getRevenueByCustomer(@PathVariable int id, @RequestParam String start, @RequestParam String end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);
            return customerService.getRevenueByCustomer(id, startDate, endDate);
        } catch (ParseException e) {
            return 0;
        }
    }
}
