package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {

    protected Connection connection;

    public DAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {

        this.connection = DriverManager.getConnection(addressDataBase, userDataBase, passWordDataBase);

    }
}
