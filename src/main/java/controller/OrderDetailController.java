package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.List;

/**
 * Created by CoT on 7/29/18.
 */
@RestController
@RequestMapping(path = "/")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    @RequestMapping(path = "order-details", method = RequestMethod.GET)
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @RequestMapping(path = "order-details", method = RequestMethod.POST)
    public OrderDetail addOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.saveOrderDetail(orderDetail);
    }

    @RequestMapping(path = "order-details", method = RequestMethod.PUT)
    public OrderDetail editOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.updateOrderDetail(orderDetail);
    }

    @RequestMapping(path = "order-details/{id}", method = RequestMethod.GET)
    public OrderDetail getOrderDetailByID(@PathVariable int id) {
        return orderDetailService.getOrderDetail(id);
    }
}
