package business.system;

public abstract class Collaborator {
    // 2 -> Scrum Master
    // 3 -> Product Owner
    // 4 -> Developer
    protected int idRole;
    protected int idUser;
    protected int idProject;
    protected boolean isAdmin;

    public int getIdRole() {
        return idRole;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdProject() {
        return idProject;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}