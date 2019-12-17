package DAO;

import business.system.User;

import java.sql.SQLException;

public abstract class UserDAO extends DAO{

    public UserDAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public abstract User getUserByID(String mail, String password) throws SQLException;

}
