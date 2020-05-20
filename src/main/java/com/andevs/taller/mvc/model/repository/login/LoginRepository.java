package com.andevs.taller.mvc.model.repository.login;

import com.andevs.taller.mvc.model.util.PersistenceConfig;
import com.andevs.taller.mvc.model.util.log.LogUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoginRepository implements ILoginRepository {
    private Session session;
    private Transaction transaction;

    public LoginRepository() {
        initSession();
        transaction.commit();
    }

    private void initSession() {
        session = PersistenceConfig.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public Boolean login(String user, String password) {
        try {
            initSession();
            Query query = session.createSQLQuery("select * from login  where _user = :user and password = :pass");
            query.setParameter("user", user);
            query.setParameter("pass", password);
            List result = query.list();
            transaction.commit();
            if (result.size() > 0) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            LogUtil.writeInLog("Error in login " + e.getMessage());
        } finally {
            session.close();
        }
        return Boolean.FALSE;
    }
}
