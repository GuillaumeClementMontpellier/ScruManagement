package DAO.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOMariaDB {

    protected Connection connection;
    private static Connection connectionStatic = null;

    public DAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {

        if (connectionStatic == null){

            connectionStatic = DriverManager.getConnection(addressDataBase, userDataBase, passWordDataBase);
        }

        this.connection = connectionStatic;

    }
}
