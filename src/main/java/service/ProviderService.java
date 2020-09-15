package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.PropertyResourceBundle;

@Transactional
@Service
public class ProviderService {
    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Provider saveProvider(Provider provider){

        sessionFactory.getCurrentSession().save(provider);

        return provider;
    }

    public Provider getProvider(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider where id=:id");
        query.setInteger("id", id);

        return (Provider) query.uniqueResult();

    }


    public List<Provider> getAllProviders(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider");
        return query.list();

    }

    public Provider updateProvider(Provider provider) {
        this.sessionFactory.getCurrentSession().update(provider);
        return provider;
    }

    public List<Provider> findProvidersByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Provider p where p.name like :name");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    public List<Order> getOrdersByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order o where o.provider.id=:id").setInteger("id", id);
        return query.list();
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        Provider provider = getProvider(id);
        this.sessionFactory.getCurrentSession().delete(provider);
        return id;
    }
}
