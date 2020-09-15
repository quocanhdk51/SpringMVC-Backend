package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class SaleInvoiceController {

    @Autowired
    private SaleInvoiceService saleInvoiceService;


    @RequestMapping(path = "sale-invoices", method = RequestMethod.GET)
    public List<SaleInvoice> getAllSaleInvoices() {
        return saleInvoiceService.getAllSaleInvoices();
    }

    @RequestMapping(path = "sale-invoices", method = RequestMethod.POST)
    public SaleInvoice addSaleInvoice(@RequestBody SaleInvoice saleInvoice) {
        return saleInvoiceService.saveSaleInvoice(saleInvoice);
    }

    @RequestMapping(path = "sale-invoices", method = RequestMethod.PUT)
    public SaleInvoice editSaleInvoice(@RequestBody SaleInvoice saleInvoice) {
        return saleInvoiceService.updateSaleInvoice(saleInvoice);
    }

    @RequestMapping(path = "sale-invoices/{id}", method = RequestMethod.GET)
    public SaleInvoice getSaleInvoiceByID(@PathVariable int id) {
        return saleInvoiceService.getSaleInvoice(id);
    }

    //Please add the Date in the correct format: "dd-MM-yyy"
    @RequestMapping(path = "sale-invoices/revenue", method = RequestMethod.GET)
    public double getRevenueBetween(@RequestParam String start, @RequestParam String end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);
            return saleInvoiceService.getRevenue(startDate, endDate);
        } catch (ParseException e) {
            return 0;
        }
    }
}
