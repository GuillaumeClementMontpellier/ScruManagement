package business.facade;

import business.system.User;

import java.sql.SQLException;

public class GlobalFacade {

    private static GlobalFacade instance = null;

    private LoginFacade loginFacade;

    private GlobalFacade() {
        this.loginFacade = new LoginFacade();
    }

    public static GlobalFacade getInstance() {
        if (instance == null) {
            instance = new GlobalFacade();
        }
        return instance;
    }

    public User login(String username, String password) throws SQLException {
        return loginFacade.login(username, password);
    }

    public User register(String username, String password, String firstName, String lastName) throws SQLException {
        return loginFacade.register(username,password,firstName,lastName);
    }
}
