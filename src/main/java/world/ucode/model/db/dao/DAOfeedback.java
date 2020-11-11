package world.ucode.model.db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.model.db.entetis.Feedback;
import world.ucode.model.db.entetis.Users;
import world.ucode.model.db.util.HibernateUtil;

import java.util.List;

public class DAOfeedback implements DAO<Feedback, Integer>{
    public List<Feedback> getAllFeedbackforlot(int lotid) {
        Transaction transaction = null;
        List <Feedback> listOfFeedback = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfFeedback = session.createQuery("from Feedback where lotId=:lotid", Feedback.class).setParameter("lotid", lotid).getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfFeedback;
    }

    @Override
    public void create(Feedback feedback) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(feedback);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public Feedback read(Integer key) {
        Transaction transaction = null;
        Feedback feedback = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            feedback = session.get(Feedback.class, key);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedback;

    }

    @Override
    public void update(Feedback feedback) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(feedback);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Feedback feedback = session.get(Feedback.class, id);
            if (feedback != null) {
                session.delete(feedback);
                System.out.println("feedback is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
