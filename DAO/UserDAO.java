package DAO;

import business.system.User;

import java.sql.SQLException;

public interface UserDAO {

    public abstract User getUserByID(String mail, String password) throws SQLException;

}
