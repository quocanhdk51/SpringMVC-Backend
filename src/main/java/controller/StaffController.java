package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ProductService;
import service.ProviderService;
import service.StaffService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping(path = "staffs/search", method = RequestMethod.GET)
    public List<Staff> getStaffsByName(@RequestParam String s){
        return staffService.findStaffsByName(s);
    }

    @RequestMapping(path = "staffs", method = RequestMethod.GET)
    public List<Staff> getAllStaffs() {
        return staffService.getAllStaffs();
    }

    @RequestMapping(path = "staffs", method = RequestMethod.POST)
    public Staff addStaffs(@RequestBody Staff staff) {
        return staffService.saveStaff(staff);
    }

    @RequestMapping(path = "staffs", method = RequestMethod.PUT)
    public Staff editStaff(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }

    @RequestMapping(path = "staffs/{id}", method = RequestMethod.GET)
    public Staff getStaffByID(@PathVariable int id) {
        return staffService.getStaff(id);
    }

    @RequestMapping(path = "staffs/{id}/orders", method = RequestMethod.GET)
    public List<Order> getOrdersByID(@PathVariable int id) {
        return staffService.getOrdersByID(id);
    }

    @RequestMapping(path = "staffs/{id}/receiving-notes", method = RequestMethod.GET)
    public List<ReceivingNote> getReceivingNotesByID(@PathVariable int id) {
        return staffService.getReceivingNotesByID(id);
    }

    @RequestMapping(path = "staffs/{id}/sale-invoices", method = RequestMethod.GET)
    public List<SaleInvoice> getSaleInvoicesByID(@PathVariable int id) {
        return staffService.getSaleInvoicesByID(id);
    }

    //Please add the Date in the correct format: "dd-MM-yyy"
    @RequestMapping(path = "staffs/{id}/revenue", method = RequestMethod.GET)
    public double getRevenueByStaff(@PathVariable int id, @RequestParam String start, @RequestParam String end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);
            return staffService.getRevenueByStaff(id, startDate, endDate);
        } catch (ParseException e) {
            return 0;
        }
    }
}
