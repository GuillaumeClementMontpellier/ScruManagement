package DAO;

import business.system.*;

import java.sql.Date;
import java.sql.SQLException;

public interface BacklogDAO {


    ProductBacklog getProductBacklog(int idProject) throws SQLException;

    TicketBacklog getTicketBacklog(int idProject) throws SQLException;

    //return null if there is no SprintBacklog
    SprintBacklog getLatestSprintBacklog(int idProject) throws SQLException;

    //return null if there is no SprintBacklog
    SprintBacklog[] getAllSprintBacklog(int idProject) throws SQLException;

    Column[] getColumn(Backlog backlog) throws SQLException;

    UserStory[] getUserStory(Column col) throws SQLException;

    Ticket[] getTickets(Column col) throws SQLException;

    boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException;

    boolean addComponent(Component c, Column col) throws SQLException;

    boolean createSprintBacklog(int idProject, Date startDate, Date endDate) throws SQLException;

    boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException;

    //use it only once, when the project is created
    boolean initiateProductTicketBacklog(int idProject) throws SQLException;

    boolean removeBacklogs(Project project) throws SQLException;

    int deleteColumn(Column col) throws SQLException;
}
