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
    private UserFacade userFacade;
    private BacklogFacade backlogFacade;
    private ProjectFacade projectFacade;
    private TicketFacade ticketFacade;
    private SprintFacade sprintFacade;

    private GlobalFacade() {

        this.userFacade = new UserFacade();
        this.userStoryFacade = new UserStoryFacade();
        this.backlogFacade = new BacklogFacade();
        this.projectFacade = new ProjectFacade();
        this.ticketFacade = new TicketFacade();
        this.sprintFacade = new SprintFacade();

        userStoryFacade.setGlobalFacade(this);
        ticketFacade.setGlobalFacade(this);
        projectFacade.setGlobalFacade(this);
        backlogFacade.setGlobalFacade(this);
    }

    /**
     * This method is the getter of the Singleton of the instance of the Global Facade
     * @return the single instance of GlobalFacade
     */
    public static GlobalFacade getInstance() {
        if (instance == null) {
            instance = new GlobalFacade();
        }
        return instance;
    }

    /**
     * This method is to get all the User Stories of a given project
     * @param project an already existing Project
     * @return UserStory[]
     * @throws SQLException
     */
    public UserStory[] getUserStoryByProject(Project project) throws SQLException {
        return userStoryFacade.getUserStoryByProject(project);
    }

    /**
     * this method is to login into the system as a User
     * @param username a String
     * @param password a String
     * @return The connected User
     * @throws SQLException
     */
    public User login(String username, String password) throws SQLException {
        return userFacade.login(username, password);
    }

    /**
     * This method is to create a new User into the system
     * @param username a String
     * @param password a String
     * @param firstName a String
     * @param lastName a String
     * @return The User newly created
     * @throws SQLException
     */
    public User register(String username, String password, String firstName, String lastName) throws SQLException {
        return userFacade.register(username, password, firstName, lastName);
    }

    /**
     * This method is to create a new user story into the product backlog
     * @param newUS the UserStory to add
     * @param project The project to add it into
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean addUserStory(UserStory newUS, Project project) throws SQLException {
        return userStoryFacade.addUserStory(newUS, project);
    }

    /**
     * This method is to update any given User Story
     * @param oldUS the UserStory we want to replace
     * @param newUS the new UserStory
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean updateUserStory(UserStory oldUS, UserStory newUS) throws SQLException {
        return userStoryFacade.updateUserStory(oldUS, newUS);
    }

    /**
     * This method is to delete any given User Story
     * @param oldUS the UserStory to delete
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean deleteUserStory(UserStory oldUS) throws SQLException {
        return userStoryFacade.deleteUserStory(oldUS);
    }

    /**
     * this method returns the UserStory with the given id
     * @param id an int representing the ID of an US
     * @return UserStory
     * @throws SQLException
     */
    public UserStory getUserStoryByID(int id) throws SQLException {
        return userStoryFacade.getUserStoryByID(id);
    }

    /**
     * this method returns the TicketBacklog of any given project
     * @param p a Project
     * @return TicketBacklog
     * @throws SQLException
     */
    public TicketBacklog getTicketBacklog(Project p) throws SQLException {
        return backlogFacade.getTicketBacklog(p);
    }

    /**
     * this method returns the ProductBacklog of any given project
     * @param p a Project
     * @return ProductBacklog
     * @throws SQLException
     */
    public ProductBacklog getProductBacklog(Project p) throws SQLException {
        return backlogFacade.getProductBacklog(p);
    }

    /**
     * this method returns the current SprintBacklog of any given project
     * @param p a Project
     * @return SprintBacklog
     * @throws SQLException
     */
    public SprintBacklog getLatestSprintBacklog(Project p) throws SQLException {
        return backlogFacade.getLatestSprintBacklog(p);
    }

    /**
     * this method returns the all the SprintBacklog of any given project
     * @param p a Project
     * @return All the SprintBacklog created so far
     * @throws SQLException
     */
    public SprintBacklog[] getAllSprintBacklog(Project p) throws SQLException {
        return backlogFacade.getAllSprintBacklog(p);
    }

    /**
     * This method returns all the column of any given project backlog
     * @param backlog a Backlog
     * @return all the Column of a Backlog
     * @throws SQLException
     */
    public Column[] getColumn(Backlog backlog) throws SQLException {
        return backlogFacade.getColumn(backlog);
    }

    /**
     * This method returns all the US of any given Column
     * @param col a Column of a Backlog
     * @return all the US of the Column
     * @throws SQLException
     */
    public UserStory[] getUserStory(Column col) throws SQLException {
        return backlogFacade.getUserStory(col);
    }

    /**
     * This method returns all the Ticket of any given Column
     * @param col a Column of a Backlog
     * @return all the Ticket of the Column
     * @throws SQLException
     */
    public Ticket[] getTickets(Column col) throws SQLException {
        return backlogFacade.getTickets(col);
    }

    /**
     * This method is to move any Component (UserStory and Ticket) from a Column to an other one
     * @param c the Component we want to move
     * @param fromCol the Column it belongs
     * @param toCol the Column we want it to be moved
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean moveComponent(Component c, Column fromCol, Column toCol) throws SQLException {
        return backlogFacade.moveComponent(c, fromCol, toCol);
    }

    /**
     * This method is to add any Component (UserStory and Ticket) into a given Column
     * @param c the Component to add
     * @param col the Column to add it into
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean addComponent(Component c, Column col) throws SQLException {
        return backlogFacade.addComponent(c, col);
    }

    /**
     * This method is to create a new Sprint into any given Project
     * @param project the ID of a already existing Project
     * @param startDate the starting Date of the Sprint
     * @param endDate the ending Date of the Sprint
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean createSprintBacklog(int project, Date startDate, Date endDate) throws SQLException {
        return backlogFacade.createSprintBacklog(project, startDate, endDate);
    }

    /**
     * This method is to delete a SprintBacklog
     * @param sprintBacklog the SprintBacklog to be deleted
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean deleteSprintBacklog(SprintBacklog sprintBacklog) throws SQLException {
        return backlogFacade.deleteSprintBacklog(sprintBacklog);
    }

    /**
     * This method is to initiate the ProductBacklog and the TicketBacklog at the start of a Project
     * @param project the newly created Project
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean initiateProductTicketBacklog(int project) throws SQLException {
        return backlogFacade.initiateProductTicketBacklog(project);
    }

    /**
     * This method returns the List of Projects the User is currently invested into
     * @param u an already existing Project
     * @return a List of Projects
     * @throws SQLException
     */
    public List<Project> getProjectListFromUser(User u) throws SQLException {
        return projectFacade.getProjectListFromUser(u);
    }

    /**
     * This method returns the Ticket with the same ID
     * @param ticketId the ID of the Ticket we want to fetch
     * @return the Ticket
     * @throws SQLException
     */
    public Ticket getTicketById(int ticketId) throws SQLException {
        return ticketFacade.getTicketById(ticketId);
    }

    /**
     * This method is to create a Ticket
     * @param newTicket the Ticket we created
     * @param project the Project we want the Ticket to belong
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean addTicket(Ticket newTicket, Project project) throws SQLException {
        return ticketFacade.addTicket(newTicket, project);
    }

    /**
     * This method is to delete any already existing Ticket
     * @param oldTicket the Ticket to delete
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean deleteTicket(Ticket oldTicket) throws SQLException {
        return ticketFacade.deleteTicket(oldTicket);
    }

    /**
     * This method is to update any already existing Ticket
     * @param updatedTicket the new Ticket
     * @param oldTicket the Ticket to update
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean updateTicket(Ticket updatedTicket, Ticket oldTicket) throws SQLException {
        return ticketFacade.updateTicket(updatedTicket, oldTicket);
    }

    /**
     * This method returns the existing Project with the given ID
     * @param idProject an int representing the ID of a Project
     * @return the Project
     * @throws SQLException
     */
    public Project getProjectByID(int idProject) throws SQLException {
        return projectFacade.getProjectByID(idProject);
    }

    /**
     * This method returns a List of Collaborator for an already existing Project
     * @param project the Project we want to know the Collaborators
     * @return a List of Collaborator
     * @throws SQLException
     */
    public List<Collaborator> getProjectTeam(Project project) throws SQLException {
        return projectFacade.getProjectTeam(project);
    }

    /**
     * This method returns an User, who is the Admin of an already existing Project
     * @param project the Project we want to know the Admin
     * @return the Admin as a User
     * @throws SQLException
     */
    public User getProjectAdmin(Project project) throws SQLException {
        return projectFacade.getProjectAdmin(project);
    }

    /**
     * This method returns an User, who is the Scrum Master of an already existing Project
     * @param project the Project we want to know the Scrum Master
     * @return the Scrum Master as a User
     * @throws SQLException
     */
    public User getProjectScrumMaster(Project project) throws SQLException {
        return projectFacade.getProjectScrumMaster(project);
    }

    /**
     * This method returns an User, who is the Product Owner of an already existing Project
     * @param project the Project we want to know the Product Owner
     * @return the Product Owner as a User
     * @throws SQLException
     */
    public User getProjectProductOwner(Project project) throws SQLException {
        return projectFacade.getProjectProductOwner(project);
    }

    /**
     * This method returns a List of User, who are the Developers of an already existing Project
     * @param project the Project we want to know the Devs
     * @return The Devs as a List of User
     * @throws SQLException
     */
    public List<User> getProjectDevelopers(Project project) throws SQLException {
        return projectFacade.getProjectDevelopers(project);
    }

    /**
     * This method returns a new Project with the following information :
     * @param name a String for the Project name
     * @param summary a String for the Project summary
     * @param type a String for the Sprint type (which could be "0", "release" or "normal")
     * @param deadline a Date which represent the end of this Project
     * @param user the User who created this Project, he will become the Admin of this Project
     * @return the newly created Project
     * @throws SQLException
     */
    public Project createProject(String name, String summary, String type, Date deadline, User user) throws SQLException {
        return projectFacade.createProject(name, summary, type, deadline, user);
    }

    /**
     * this method is to edit an already existing Project
     * @param project the edited Project
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean editProject(Project project) throws SQLException {
        return projectFacade.editProject(project);
    }

    /**
     * This method is to delete the given Project
     * @param project the Project we want to delete
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean deleteProject(Project project) throws SQLException {
        return projectFacade.deleteProject(project);
    }

    /**
     * This method is to add a Collaborator to an already existing Project, it also gives him a role for this Project and set him as an Admin or not
     * @param idProject a int representing the ID of the Project wa want to add the Collaborator
     * @param idCollaborator a int representing the ID of the Collaborator we want to add
     * @param idRole a int representing the ID of the Role we want to give him
     * @param isAdmin a boolean, if true the Collaborator will be added to the Project as an Admin, else it will not be an Admin
     * @return the Collaborator we added to the Project
     * @throws SQLException
     */
    public Collaborator addCollaborator(int idProject, int idCollaborator, int idRole, boolean isAdmin) throws SQLException {
        return projectFacade.addCollaborator(idProject, idCollaborator, idRole, isAdmin);
    }

    /**
     * This method is to Edit the info our permission of a Collaborator for any already existing Project
     * @param collaborator the Collaborator we want to change the infos, such as his role or if he is an Admin of this Project
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean editCollaborator(Collaborator collaborator) throws SQLException {
        return projectFacade.editCollaborator(collaborator);
    }

    /**
     * This method remove any Collaborator for an already existing Project
     * @param collaborator the Collaborator to be deleted
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean removeCollaborator(Collaborator collaborator) throws SQLException {
        return projectFacade.removeCollaborator(collaborator);
    }

    /**
     * This method returns an already existing Sprint, of any Project, thanks to its ID
     * @param idSprint an int representing the ID of the Sprint we want
     * @param project the Project we want the Sprint from
     * @return the Sprint with the matching ID
     * @throws SQLException
     */
    public Sprint getSprintByID(int idSprint, Project project) throws SQLException {
        return sprintFacade.getSprintById(idSprint, project);
    }

    /**
     * This method is to create a Sprint
     * @param project the Project we want to create the Sprint into
     * @param type a String representing the type of the Sprint we want to create (could be "0", "release" or "normal")
     * @param start a Date representing the beginning of the Sprint
     * @param end a Date representing the ending of the Sprint
     * @return the newly created Sprint
     * @throws SQLException
     */
    public Sprint createSprint(Project project, String type, Date start, Date end) throws SQLException {
        return sprintFacade.createSprint(project, type, start, end);
    }

    /**
     * This method is to update any already existing Sprint
     * @param sprint the updated Sprint
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean updateSprint(Sprint sprint) throws SQLException {
        return sprintFacade.updateSprint(sprint);
    }

    /**
     * This method is to delete any already existing Sprint
     * @param sprint the Sprint we want to delete
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean deleteSprint(Sprint sprint) throws SQLException {
        return sprintFacade.deleteSprint(sprint);
    }

    /**
     * This method is to delete any already existing Backlog
     * @param project the Backlog we want to delete
     * @return true if the operation succeed, else return false
     * @throws SQLException
     */
    public boolean deleteBacklogs(Project project) throws SQLException {
        return backlogFacade.deleteBacklogs(project);
    }

    /**
     * This method return all the Sprint for any already existing Project
     * @param project the Project we want the Sprints
     * @return a tab of Sprint
     * @throws SQLException
     */
    public Sprint[] getSprintsByProject(Project project) throws SQLException {
        return sprintFacade.getSprintsByProject(project);
    }
}
