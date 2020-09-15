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
public class ReceivingNoteController {

    @Autowired
    private ReceivingNoteService receivingNoteService;


    @RequestMapping(path = "receiving-notes", method = RequestMethod.GET)
    public List<ReceivingNote> getAllReceivingNotes() {
        return receivingNoteService.getAllReceivingNotes();
    }

    @RequestMapping(path = "receiving-notes", method = RequestMethod.POST)
    public ReceivingNote addReceivingNote(@RequestBody ReceivingNote receivingNote) {
        return receivingNoteService.saveReceivingNote(receivingNote);
    }

    @RequestMapping(path = "receiving-notes", method = RequestMethod.PUT)
    public ReceivingNote editReceivingNote(@RequestBody ReceivingNote receivingNote) {
        return receivingNoteService.updateReceivingNote(receivingNote);
    }

    @RequestMapping(path = "receiving-notes/{id}", method = RequestMethod.GET)
    public ReceivingNote getReceivingNoteByID(@PathVariable int id) {
        return receivingNoteService.getReceivingNote(id);
    }

    //Please add the Date in the correct format: "dd-MM-yyy"
    @RequestMapping(path = "receiving-notes/between", method = RequestMethod.GET)
    public List<ReceivingNote> getReceivingNotesBetween(@RequestParam String start, @RequestParam String end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = format.parse(start);
            Date endDate = format.parse(end);
            return receivingNoteService.getReceivingNotesBetween(startDate, endDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
