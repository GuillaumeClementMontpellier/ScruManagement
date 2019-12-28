package business.facade;

import business.system.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GlobalFacade {

    private static GlobalFacade instance = null;
    private final UserStoryFacade userStoryFacade;
    private final LoginFacade loginFacade;
    private final BacklogFacade backlogFacade;

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

    // TODO : redo type and params
    public boolean addUserStory(UserStory newUS) {
        return userStoryFacade.addUserStory(newUS);
    }

    public boolean updateUserStory(UserStory oldUS, UserStory newUS) {
        return userStoryFacade.updateUserStory(oldUS, newUS);
    }

    public List<UserStory> getUserStoryByProject(Projet currentProject) {
        return userStoryFacade.getUserStoryByProject(currentProject);
    }

    public boolean deleteUserStory(UserStory oldUS) {
        return userStoryFacade.deleteUserStory(oldUS);
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
