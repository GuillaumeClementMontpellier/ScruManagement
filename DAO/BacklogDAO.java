package DAO;

import business.system.*;

import java.sql.SQLException;

public abstract class BacklogDAO extends DAO{
    public BacklogDAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public abstract ProductBacklog getProductBacklog (int idProject) throws SQLException;
    public abstract TicketBacklog getTicketBacklog (int idProject) throws SQLException;

    public abstract SprintBacklog getLatestSprintBacklog(int idProject) throws SQLException;

    public abstract SprintBacklog[] getAllSprintBacklog (int idProject);
    public abstract Column[] getColumn(Backlog backlog) throws SQLException;
    public abstract UserStory[] getUserStory(Column col) throws SQLException;
}
