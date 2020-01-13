package DAO.mariadb;

import DAO.*;
import DAO.factory.AbstractFactoryDAO;
import business.system.Sprint;

import java.sql.SQLException;

public class FactoryDAOMariaDB extends AbstractFactoryDAO {

    private String pwDataBase;
    private String userDataBase;
    private String urlDataBase;

    public FactoryDAOMariaDB(String urlDataBase, String userDataBase, String pwDataBase) {

        this.urlDataBase = urlDataBase;
        this.userDataBase = userDataBase;
        this.pwDataBase = pwDataBase;

    }

    @Override
    public UserDAOMariaDB createUserDAO() throws SQLException {
        return new UserDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
    }

    @Override
    public UserStoryDAO createUserStoryDAO() throws SQLException {
        return new UserStoryDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
    }

    @Override
    public BacklogDAO createBacklogDAO() throws SQLException {
        return new BacklogDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
    }

    @Override
    public ProjectDAO createProjectDAO() throws SQLException {
        return new ProjectDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
    }

    @Override
    public TicketDAO createTicketDAO() throws SQLException {
        return new TicketDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
    }

    public SprintDAO createSprintDAO() throws SQLException {
        return new SprintDAOMariaDB(urlDataBase, userDataBase, pwDataBase);
    }
}
