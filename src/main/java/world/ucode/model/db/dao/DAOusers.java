package world.ucode.model.db.dao;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.hibernate.query.Query;
import world.ucode.model.db.entetis.Users;
import world.ucode.model.db.util.HibernateUtil;


import javax.persistence.NoResultException;
import java.util.List;

/**
 * CRUD database operations
 * @author Ramesh Fadatare
 *
 */
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
            e.printStackTrace();
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

    public Users readbyToken(String token) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users WHERE token = :token");
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

    public Users readbyLogin(String login) {
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

    public Users getUserandBidbyId(Integer id) {
        Transaction transaction = null;
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(Users.class, id);
            if (user != null)
                Hibernate.initialize(user.getUserbids());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users getUserandBidbyToken(String token) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users WHERE token = :token");
            query.setParameter("token", token);
            user = (Users) query.getSingleResult();
            //if status == bider or other assasments then create bid list
            if (user != null)
                Hibernate.initialize(user.getUserbids());
        }
        catch (NoResultException ignored) {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users getUserandLotbyId(Integer id) {
        Transaction transaction = null;
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(Users.class, id);
            if (user != null)
                Hibernate.initialize(user.getUserLots());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users getUserandLotbyToken(String token) {
        Users user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Users WHERE token = :token");
            query.setParameter("token", token);
            user = (Users) query.getSingleResult();
            //if status == lots or other assasments then create lot list
            if (user != null)
                Hibernate.initialize(user.getUserLots());
        }
        catch (NoResultException ignored) {

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }



}