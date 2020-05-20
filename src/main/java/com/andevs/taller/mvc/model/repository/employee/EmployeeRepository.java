package com.andevs.taller.mvc.model.repository.employee;

import com.andevs.taller.mvc.model.entities.Employee;
import com.andevs.taller.mvc.model.util.PersistenceConfig;
import com.andevs.taller.mvc.model.util.log.LogUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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
            LogUtil.writeInLog("Error in save() " + e.getMessage());
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
            LogUtil.writeInLog("Error in update() " + e.getMessage());
        } finally {
            session.close();
        }
        return Boolean.FALSE;
    }

    public Boolean delete(Integer id) {
        try {
            Employee employee = findById(id);
            initSession();
            session.delete(employee);
            commitTransaction();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            exceptionHandler(e);
        } catch (Exception e) {
            LogUtil.writeInLog("Error in delete() " + e.getMessage());
        } finally {
            session.close();
        }
        return Boolean.FALSE;
    }

    public Employee findByDocNumber(Long documentNumber) {
        try {
            initSession();
            Object employee = session.byNaturalId(Employee.class)
                    .using("documentNumber", documentNumber)
                    .load();
            commitTransaction();
            return (Employee) employee;
        } catch (Exception e) {
            LogUtil.writeInLog("Error in findByDocNumber ".concat(e.getMessage()));
        } finally {
            session.close();
        }
        return Employee.builder().build();
    }

    public Employee findById(Integer employeeId) {
        try {
            initSession();
            return (Employee) session.get(Employee.class, employeeId);
        } catch (Exception e) {
            LogUtil.writeInLog("Error in findById ".concat(e.getMessage()));
        }finally {
            commitTransaction();
            session.close();
        }
        return Employee.builder().build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        try {
            initSession();
            List<Employee> employeeList = session.createCriteria(Employee.class).list();
            commitTransaction();
            return employeeList;
        } catch (HibernateException e) {
            exceptionHandler(e);
        } catch (Exception e) {
            LogUtil.writeInLog("Error in findAll() " + e.getMessage());
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }

    private void exceptionHandler(HibernateException e) {
        session.getTransaction().rollback();
        LogUtil.writeInLog("Error in EmployeeRepository ".concat(e.getMessage()));
    }
}
