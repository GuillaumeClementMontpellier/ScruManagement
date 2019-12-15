package DAO.factory;

import DAO.UserDAO;

import java.sql.SQLException;

public abstract class AbstractFactoryDAO {

    private static AbstractFactoryDAO instance = null;

    public static AbstractFactoryDAO getInstance() {
        return instance;
    }

    public static void setInstance(AbstractFactoryDAO factory) {
        instance = factory;
    }

    public abstract UserDAO createUserDAO() throws SQLException;

}
