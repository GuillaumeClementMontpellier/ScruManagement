package business.facade;

import business.system.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GlobalFacade {

    // Singleton
    private static GlobalFacade instance = null;
    // Logic
    private UserStoryFacade userStoryFacade;
    private LoginFacade loginFacade;
    private BacklogFacade backlogFacade;
    private ProjectFacade projectFacade;
    private TicketFacade ticketFacade;

    private GlobalFacade() {

        this.loginFacade = new LoginFacade();
        this.userStoryFacade = new UserStoryFacade();
        this.backlogFacade = new BacklogFacade();
        this.projectFacade = new ProjectFacade();
        this.ticketFacade = new TicketFacade();

        userStoryFacade.setBacklogFacade(backlogFacade);
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

    public boolean addUserStory(UserStory newUS, Project project) throws SQLException {
        return userStoryFacade.addUserStory(newUS, project);
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


    public TicketBacklog getTicketBacklog(Project p) throws SQLException {
        return backlogFacade.getTicketBacklog(p);
    }

    public ProductBacklog getProductBacklog(Project p) throws SQLException {
        return backlogFacade.getProductBacklog(p);
    }

    public SprintBacklog getLatestSprintBacklog(Project p) throws SQLException {
        return backlogFacade.getLatestSprintBacklog(p);
    }

    public SprintBacklog[] getAllSprintBacklog(Project p) throws SQLException {
        return backlogFacade.getAllSprintBacklog(p);
    }

    public Column[] getColumn(Backlog backlog) throws SQLException {
        return backlogFacade.getColumn(backlog);
    }

    public UserStory[] getUserStory(Column col) throws SQLException {
        return backlogFacade.getUserStory(col);
    }

    public Ticket[] getTickets(Column col) throws SQLException {
        return backlogFacade.getTickets(col);
    }

    public boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException {
        return backlogFacade.moveComponent(c, fromCol, toCol);
    }

    public boolean addComponent(Component c, Column col) throws SQLException {
        return backlogFacade.addComponent(c, col);
    }

    public boolean createSprintBacklog(int idProject, Date startDate, Date endDate) throws SQLException {
        return backlogFacade.createSprintBacklog(idProject, startDate, endDate);
    }

    public boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException {
        return backlogFacade.deleteSprintBacklog(sprintBacklog);
    }

    public boolean initiateProductTicketBacklog(int idProject) throws SQLException {
        return backlogFacade.initiateProductTicketBacklog(idProject);
    }

    public List<Project> getProjectListFromUser(User u) throws SQLException {
        return projectFacade.getProjectListFromUser(u);
    }

    public Ticket getTicketById(int ticketId) throws SQLException {
        return ticketFacade.getTicketById(ticketId);
    }

    public boolean addTicket(Ticket newTicket, int ProjectId) throws SQLException {
        return ticketFacade.addTicket(newTicket, ProjectId);
    }

    public boolean deleteTicket(Ticket oldTicket) throws SQLException {
        return ticketFacade.deleteTicket(oldTicket);

    }

    public boolean updateTicket(Ticket updatedTicket, Ticket oldTicket) throws SQLException {
        return ticketFacade.updateTicket(updatedTicket, oldTicket);
    }

    public boolean addProject(Project project, User user) {
        return projectFacade.addProject(project, user);
    }

    public Project getProjectByID(int idProject) throws SQLException {
        return projectFacade.getProjectByID(idProject);
    }

    public User getProjectAdmin(int idProject) throws SQLException {
        return projectFacade.getProjectAdmin(idProject);
    }

    public User getProjectScrumMaster(int idProject) throws SQLException {
        return projectFacade.getProjectScrumMaster(idProject);
    }

    public User getProjectProductOwner(int idProject) throws SQLException {
        return projectFacade.getProjectProductOwner(idProject);
    }

    public User[] getProjectDevelopers(int idProject) throws SQLException {
        return projectFacade.getProjectDevelopers(idProject);
    }

    public User[] getProjectTeam(int idProject) throws SQLException {
        return projectFacade.getProjectTeam(idProject);
    }


    public boolean createProject(Project project, User creator) throws SQLException {
        return projectFacade.createProject(project, creator);
    }

    public boolean editProject(Project project) throws SQLException {
        return projectFacade.editProject(project);
    }

    public boolean deleteProject(Project project) throws SQLException {
        return projectFacade.deleteProject(project);
    }


    public boolean addCollaborator(int idProject, User collaborator) throws SQLException {
        return projectFacade.addCollaborator(idProject, collaborator);
    }

    public boolean editCollaborator(int idProject, User collaborator) throws SQLException {
        return projectFacade.editCollaborator(idProject, collaborator);
    }

    public boolean removeCollaborator(int idProject, User collaborator) throws SQLException {
        return projectFacade.removeCollaborator(idProject, collaborator);

    }

}
