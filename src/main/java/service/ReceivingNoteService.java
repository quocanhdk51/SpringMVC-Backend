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
public class ReceivingNoteService {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ReceivingNote saveReceivingNote(ReceivingNote receivingNote){
        List<ReceivingNote> receivingNotes = getAllReceivingNotes();
        for (ReceivingNote note : receivingNotes) {
            if (receivingNote.getOrder().getId() == note.getOrder().getId())
                return null;
        }
        sessionFactory.getCurrentSession().save(receivingNote);

        return receivingNote;
    }

    public ReceivingNote updateReceivingNote(ReceivingNote receivingNote) {
        this.sessionFactory.getCurrentSession().update(receivingNote);
        return receivingNote;
    }


    public ReceivingNote getReceivingNote(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote where id=:id");
        query.setInteger("id", id);

        return (ReceivingNote) query.uniqueResult();

    }


    public List<ReceivingNote> getAllReceivingNotes(){
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote");
        return query.list();

    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        ReceivingNote receivingNote = getReceivingNote(id);
        this.sessionFactory.getCurrentSession().delete(receivingNote);

        return id;
    }

    private boolean isInPeriod(Date date, Date start, Date end) {
        return date.before(end) && date.after(start);
    }

    public List<ReceivingNote> getReceivingNotesBetween(Date start, Date end) {
        List<ReceivingNote> list = getAllReceivingNotes();
        List<ReceivingNote> result = new ArrayList<>();
        for (ReceivingNote note : list) {
            if (isInPeriod(note.getDate(), start, end))
                result.add(note);
        }
        return result;
    }
}
