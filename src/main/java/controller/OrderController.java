package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CategoryService;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "orders", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(path = "orders", method = RequestMethod.POST)
    public Order addOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @RequestMapping(path = "orders", method = RequestMethod.PUT)
    public Order editOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(path = "orders/{id}", method = RequestMethod.GET)
    public Order getOrderByID(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @RequestMapping(path = "orders/{id}/order-details", method = RequestMethod.GET)
    public List<OrderDetail> getOrderDetailsByID(@PathVariable int id) {
        return orderService.getOrderDetailsByID(id);
    }

    @RequestMapping(path = "orders/{id}/receiving-notes", method = RequestMethod.GET)
    public ReceivingNote getReceivingNoteByID(@PathVariable int id) {
        return orderService.getReceivingNotesByID(id);
    }


}
