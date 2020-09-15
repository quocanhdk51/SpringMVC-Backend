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
public class CategoryService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Category saveCategory(Category category){
        sessionFactory.getCurrentSession().save(category);

        return category;
    }

    public Category updateCategory(Category category) {
        this.sessionFactory.getCurrentSession().update(category);
        return category;
    }


    public Category getCategory(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Category where id=:id");
        query.setInteger("id", id);

        return (Category) query.uniqueResult();

    }


    public List<Category> getAllCategories(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Category");
        return query.list();

    }

    public List<Category> findCategories(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Category c where c.name like :name");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    public List<Product> getProductsByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select p.product from ProductCategory p where p.category.id=:id").setInteger("id", id);
        return query.list();
    }

    private List<ProductCategory> getProductCategoryByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ProductCategory p where p.category.id=:id").setInteger("id", id);
        return query.list();
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        Category category = getCategory(id);
        this.sessionFactory.getCurrentSession().delete(category);
        return id;
    }
}
