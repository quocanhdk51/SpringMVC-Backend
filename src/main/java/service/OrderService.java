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
public class OrderService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order saveOrder(Order order){

        sessionFactory.getCurrentSession().save(order);

        return order;
    }

    public Order updateOrder(Order order) {
        this.sessionFactory.getCurrentSession().update(order);
        return order;
    }


    public Order getOrder(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Order where id=:id");
        query.setInteger("id", id);

        return (Order) query.uniqueResult();

    }


    public List<Order> getAllOrders(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Order");
        return query.list();

    }

    public List<OrderDetail> getOrderDetailsByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail o where o.order.id=:id").setInteger("id", id);
        return query.list();
    }

    public ReceivingNote getReceivingNotesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote r where r.order.id=:id").setInteger("id", id);
        return (ReceivingNote) query.uniqueResult();
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        Order order = getOrder(id);
        this.sessionFactory.getCurrentSession().delete(order);
        return id;
    }

}
