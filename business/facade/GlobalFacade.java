package business.facade;

import business.system.*;

import java.sql.Date;
import java.sql.SQLException;

public class GlobalFacade {

    // Singleton
    private static GlobalFacade instance = null;
    // Logic
    private UserStoryFacade userStoryFacade;
    private LoginFacade loginFacade;
    private BacklogFacade backlogFacade;

    private GlobalFacade() {

        this.loginFacade = new LoginFacade();
        this.userStoryFacade = new UserStoryFacade();
        this.backlogFacade = new BacklogFacade();
    }

    public static GlobalFacade getInstance() {
        if (instance == null) {
            instance = new GlobalFacade();
        }
        return instance;
    }

    public User login(String username, String password) throws SQLException {
        return loginFacade.login(username, password);
    }

    public User register(String username, String password, String firstName, String lastName) throws SQLException {
        return loginFacade.register(username, password, firstName, lastName);
    }

    public boolean addUserStory(UserStory newUS, int projectID) throws SQLException {
        return userStoryFacade.addUserStory(newUS, projectID);
    }

    public boolean updateUserStory(UserStory oldUS, UserStory newUS) throws SQLException {
        return userStoryFacade.updateUserStory(oldUS, newUS);
    }

    public boolean deleteUserStory(UserStory oldUS) throws SQLException {
        return userStoryFacade.deleteUserStory(oldUS);
    }

    public UserStory getUserStoryByID(int id) throws SQLException {
        return userStoryFacade.getUserStoryByID(id);
    }


    public TicketBacklog getTicketBacklog(Projet p) throws SQLException {
        return backlogFacade.getTicketBacklog(p);
    }
    public ProductBacklog getProductBacklog(Projet p) throws SQLException {
        return backlogFacade.getProductBacklog(p);
    }
    public SprintBacklog getLatestSprintBacklog(Projet p) throws SQLException {
        return backlogFacade.getLatestSprintBacklog(p);
    }

    public SprintBacklog[] getAllSprintBacklog(Projet p) throws SQLException {
        return backlogFacade.getAllSprintBacklog(p);
    }

    public Column[] getColumn(Backlog backlog) throws SQLException {
        return backlogFacade.getColumn(backlog);
    }

    public UserStory[] getUserStory(Column col) throws SQLException{
        return backlogFacade.getUserStory(col);
    }

    public Ticket[] getTickets(Column col) throws SQLException {
        return backlogFacade.getTickets(col);
    }

    public boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException {
        return backlogFacade.moveComponent(c,fromCol,toCol);
    }

    public boolean addComponent(Component c, Column col) throws SQLException {
        return backlogFacade.addComponent(c,col);
    }

    public boolean createSprintBacklog(int idProject, Date startDate, Date endDate) throws SQLException {
        return backlogFacade.createSprintBacklog(idProject,startDate,endDate);
    }

    public boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException {
        return backlogFacade.deleteSprintBacklog(sprintBacklog);
    }

    public boolean initiateProductTicketBacklog(int idProject) throws SQLException {
        return backlogFacade.initiateProductTicketBacklog(idProject);
    }
}
