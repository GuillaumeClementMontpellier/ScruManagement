package business.system;


public class ScrumMaster extends Collaborator {

    public ScrumMaster(int idUser, int idProject) {
        super.idRole = 2;
        super.idUser = idUser;
        super.idProject = idProject;
        super.isAdmin = false;
    }

    public ScrumMaster(int idUser, int idProject, boolean isAdmin) {
        super.idRole = 2;
        super.idUser = idUser;
        super.idProject = idProject;
        super.isAdmin = isAdmin;
    }
}