package business.facade;

import business.system.Projet;
import business.system.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjetFacade {

    public Projet[] getProjectListFromUser(User u) throws SQLException {
        // TODO : use DAO
        System.out.println("ProjetFacade.getProjectListFromUser");
        System.out.println("u = " + u);
        ArrayList<Projet> pList = new ArrayList<>();
        pList.add(getProjectByID(-1));
        return pList.toArray(new Projet[1]);
    }

    public boolean addProject(Projet projet, User user) {
        // TODO : Use DAO
        System.out.println("ProjetFacade.addProject");
        System.out.println("projet = " + projet + ", user = " + user);
        return false;

    }

    public Projet getProjectByID(int idProject) throws SQLException {
        // TODO : Use DAO
        return new Projet(-1, "NomMock Projet", "summ\nary", "type", new Date(System.currentTimeMillis()));
    }

    public User getProjectAdmin(int idProject) throws SQLException {
        // TODO : Use DAO
        return null;
    }

    public User getProjectScrumMaster(int idProject) throws SQLException {
        // TODO : Use DAO
        return null;
    }

    public User getProjectProductOwner(int idProject) throws SQLException {
        // TODO : Use DAO
        return null;
    }

    public User[] getProjectDevelopers(int idProject) throws SQLException {
        // TODO : Use DAO
        return null;
    }

    public User[] getProjectTeam(int idProject) throws SQLException {
        // TODO : Use DAO
        return null;
    }


    public boolean createProject(Projet projet, User creator) throws SQLException {
        // TODO : Use DAO
        return false;
    }

    public boolean editProject(Projet projet) throws SQLException {
        // TODO : Use DAO
        return false;
    }

    public boolean deleteProject(Projet projet) throws SQLException {
        // TODO : Use DAO
        return false;

    }


    public boolean addCollaborator(int idProject, User collaborator) throws SQLException {
        // TODO : Use DAO
        return false;

    }

    public boolean editCollaborator(int idProject, User collaborator) throws SQLException {
        // TODO : Use DAO
        return false;

    }

    public boolean removeCollaborator(int idProject, User collaborator) throws SQLException {
        // TODO : Use DAO
        return false;

    }
}
