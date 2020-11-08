package world.ucode.model.db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Users;
import world.ucode.model.db.util.HibernateUtil;

public class DAObid implements DAO<Bid, Integer>{


    @Override
    public void create(Bid bid) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bid);
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public Bid read(Integer integer) {
        Transaction transaction = null;
        Bid bid = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            bid = session.get(Bid.class, integer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bid;
    }

    @Override
    public void update(Bid bid) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(bid);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer integer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Bid bid = session.get(Bid.class, integer);
            if (bid != null) {
                session.delete(bid);
                System.out.println("bid is deleted");
            }
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }



}
