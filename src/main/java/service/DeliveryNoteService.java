package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CoT on 10/14/17.
 */
@Transactional
@Service
public class DeliveryNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DeliveryNote saveDeliveryNote(DeliveryNote deliveryNote){
        sessionFactory.getCurrentSession().save(deliveryNote);

        return deliveryNote;
    }

    public DeliveryNote updateDeliveryNote(DeliveryNote deliveryNote) {
        this.sessionFactory.getCurrentSession().update(deliveryNote);
        return deliveryNote;
    }


    public DeliveryNote getDeliveryNote(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote where id=:id");
        query.setInteger("id", id);

        return (DeliveryNote) query.uniqueResult();

    }


    public List<DeliveryNote> getAllDeliveryNotes(){
        Query query = sessionFactory.getCurrentSession().createQuery("from DeliveryNote");
        return query.list();

    }

    public List<DeliveryNote> getDeliveryNotesBetween(Date start, Date end) {
        List<DeliveryNote> list = getAllDeliveryNotes();
        List<DeliveryNote> result = new ArrayList<>();
        for (DeliveryNote note : list) {
            if (isInPeriod(note.getDate(), start, end))
                result.add(note);
        }
        return result;
    }

    private boolean isInPeriod(Date date, Date start, Date end) {
        return date.before(end) && date.after(start);
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        DeliveryNote deliveryNote = getDeliveryNote(id);
        this.sessionFactory.getCurrentSession().delete(deliveryNote);
        return id;
    }
}
