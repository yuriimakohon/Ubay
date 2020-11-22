package world.ucode.model.db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

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

    public List<Bid> get_all_by_user(int id) {
        Transaction transaction;
        List<Bid> listOfBid = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfBid = session.createQuery("SELECT b from Bid b join Users u on b.bidderId = u.id where u.id = :id", Bid.class).setParameter("id", id).getResultList();
            transaction.commit();
        } catch (NoResultException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBid;
    }

    public List<Bid> get_all() {
        Transaction transaction;
        List<Bid> listOfBid = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//            listOfBid = session.createQuery("SELECT b from Bid b join Lot l on b.lotid = l.id where l.id = :id", Bid.class).setParameter("id", id).getResultList();
            listOfBid = session.createQuery("SELECT b FROM Bid b", Bid.class).getResultList();
            transaction.commit();
        } catch (NoResultException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBid;
    }

    public List<Bid> get_all_by_lot(int id) {
        Transaction transaction;
        List<Bid> listOfBid = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//            listOfBid = session.createQuery("SELECT b from Bid b join Lot l on b.lotid = l.id where l.id = :id", Bid.class).setParameter("id", id).getResultList();
            listOfBid = session.createQuery("SELECT b FROM Bid b WHERE b.id IN (SELECT id FROM Bid WHERE lotid=:id)", Bid.class).setParameter("id", id).getResultList();
            transaction.commit();
        } catch (NoResultException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBid;
    }

//    SELECT b.id, b.lotid, b.price, b.statusofBid from bid b join users u on u.id = b.bidderID where u.token = 'token3';
    public List<Bid> readByToken(String token) {
        Transaction transaction = null;
        List<Bid> listOfBid = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfBid = session.createQuery("SELECT b from Bid b join Users u on u.id = b.bidderId where u.token = :token", Bid.class).setParameter("token", token).getResultList();
            transaction.commit();
        } catch (NoResultException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfBid;
    }
}
