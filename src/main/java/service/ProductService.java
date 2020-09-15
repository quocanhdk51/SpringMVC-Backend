package service;

import model.*;
import service.CategoryService;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CoT on 10/14/17.
 */
@Transactional
@Service
public class ProductService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CategoryService categoryService;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product saveProduct(Product product){

        sessionFactory.getCurrentSession().save(product);

        return product;
    }

    public Product getProduct(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Product where id=:id");
        query.setInteger("id", id);

        return (Product) query.uniqueResult();

    }


    public List<Product> getAllProducts(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Product");
        return query.list();

    }

    public Product updateProduct(Product product) {
        this.sessionFactory.getCurrentSession().update(product);
        return product;
    }

    public List<Product> findProductsByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Product p where p.name like :name");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    public List<Category> getCategoriesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select p.category from ProductCategory p where p.product.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<OrderDetail> getOrderDetailsByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail o where o.product.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<DeliveryNote> getDeliveryNotesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote d where d.product.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<SaleInvoice> getSaleInvoicesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice s where s.product.id=:id").setInteger("id", id);
        return query.list();
    }

    private boolean isInPeriod(Date date, Date start, Date end) {
        return date.before(end) && date.after(start);
    }

    private int getTotalDeliveryProductsBetween(int id, Date start, Date end) {
        List<DeliveryNote> list = getDeliveryNotesByID(id);
        int result = 0;
        for (DeliveryNote note : list) {
            if (isInPeriod(note.getDate(), start, end))
                result += note.getQuantity();
        }
        return result;
    }

    private int getTotalReceivingProductsBetween(int id, Date start, Date end) {
        List<OrderDetail> list = getOrderDetailsByID(id);
        int result = 0;
        for (OrderDetail detail : list) {
            ReceivingNote note = getReceivingNotesByID(detail.getOrder().getId());
            if (note != null && isInPeriod(note.getDate(), start, end))
                result += detail.getQuantity();
        }
        return result;
    }

    private ReceivingNote getReceivingNotesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote r where r.order.id=:id").setInteger("id", id);
        return (ReceivingNote) query.uniqueResult();
    }

    public String getInventoryBetween(Date start, Date end) {
        List<Product> products = getAllProducts();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String startDate = format.format(start);
        String endDate = format.format(end);
        String result = "Date: " + startDate + " - " + endDate + "\n";
        for (Product product : products) {
            int re = getTotalReceivingProductsBetween(product.getId(), start, end),
                    de = getTotalDeliveryProductsBetween(product.getId(), start, end);
            String productDetail = String.format("Product: id: %d, name: %s, received: %d, delivered: %d, balance: %d \n", product.getId(), product.getName(), re, de, re - de);
            result += productDetail;
        }
        return result;
    }

    private List<ProductCategory> getProductCategoryByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ProductCategory p where p.product.id=:id").setInteger("id", id);
        return query.list();
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        Product product = getProduct(id);
        this.sessionFactory.getCurrentSession().delete(product);
        return id;
    }
}
