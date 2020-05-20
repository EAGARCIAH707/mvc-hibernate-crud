package com.andevs.taller.mvc.model.dao.login;

import com.andevs.taller.mvc.model.repository.login.ILoginRepository;
import com.andevs.taller.mvc.model.repository.login.LoginRepository;

public class LoginDAO implements ILoginDAO {
    private final ILoginRepository loginRepository;

    public LoginDAO() {
        loginRepository = new LoginRepository();
    }

    public Boolean login(String user, String password) {
        return loginRepository.login(user, password);
    }
}
