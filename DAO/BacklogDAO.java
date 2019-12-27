package DAO;

import business.system.*;

import java.sql.Date;
import java.sql.SQLException;

public abstract class BacklogDAO extends DAO{
    public BacklogDAO(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public abstract ProductBacklog getProductBacklog (int idProject) throws SQLException;
    public abstract TicketBacklog getTicketBacklog (int idProject) throws SQLException;

    //return null if there is no SprintBacklog
    public abstract SprintBacklog getLatestSprintBacklog(int idProject) throws SQLException;

    //return null if there is no SprintBacklog
    public abstract SprintBacklog[] getAllSprintBacklog (int idProject) throws SQLException;
    public abstract Column[] getColumn(Backlog backlog) throws SQLException;
    public abstract UserStory[] getUserStory(Column col) throws SQLException;
    public abstract Ticket[] getTickets(Column col) throws SQLException;
    public abstract boolean moveComponent (Component c, Column fromCol,Column toCol)throws SQLException;
    public abstract boolean addComponent (Component c, Column col) throws SQLException;
    public abstract boolean createSprintBacklog (int idProject, Date startDate, Date endDate) throws SQLException;
    public abstract boolean deleteSprintBacklog (SprintBacklog sprintBacklog) throws SQLException;
    //use it only once, when the project is created
    public abstract boolean initiateProductTicketBacklog(int idProject) throws SQLException;
}
