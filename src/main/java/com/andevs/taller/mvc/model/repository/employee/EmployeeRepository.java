package com.andevs.taller.mvc.model.repository.employee;

import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.util.PersistenceConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EmployeeRepository implements IEmployeeRepository {
    private Session session;

    private void initSession() {
        session = PersistenceConfig.getSessionFactory().openSession();
    }

    public Boolean save(Employee employee) {
        try {
            initSession();
            Integer id = (Integer) session.save(employee);
            session.getTransaction().commit();
            if (id != null && id > 0) {
                return Boolean.TRUE;
            }
        } catch (HibernateException e) {
            exceptionHandler(e);
        } catch (Exception e) {
            System.out.println("Error in save() " + e.getMessage());
        } finally {
            // session.close();
        }
        return Boolean.FALSE;
    }

    public Boolean update(Employee employee) {
        try {
            initSession();
            Integer id = (Integer) session.merge(employee);
            session.getTransaction().commit();
            if (id != null && id > 0) {
                return Boolean.TRUE;
            }
        } catch (HibernateException e) {
            exceptionHandler(e);
        } catch (Exception e) {
            System.out.println("Error in update() " + e.getMessage());
        } finally {
            session.close();
        }
        return Boolean.FALSE;
    }

    public void delete(Integer id) {
        try {
            Employee employee = findById(id);
            initSession();
            session.delete(employee);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            exceptionHandler(e);
        } catch (Exception e) {
            System.out.println("Error in update() " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void findByDocNumber(Long docNumber) {

    }

    public Employee findById(Integer id) {
        return null;
    }

    private void exceptionHandler(HibernateException e) {
        session.getTransaction().rollback();
        System.out.println("Error in EmployeeRepository " + e.getMessage());
    }
}
