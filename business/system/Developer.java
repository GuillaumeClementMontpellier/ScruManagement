package business.system;

public class Developer extends Collaborator {

    public Developer(int idUser, int idProject) {
        super.idRole = 4;
        super.idUser = idUser;
        super.idProject = idProject;
        super.isAdmin = false;
    }

    public Developer(int idUser, int idProject, boolean isAdmin) {
        super.idRole = 4;
        super.idUser = idUser;
        super.idProject = idProject;
        super.isAdmin = isAdmin;
    }
}