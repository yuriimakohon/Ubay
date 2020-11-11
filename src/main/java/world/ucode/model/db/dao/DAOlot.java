package world.ucode.model.db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.model.db.entetis.Bid;
import world.ucode.model.db.entetis.Lot;
import world.ucode.model.db.entetis.Users;
import world.ucode.model.db.util.HibernateUtil;

import java.util.List;

public class DAOlot implements DAO<Lot, Integer>{
    @Override
    public void create(Lot lot) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(lot);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Lot read(Integer integer) {
        Transaction transaction = null;
        Lot lot = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            lot = session.get(Lot.class, integer);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lot;
    }

    @Override
    public void update(Lot lot) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(lot);
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
            Lot lot = session.get(Lot.class, integer);
            if (lot != null) {
                session.delete(lot);
                System.out.println("lot is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Lot> getAllLots() {
        Transaction transaction = null;
        List <Lot> listOfLot = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfLot = session.createQuery("from Lot ", Lot.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfLot;
    }

    public List<Lot> getAllLotsbyCategoris() {
        Transaction transaction = null;
        List <Lot> listOfLot = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String cate = "Sport";

            String qua = "from Lot where ";
            for (int i = 0; i < 1; i++) {
                qua = qua + "category like :" + cate;
            }


            //String qua = "from Lot where category like :category1";
            listOfLot = session.createQuery(qua, Lot.class).setParameter(cate, "%"+cate+"%").getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfLot;
    }
}
