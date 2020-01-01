package business.facade;

import DAO.BacklogDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.*;

import java.sql.Date;
import java.sql.SQLException;

public class BacklogFacade {

    public TicketBacklog getTicketBacklog(Projet p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getTicketBacklog(p.getId());
    }

    public ProductBacklog getProductBacklog(Projet p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getProductBacklog(p.getId());
    }

    public SprintBacklog getLatestSprintBacklog(Projet p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getLatestSprintBacklog(p.getId());
    }

    public SprintBacklog[] getAllSprintBacklog(Projet p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getAllSprintBacklog(p.getId());
    }

    public Column[] getColumn(Backlog backlog) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getColumn(backlog);
    }

    public UserStory[] getUserStory(Column col) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getUserStory(col);
    }

    public Ticket[] getTickets(Column col) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getTickets(col);
    }

    public boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.moveComponent(c, fromCol, toCol);
    }

    public boolean addComponent(Component c, Column col) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.addComponent(c, col);
    }

    public boolean createSprintBacklog(int idProject, Date startDate, Date endDate) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.createSprintBacklog(idProject, startDate, endDate);
    }

    public boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.deleteSprintBacklog(sprintBacklog);
    }

    public boolean initiateProductTicketBacklog(int idProject) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.initiateProductTicketBacklog(idProject);
    }
}
