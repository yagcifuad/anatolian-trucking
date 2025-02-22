package hibernateControllers;

import javafx.scene.control.Alert;
import model.Driver;
import model.Manager;
import model.Truck;
import model.User;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateController {
  private static EntityManagerFactory emf;

  public HibernateController(EntityManagerFactory emf) {
    this.emf = emf;
  }

  private EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public <T> void create(T entity){
    EntityManager em = null;

    try {
      em = getEntityManager();
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    } catch (Exception e){
      e.printStackTrace();
    }
    finally {
      if(em != null) em.close();
    }
  }
  public <T> void update(T entity){
    EntityManager em = null;

    try {
      em = getEntityManager();
      em.getTransaction().begin();
      em.merge(entity);
      em.getTransaction().commit();
    } catch (Exception e){
      e.printStackTrace();
    }
    finally {
      if(em != null) em.close();
    }
  }
  public <T> void delete(T entity) {
    EntityManager em = null;


    try {
      em = getEntityManager();
      em.getTransaction().begin();

      // Make sure the entity is managed before deleting it
      T managedEntity = em.contains(entity) ? entity : em.merge(entity);
      System.out.println(entity.toString());

      em.remove(managedEntity);
      em.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }
//  public <T> void delete(T entity){
//    EntityManager em = null;
//
//    try {
//      em = getEntityManager();
//      em.getTransaction().begin();
//      em.remove(entity);
//      em.getTransaction().commit();
//    } catch (Exception e){
//      e.printStackTrace();
//    }
//    finally {
//      if(em != null) em.close();
//    }
//  }
  public <T> List<T> getAllRecords(Class<T> entity){ //getAllRecords(Truck.class)
    EntityManager em = null;
    try {
      em = getEntityManager();
      CriteriaQuery<Object> query = em.getCriteriaBuilder().createQuery();
      query.select(query.from(entity));
      Query q = em.createQuery(query);
      return q.getResultList();
    }
    catch (Exception e){
      e.printStackTrace();
    }finally {
      if (em != null) em.close();
    }
    return null;
  }

  public <T> T getEntityByID(Class<T> entity, int id){
    EntityManager em = null;
    T result = null;
    try {
      em = getEntityManager();

      em.getTransaction().begin();

      result = em.find(entity, id);
      em.getTransaction().commit();

    }catch (Exception e){
      e.printStackTrace();
    }finally {
      if (em != null) em.close();
    }
    return result;
  }

  public <T> T findUserByCredentials(String login, String password, Class<T> userType) throws Exception {
    EntityManager em = getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> query = cb.createQuery(userType);
    Root<T> root = query.from(userType);
    query.select(root).where(cb.and(cb.like(root.get("login"), login), cb.like(root.get("password"), password)));
    Query q;
    q = em.createQuery(query);
    try {
      return (T) q.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public void alertDialog(String message, String header) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Info");
    alert.setHeaderText(header);
    alert.setContentText(message);

    alert.showAndWait();
  }

  //  public Driver findDriverByCredentials(String login, String password) throws Exception {
//    EntityManager em = getEntityManager();
//    CriteriaBuilder cb = em.getCriteriaBuilder();
//    CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
//    Root<Driver> root = query.from(Driver.class);
//    query.select(root).where(cb.and(cb.like(root.get("login"), login), cb.like(root.get("password"), password)));
//    Query q;
//    q = em.createQuery(query);
//    return (Driver) q.getSingleResult();
//  }
//
//  public Manager findManagerByCredentials(String login, String password) throws Exception {
//    EntityManager em = getEntityManager();
//    CriteriaBuilder cb = em.getCriteriaBuilder();
//    CriteriaQuery<Manager> query = cb.createQuery(Manager.class);
//    Root<Manager> root = query.from(Manager.class);
//    query.select(root).where(cb.and(cb.like(root.get("login"), login), cb.like(root.get("password"), password)));
//    Query q;
//    q = em.createQuery(query);
//    return (Manager) q.getSingleResult();
//  }
}
