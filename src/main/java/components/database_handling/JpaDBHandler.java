package components.database_handling;

import components.database_handling.models.DateDB;
import components.database_handling.models.EarningDB;
import components.database_handling.models.PurchaseDB;
import org.hibernate.jpa.HibernatePersistenceProvider;



import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;


public class JpaDBHandler implements DBHandler {

    EntityManagerFactory entityManagerFactory;

    private static JpaDBHandler uniqueInstance;
    private JpaDBHandler() {
        entityManagerFactory = new HibernatePersistenceProvider().createEntityManagerFactory("PostgreSQL_MonManApp_PU", null);
    }
    public static JpaDBHandler getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new JpaDBHandler();
        return uniqueInstance;
    }


    @Override
    public List<DateDB> getAllDays() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<DateDB> days = null;

        try {
            transaction.begin();
            days = entityManager.createQuery("from DateDB").getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return days;

    }


    @Override
    public List<PurchaseDB> getAllPurchases() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<PurchaseDB> purchases = null;

        try {
            transaction.begin();
            purchases = entityManager.createQuery("from PurchaseDB").getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return purchases;
    }


    @Override
    public List<EarningDB> getAllEarnings() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        List<EarningDB> earnings = null;

        try {
            transaction.begin();
            earnings = entityManager.createQuery("from EarningDB").getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return earnings;
    }


    @Override
    public void setDay(DateDB date) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(date);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }


    @Override
    public void setPurchase(PurchaseDB purchase) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(purchase);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }


    @Override
    public void setEarning(EarningDB earning) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(earning);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }


    @Override
    public List<PurchaseDB> getPurchasesInTimePeriod(Date firstDay, Date lastDay) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<PurchaseDB> purchases = null;

        try {
            transaction.begin();

            purchases = entityManager.createQuery("select p from PurchaseDB p where p.day between ?1 and ?2")
                    .setParameter(1, firstDay, TemporalType.TIMESTAMP).setParameter(2, lastDay, TemporalType.TIMESTAMP).getResultList();

            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return purchases;
    }


    @Override
    public List<EarningDB> getEarningsInTimePeriod(Date firstDay, Date lastDay) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<EarningDB> earnings = null;

        try {
            transaction.begin();

            Query query = entityManager.createQuery("select e from EarningDB e where e.day between ?1 and ?2");

            query.setParameter(1, firstDay, TemporalType.TIMESTAMP);
            query.setParameter(2, lastDay, TemporalType.TIMESTAMP);

            earnings = query.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return earnings;
    }

    @Override
    public void deleteDay(Date date) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("from DateDB d where(day(d) = ?1)").setParameter(1, date);

            List<DateDB> days = query.getResultList();
            if (days.size() != 0) {
                DateDB day = days.get(0);
                entityManager.remove(day);
            }
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void deletePurchase(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("from PurchaseDB p where(p.id = ?1)").setParameter(1, id);

            List<PurchaseDB> purchases = query.getResultList();
            if (purchases.size() != 0) {
                PurchaseDB purchase = purchases.get(0);
                entityManager.remove(purchase);
            }
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteEarning(Long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Query query = entityManager.createQuery("from EarningDB e where(e.id = ?1)").setParameter(1, id);

            List<EarningDB> earnings = query.getResultList();
            if (earnings.size() != 0) {
                EarningDB earning = earnings.get(0);
                entityManager.remove(earning);
            }
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }
        finally {
            entityManager.close();
        }
    }
}
