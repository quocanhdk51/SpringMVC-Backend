package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by CoT on 10/14/17.
 */
@Transactional
@Service
public class OrderDetailService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail){

        if (orderDetail.getQuantity() != 0 && orderDetail.getPrice() == 0) {
            if (orderDetail.getProduct() != null) {
                Product product = (Product) sessionFactory.getCurrentSession()
                        .createQuery("from Product where id=:id")
                        .setInteger("id", orderDetail.getProduct().getId())
                        .uniqueResult();
                double price = product.getPrice()*orderDetail.getQuantity();
                orderDetail.setPrice(price);
            }
        }

        sessionFactory.getCurrentSession().save(orderDetail);

        return orderDetail;
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {

        if (orderDetail.getQuantity() != 0 && orderDetail.getPrice() == 0) {
            if (orderDetail.getProduct() != null) {
                Product product = (Product) sessionFactory.getCurrentSession()
                        .createQuery("from Product where id=:id")
                        .setInteger("id", orderDetail.getProduct().getId())
                        .uniqueResult();
                double price = product.getPrice()*orderDetail.getQuantity();
                orderDetail.setPrice(price);
            }
        }

        this.sessionFactory.getCurrentSession().update(orderDetail);
        return orderDetail;
    }


    public OrderDetail getOrderDetail(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail where id=:id");
        query.setInteger("id", id);

        return (OrderDetail) query.uniqueResult();

    }


    public List<OrderDetail> getAllOrderDetails(){
        Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail");
        return query.list();
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        OrderDetail orderDetail = getOrderDetail(id);
        this.sessionFactory.getCurrentSession().delete(orderDetail);
        return id;
    }
}
