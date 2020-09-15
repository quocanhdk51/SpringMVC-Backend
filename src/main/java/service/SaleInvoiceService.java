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
public class SaleInvoiceService {
    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SaleInvoice saveSaleInvoice(SaleInvoice saleInvoice) {

        if (saleInvoice.getQuantity() != 0) {
            if (saleInvoice.getPrice() == 0 && saleInvoice.getProduct() != null) {
                Product product = (Product) this.sessionFactory.getCurrentSession()
                        .createQuery("from Product where id=:id")
                        .setInteger("id", saleInvoice.getProduct().getId())
                        .uniqueResult();
                saleInvoice.setPrice(product.getPrice());
            }
            if (saleInvoice.getTotal() == 0) saleInvoice.setTotal(saleInvoice.getPrice()*saleInvoice.getQuantity());
        }

        sessionFactory.getCurrentSession().save(saleInvoice);

        return saleInvoice;
    }

    public SaleInvoice getSaleInvoice(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice where id=:id");
        query.setInteger("id", id);

        return (SaleInvoice) query.uniqueResult();

    }


    public List<SaleInvoice> getAllSaleInvoices(){
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice");
        return query.list();

    }

    public SaleInvoice updateSaleInvoice(SaleInvoice saleInvoice) {

        if (saleInvoice.getQuantity() != 0) {
            if (saleInvoice.getPrice() == 0 && saleInvoice.getProduct() != null) {
                Product product = (Product) this.sessionFactory.getCurrentSession()
                        .createQuery("from Product where id=:id")
                        .setInteger("id", saleInvoice.getProduct().getId())
                        .uniqueResult();
                saleInvoice.setPrice(product.getPrice());
            }
            if (saleInvoice.getTotal() == 0) saleInvoice.setTotal(saleInvoice.getPrice()*saleInvoice.getQuantity());
        }

        this.sessionFactory.getCurrentSession().update(saleInvoice);
        return saleInvoice;
    }

    private boolean isInPeriod(Date date, Date start, Date end) {
        return date.before(end) && date.after(start);
    }

    public List<SaleInvoice> getSaleInvoicesBetween(Date start, Date end) {
        List<SaleInvoice> list = getAllSaleInvoices();
        List<SaleInvoice> result = new ArrayList<>();
        for (SaleInvoice invoice : list) {
            if (isInPeriod(invoice.getDate(), start, end))
                result.add(invoice);
        }
        return result;
    }

    public double getRevenue(Date start, Date end) {
        double result = 0;
        List<SaleInvoice> list = getSaleInvoicesBetween(start, end);
        for (SaleInvoice invoice : list)
            result += invoice.getTotal();
        return result;
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        SaleInvoice saleInvoice = getSaleInvoice(id);
        this.sessionFactory.getCurrentSession().delete(saleInvoice);

        return id;
    }
}
