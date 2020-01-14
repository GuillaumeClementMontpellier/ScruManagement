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
    private SprintFacade sprintFacade;

    private GlobalFacade() {

        this.loginFacade = new LoginFacade();
        this.userStoryFacade = new UserStoryFacade();
        this.backlogFacade = new BacklogFacade();
        this.projectFacade = new ProjectFacade();
        this.ticketFacade = new TicketFacade();
        this.sprintFacade = new SprintFacade();

        userStoryFacade.setGlobalFacade(this);
        ticketFacade.setGlobalFacade(this);
        projectFacade.setGlobalFacade(this);
    }

    public static GlobalFacade getInstance() {
        if (instance == null) {
            instance = new GlobalFacade();
        }
        return instance;
    }

    public UserStory[] getUserStoryByProject(Project project) throws SQLException {
        return userStoryFacade.getUserStoryByProject(project);
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

    public boolean createSprintBacklog(int project, Date startDate, Date endDate) throws SQLException {
        return backlogFacade.createSprintBacklog(project, startDate, endDate);
    }

    public boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException {
        return backlogFacade.deleteSprintBacklog(sprintBacklog);
    }

    public boolean initiateProductTicketBacklog(int project) throws SQLException {
        return backlogFacade.initiateProductTicketBacklog(project);
    }

    public List<Project> getProjectListFromUser(User u) throws SQLException {
        return projectFacade.getProjectListFromUser(u);
    }

    public Ticket getTicketById(int ticketId) throws SQLException {
        return ticketFacade.getTicketById(ticketId);
    }

    public boolean addTicket(Ticket newTicket, Project project) throws SQLException {
        return ticketFacade.addTicket(newTicket, project);
    }

    public boolean deleteTicket(Ticket oldTicket) throws SQLException {
        return ticketFacade.deleteTicket(oldTicket);
    }

    public boolean updateTicket(Ticket updatedTicket, Ticket oldTicket) throws SQLException {
        return ticketFacade.updateTicket(updatedTicket, oldTicket);
    }

    public Project getProjectByID(int idProject) throws SQLException {
        return projectFacade.getProjectByID(idProject);
    }

    public List<Collaborator> getProjectTeam(Project project) throws SQLException {
        return projectFacade.getProjectTeam(project);
    }

    public User getProjectAdmin(Project project) throws SQLException {
        return projectFacade.getProjectAdmin(project);
    }

    public User getProjectScrumMaster(Project project) throws SQLException {
        return projectFacade.getProjectScrumMaster(project);
    }

    public User getProjectProductOwner(Project project) throws SQLException {
        return projectFacade.getProjectProductOwner(project);
    }

    public List<User> getProjectDevelopers(Project project) throws SQLException {
        return projectFacade.getProjectDevelopers(project);
    }


    public Project createProject(String name, String summary, String type, Date deadline, User user) throws SQLException {
        return projectFacade.createProject(name, summary, type, deadline, user);
    }

    public boolean editProject(Project project) throws SQLException {
        return projectFacade.editProject(project);
    }

    public boolean deleteProject(Project project) throws SQLException {
        return projectFacade.deleteProject(project);
    }


    public Collaborator addCollaborator(int idProject, int idCollaborator, int idRole, boolean isAdmin) throws SQLException {
        return projectFacade.addCollaborator(idProject, idCollaborator, idRole, isAdmin);
    }

    public boolean editCollaborator(Collaborator collaborator) throws SQLException {
        return projectFacade.editCollaborator(collaborator);
    }

    public boolean removeCollaborator(Collaborator collaborator) throws SQLException {
        return projectFacade.removeCollaborator(collaborator);
    }

    public Sprint getSprintByID(int idSprint, Project project) throws SQLException {
        return sprintFacade.getSprintById(idSprint, project);
    }

    public String getTypeOfSprint(Sprint sprint) throws SQLException {
        return sprintFacade.getTypeOfSprint(sprint);
    }

    public Sprint createSprint(Project project, String type , Date start, Date end) throws SQLException {
        return sprintFacade.createSprint(project, type, start, end);
    }

    public boolean updateSprint(Sprint sprint) throws SQLException {
        return sprintFacade.updateSprint(sprint);
    }

    public boolean deleteSprint(Sprint sprint) throws SQLException {
        return sprintFacade.deleteSprint(sprint);
    }
}
