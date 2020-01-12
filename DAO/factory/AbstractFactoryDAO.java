package DAO.factory;

import DAO.BacklogDAO;
import DAO.TicketDAO;
import DAO.UserDAO;
import DAO.UserStoryDAO;

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

    public abstract UserStoryDAO createUserStoryDAO() throws SQLException;

    public abstract BacklogDAO createBacklogDAO() throws SQLException;

    public abstract TicketDAO createTicketDAO() throws  SQLException;

}
