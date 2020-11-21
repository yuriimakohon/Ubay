package world.ucode.model.db.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import world.ucode.model.db.entetis.Users;
import world.ucode.model.db.util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;


public class DAOusers implements DAO<Users, Integer>{
    public List<Users> getAllUser() {
        Transaction transaction = null;
        List <Users> listOfUser = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfUser = session.createQuery("from Users", Users.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfUser;
    }

    @Override
    public void create(Users users) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(users);
            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }



    @Override
    public Users read(Integer key) {
        Transaction transaction = null;
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(Users.class, key);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public void update(Users users) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(users);
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
            Users user = session.get(Users.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Users readByTokenAndId(String token, int id) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users WHERE token = :token and id=:id");
            query.setParameter("token", token);
            query.setParameter("id", id);
            user = (Users) query.getSingleResult();
        }
        catch (NoResultException ignored) {
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users readByLogin(String login) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users WHERE login = :login");
            query.setParameter("login", login);
            user = (Users) query.getSingleResult();
        }
        catch (NoResultException ignored) {


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

//    public Users getUserandBidbyId(Integer id) {
//        Transaction transaction = null;
//        Users user = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            user = session.get(Users.class, id);
////            if (user != null)
////                Hibernate.initialize(user.getUserbids());
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public Users getUserandBidbyToken(String token) {
//        Users user = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query query = session.createQuery("FROM Users WHERE token = :token");
//            query.setParameter("token", token);
//            user = (Users) query.getSingleResult();
//            //if status == bider or other assasments then create bid list
////            if (user != null)
////                Hibernate.initialize(user.getUserbids());
//        }
//        catch (NoResultException ignored) {
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public Users getUserandLotbyId(Integer id) {
//        Transaction transaction = null;
//        Users user = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            user = session.get(Users.class, id);
//            if (user != null)
//                Hibernate.initialize(user.getUserLots());
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public Users getUserandLotbyToken(String token) {
//        Users user = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query query = session.createQuery("FROM Users WHERE token = :token");
//            query.setParameter("token", token);
//            user = (Users) query.getSingleResult();
//            //if status == lots or other assasments then create lot list
//            if (user != null)
//                Hibernate.initialize(user.getUserLots());
//        }
//        catch (NoResultException ignored) {
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

    public Users getUserAndLotByIdAndToken(Integer id, String token) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users u left join fetch u.userlots where u.id = :id and u.token = :token");
            query.setParameter("id", id);
            query.setParameter("token", token);
            user = (Users) query.getSingleResult();
        }
        catch (NoResultException ignored) {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users getUserandBidbyIdandToken(Integer id, String token) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users u left join fetch u.userbids where u.id = :id and u.token = :token");
            query.setParameter("id", id);
            query.setParameter("token", token);
            user = (Users) query.getSingleResult();
        }
        catch (NoResultException ignored) {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }



}