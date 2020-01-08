package DAO.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOMariaDB {

    protected Connection connection;

    public DAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {

        this.connection = DriverManager.getConnection(addressDataBase, userDataBase, passWordDataBase);

    }
}
