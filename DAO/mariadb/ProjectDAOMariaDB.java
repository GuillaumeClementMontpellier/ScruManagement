package DAO.mariadb;

import DAO.ProjectDAO;
import DAO.UserDAO;
import DAO.factory.AbstractFactoryDAO;
import business.system.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOMariaDB extends DAOMariaDB implements ProjectDAO {

    public ProjectDAOMariaDB(String addressDataBase, String userDataBase, String passWordDataBase) throws SQLException {
        super(addressDataBase, userDataBase, passWordDataBase);
    }

    public List<Project> getProjectListFromUser(int idUser) throws SQLException {
        String sql = "Select idProject From WorkOn WHERE idUser = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idUser);
        ResultSet resultSet = pre.executeQuery();
        List<Project> projects = new ArrayList();
        ProjectDAO projectDAO = AbstractFactoryDAO.getInstance().createProjectDAO();
        while (resultSet.next()) {
            projects.add(projectDAO.getProjectByID(resultSet.getInt("idProject")));
        }
        return projects;

    }

    @Override
    public Project getProjectByID(int idProject) throws SQLException {
        ResultSet resultSet = getProject(idProject);
        int id = resultSet.getInt("idProject");
        String name = resultSet.getString("name");
        String summary = resultSet.getString("summary");
        String type = resultSet.getString("type");
        Date deadline = resultSet.getDate("deadline");
        return new Project(id, name, summary, type, deadline);
    }



    @Override
    public List<Collaborator> getProjectTeam(int idProject) throws SQLException {
        ResultSet resultSet = getTeam(idProject);
        List<Collaborator> team = new ArrayList();
        int idUser;
        int idRole;
        boolean isAdmin;
        Collaborator collaborator;
        while (resultSet.next()) {
            idUser = resultSet.getInt("idUser");
            idRole = resultSet.getInt("idRole");
            isAdmin = resultSet.getBoolean("isAdmin");
            if (idRole == 2) {
                collaborator = new ScrumMaster(idUser, idProject, isAdmin);
            }
            else if (idRole == 3) {
                collaborator = new ProductOwner(idUser, idProject, isAdmin);
            }
            else {
                collaborator = new Developer(idUser, idProject, isAdmin);
            }
            team.add(collaborator);
        }
        return team;
    }

    // Currently returning the first (and only one) admin
    // If multiple admin is implemented TODO list of admin
    @Override
    public User getProjectAdmin(int idProject) throws SQLException {
        List<Collaborator> team = getProjectTeam(idProject);
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        int i = 0;
        while (i < team.size()) {
            if (team.get(i).isAdmin()) {
                return userDAO.getUserByIdUser(team.get(i).getIdUser());
            }
            i+=1;
        }
        return null;
    }

    @Override
    public User getProjectScrumMaster(int idProject) throws SQLException {
        List<Collaborator> team = getProjectTeam(idProject);
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        int i = 0;
        while (i < team.size()) {
            if (team.get(i).getIdRole() == 2) {
                return userDAO.getUserByIdUser(team.get(i).getIdUser());
            }
            i+=1;
        }
        return null;
    }

    @Override
    public User getProjectProductOwner(int idProject) throws SQLException {
        List<Collaborator> team = getProjectTeam(idProject);
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        int i = 0;
        while (i < team.size()) {
            if (team.get(i).getIdRole() == 3) {
                return userDAO.getUserByIdUser(team.get(i).getIdUser());
            }
            i+=1;
        }
        return null;
    }

    @Override
    public List<User> getProjectDevelopers(int idProject) throws SQLException {
        List<Collaborator> team = getProjectTeam(idProject);
        UserDAO userDAO = AbstractFactoryDAO.getInstance().createUserDAO();
        List<User> users = new ArrayList<>();
        int i = 0;
        while (i < team.size()) {
            if (team.get(i).getIdRole() == 4) {
                users.add(userDAO.getUserByIdUser(team.get(i).getIdUser()));
            }
            i+=1;
        }
        return users;
    }



    @Override
    public boolean createProject(int id, String name, String summary, String type, Date deadline) throws SQLException {
        String sql = "INSERT INTO Project VALUES (?,?,?,?,?)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, id);
        pre.setString(2, name);
        pre.setString(3, summary);
        pre.setString(4, type);
        pre.setDate(5, deadline);
        pre.execute();
        return true;
    }

    @Override
    public boolean editProject(Project project) throws SQLException {
        String sql = "UPDATE Project SET idRole = ? WHERE idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, project.getId());
        pre.setString(2, project.getName());
        pre.setString(3, project.getSummary());
        pre.setString(4, project.getType());
        pre.setDate(5, project.getDeadline());
        pre.execute();
        return true;
    }

    @Override
    public boolean deleteProject(int idProject) throws SQLException {
        String sql = "DELETE FROM Project WHERE idProject = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        pre.execute();
        return true;
    }



    @Override
    public boolean addCollaborator(int idProject, int idCollaborator, int idRole, boolean isAdmin) throws SQLException {
        String sql = "INSERT INTO WorkOn VALUES (?,?,?,?)";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idCollaborator);
        pre.setInt(2, idProject);
        pre.setInt(3, idRole);
        pre.setBoolean(4, isAdmin);
        pre.execute();
        return true;
    }

    @Override
    public boolean editCollaborator(int idProject, int idCollaborator, int idRole, boolean isAdmin) throws SQLException {
        String sql = "UPDATE WorkOn SET idRole = ? WHERE idUser = ? AND idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idRole);
        pre.setInt(2, idCollaborator);
        pre.setInt(3, idProject);
        pre.setBoolean(4, isAdmin);
        pre.execute();
        return true;
    }

    @Override
    public boolean removeCollaborator(int idProject, int idCollaborator) throws SQLException {
        String sql = "DELETE FROM WorkOn WHERE idUser = ? AND idProject = ? ";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idCollaborator);
        pre.setInt(2, idProject);
        pre.execute();
        return true;
    }



    private ResultSet getProject(int idProject) throws SQLException {
        String sql = "Select * FROM Project WHERE idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        return pre.executeQuery();
    }

    private ResultSet getTeam(int idProject) throws SQLException {
        String sql = "Select * From WorkOn LEFT JOIN Role ON WorkOn.idRole = Role.idRole WHERE idProject = ?";
        PreparedStatement pre = this.connection.prepareStatement(sql);
        pre.setInt(1, idProject);
        return pre.executeQuery();
    }
}
