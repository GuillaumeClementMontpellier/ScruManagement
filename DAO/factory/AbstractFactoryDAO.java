package DAO.factory;

import DAO.UserDAO;

import java.sql.SQLException;

public interface AbstractFactoryDAO {

    public UserDAO createUserDAO() throws SQLException;

}
