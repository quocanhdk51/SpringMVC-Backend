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

@Transactional
@Service
public class StaffService {
    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Staff saveStaff(Staff staff){

        sessionFactory.getCurrentSession().save(staff);

        return staff;
    }

    public Staff getStaff(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where id=:id");
        query.setInteger("id", id);

        return (Staff) query.uniqueResult();

    }


    public List<Staff> getAllStaffs(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff");
        return query.list();

    }

    public Staff updateStaff(Staff staff) {
        this.sessionFactory.getCurrentSession().update(staff);
        return staff;
    }

    public List<Staff> findStaffsByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff p where p.name like :name");

        query.setString("name", "%"+name+"%");

        return query.list();
    }

    public List<Order> getOrdersByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Order o where o.staff.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<ReceivingNote> getReceivingNotesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from ReceivingNote r where r.staff.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<SaleInvoice> getSaleInvoicesByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from SaleInvoice s where s.staff.id=:id").setInteger("id", id);
        return query.list();
    }

    public List<SaleInvoice> getSaleInvoicesByStaffBetween(int id, Date start, Date end) {
        List<SaleInvoice> list = getSaleInvoicesByID(id);
        List<SaleInvoice> result = new ArrayList<>();
        for (SaleInvoice invoice : list) {
            if (isInPeriod(invoice.getDate(), start, end))
                result.add(invoice);
        }
        return result;
    }

    public double getRevenueByStaff(int id, Date start, Date end) {
        double result = 0;
        List<SaleInvoice> list = getSaleInvoicesByStaffBetween(id, start, end);
        for (SaleInvoice invoice : list)
            result += invoice.getTotal();
        return result;
    }

    private boolean isInPeriod(Date date, Date start, Date end) {
        return date.before(end) && date.after(start);
    }

    //not recommend to use this method because it might corrupt a lot of data
    public int delete(int id) {
        Staff staff = getStaff(id);
        this.sessionFactory.getCurrentSession().delete(staff);

        return id;
    }
}
