package com.andevs.taller.mvc.model.repository.employee;

import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.util.PersistenceConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeRepository implements IEmployeeRepository {
    private Session session;
    private Transaction transaction;

    private void initSession() {
        session = PersistenceConfig.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private void commitTransaction() {
        transaction.commit();
    }

    public Boolean save(Employee employee) {
        try {
            initSession();
            Integer id = (Integer) session.save(employee);
            commitTransaction();
            if (id != null && id > 0) {
                return Boolean.TRUE;
            }
        } catch (HibernateException e) {
            exceptionHandler(e);
        } catch (Exception e) {
            System.out.println("Error in save() " + e.getMessage());
        } finally {
            session.close();
        }
        return Boolean.FALSE;
    }

    public Boolean update(Employee employee) {
        try {
            initSession();
            session.update(employee);
            commitTransaction();
            return Boolean.TRUE;

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

    public Employee findByDocNumber(Long documentNumber) {
        try {
            initSession();

            Object id = session.byNaturalId(Employee.class).using("documentNumber", documentNumber).load();
            commitTransaction();
            return (Employee) id;
        } catch (Exception e) {
            System.out.println("Error in findByDocNumber ".concat(e.getMessage()));
        }
        return Employee.builder().build();
    }

    public Employee findById(Integer employeeId) {
        try {
            initSession();
            return (Employee) session.get(Employee.class, employeeId);
        } catch (Exception e) {
            System.out.println("Error in findById ".concat(e.getMessage()));
        }
        return Employee.builder().build();
    }

    private void exceptionHandler(HibernateException e) {
        session.getTransaction().rollback();
        System.out.println("Error in EmployeeRepository ".concat(e.getMessage()));
    }
}
