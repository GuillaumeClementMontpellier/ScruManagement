package DAO;

import business.system.User;

import java.sql.SQLException;

public interface UserDAO {

    User getUserByID(String mail, String password) throws SQLException;

    void registerUser(String mail, String password, String firstName, String lastName, String salt) throws SQLException;

    boolean userExists(String mail) throws SQLException;

    User getUserByIdUser(int idUser) throws SQLException;

    String getSalt(String mail) throws SQLException;
}
