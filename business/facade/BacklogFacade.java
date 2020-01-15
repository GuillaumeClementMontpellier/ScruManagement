package business.facade;

import DAO.BacklogDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.*;

import java.sql.Date;
import java.sql.SQLException;

public class BacklogFacade {

    private GlobalFacade globalFacade;

    public TicketBacklog getTicketBacklog(Project p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getTicketBacklog(p.getId());
    }

    public ProductBacklog getProductBacklog(Project p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getProductBacklog(p.getId());
    }

    public SprintBacklog getLatestSprintBacklog(Project p) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();
        return backlogDAO.getLatestSprintBacklog(p.getId());
    }

    public SprintBacklog[] getAllSprintBacklog(Project p) throws SQLException {
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

    public boolean deleteBacklogs(Project project) throws SQLException {
        BacklogDAO backlogDAO = AbstractFactoryDAO.getInstance().createBacklogDAO();

        Column[] pCol = backlogDAO.getColumn(backlogDAO.getProductBacklog(project.getId()));
        Column[] tCol = backlogDAO.getColumn(backlogDAO.getTicketBacklog(project.getId()));
        SprintBacklog[] sBack = backlogDAO.getAllSprintBacklog(project.getId());

        // Remove Colonnes Backlog
        for (Column c : tCol) { // for each
            Ticket[] ticket = backlogDAO.getTickets(c);
            for (Ticket u : ticket) { // remove User Stories
                boolean success = globalFacade.deleteTicket(u);
                if (!success) {
                    System.err.println("Suppression T");
                }
            }
            int nb = backlogDAO.deleteColumn(c);
            if (nb <= 0) {
                System.err.println("Suppression T col " + c.getId());
            }
        }
        // Remove Colonnes Backlog
        for (Column c : pCol) { // for each
            UserStory[] userStory = backlogDAO.getUserStory(c);
            for (UserStory u : userStory) { // remove User Stories
                boolean success = globalFacade.deleteUserStory(u);
                if (!success) {
                    System.err.println("Suppression US");
                }
            }
            int nb = backlogDAO.deleteColumn(c);
            if (nb <= 0) {
                System.err.println("Suppression US col " + c.getId());
            }
        }

        for (SprintBacklog sb : sBack) {
            boolean success = deleteSprintBacklog(sb);
            if (!success) {
                System.err.println("Suppression US col sprint");
            }
        }
        return backlogDAO.removeBacklogs(project);
    }

    public void setGlobalFacade(GlobalFacade globalFacade) {
        this.globalFacade = globalFacade;
    }
}
