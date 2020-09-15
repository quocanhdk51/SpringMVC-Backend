package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;

@Transactional
@Service
public class CustomerService {
    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Customer saveCustomer(Customer customer){

        sessionFactory.getCurrentSession().save(customer);

        return customer;
    }

    public Customer getCustomer(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where id=:id");
        query.setInteger("id", id);

        return (Customer) query.uniqueResult();

    }


    public List<Customer> getAllCustomers(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer");
        return query.list();

    }

    public Customer updateCustomer(Customer customer) {
        this.sessionFactory.getCurrentSession().update(customer);
        return customer;
    }

    public List<Customer> findCustomersByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer p where p.name like :name");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    public List<SaleInvoice> getSaleInvoicesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice s where s.customer.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<SaleInvoice> getSaleInvoicesByCustomerBetween(int id, Date start, Date end) {
        List<SaleInvoice> list = getSaleInvoicesByID(id);
        List<SaleInvoice> result = new ArrayList<>();
        for (SaleInvoice invoice : list) {
            if (isInPeriod(invoice.getDate(), start, end))
                result.add(invoice);
        }
        return result;
    }

    private boolean isInPeriod(Date date, Date start, Date end) {
        return date.before(end) && date.after(start);
    }

    public double getRevenueByCustomer(int id, Date start, Date end) {
        double result = 0;
        List<SaleInvoice> list = getSaleInvoicesByCustomerBetween(id, start, end);
        for (SaleInvoice invoice : list)
            result += invoice.getTotal();
        return result;
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        Customer customer = getCustomer(id);
        this.sessionFactory.getCurrentSession().delete(customer);
        return id;
    }
}
