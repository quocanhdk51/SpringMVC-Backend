package service;

import model.Category;
import model.Product;
import model.ProductCategory;
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
public class ProductCategoryService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory){

        sessionFactory.getCurrentSession().save(productCategory);

        return productCategory;
    }

    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        this.sessionFactory.getCurrentSession().update(productCategory);
        return productCategory;
    }


    public ProductCategory getProductCategory(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ProductCategory where id=:id");
        query.setInteger("id", id);

        return (ProductCategory) query.uniqueResult();

    }


    public List<ProductCategory> getAllProductCategories(){
        Query query = sessionFactory.getCurrentSession().createQuery("from ProductCategory");
        return query.list();
    }

    public int delete(int id) {
        ProductCategory productCategory = getProductCategory(id);
        this.sessionFactory.getCurrentSession().delete(productCategory);
        return id;
    }

}
