package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;
import service.CustomerService;
import service.DeliveryNoteService;
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
public class DeliveryNoteController {

    @Autowired
    private DeliveryNoteService deliveryNoteService;


    @RequestMapping(path = "delivery-notes", method = RequestMethod.GET)
    public List<DeliveryNote> getAllDeliveryNotes() {
        return deliveryNoteService.getAllDeliveryNotes();
    }

    @RequestMapping(path = "delivery-notes", method = RequestMethod.POST)
    public DeliveryNote addDeliveryNote(@RequestBody DeliveryNote deliveryNote) {
        return deliveryNoteService.saveDeliveryNote(deliveryNote);
    }

    @RequestMapping(path = "delivery-notes", method = RequestMethod.PUT)
    public DeliveryNote editDeliveryNote(@RequestBody DeliveryNote deliveryNote) {
        return deliveryNoteService.updateDeliveryNote(deliveryNote);
    }

    @RequestMapping(path = "delivery-notes/{id}", method = RequestMethod.GET)
    public DeliveryNote getDeliveryNoteByID(@PathVariable int id) {
        return deliveryNoteService.getDeliveryNote(id);
    }

    //Please add the Date in the correct format: "dd-MM-yyy"
    @RequestMapping(path = "delivery-notes/between", method = RequestMethod.GET)
    public List<DeliveryNote> getDeliveryNotesBetween(@RequestParam String start, @RequestParam String end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);
            return deliveryNoteService.getDeliveryNotesBetween(startDate, endDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
