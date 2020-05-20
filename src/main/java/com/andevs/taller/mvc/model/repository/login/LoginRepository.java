package com.andevs.taller.mvc.model.repository.login;

import com.andevs.taller.mvc.model.util.PersistenceConfig;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class LoginRepository implements ILoginRepository {
    private Session session;

    private void initSession() {
        session = PersistenceConfig.getSessionFactory().openSession();
    }

    public Boolean login(String user, String password) {
        try {
            initSession();
            Query query = session.createSQLQuery("select * from login  where _user = :user and password = :pass");
            query.setParameter("user", user);
            query.setParameter("pass", password);
            List result = query.list();
            if (result.size() > 0) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error in login " + e.getMessage());
        } finally {
            session.close();
        }
        return Boolean.FALSE;
    }
}
