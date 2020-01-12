package gui.main;

import business.system.Project;
import business.system.User;

public abstract class AbstractControlleur implements MainControlleur {

    private Project project;
    private User activeUser;
    private HomeController homeControlleur;

    protected Project getProject() {
        return project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    protected User getActiveUser() {
        return activeUser;
    }

    protected HomeController getHomeControlleur() {
        return homeControlleur;
    }

    @Override
    public void setHomeControlleur(HomeController homeControlleur) {
        this.homeControlleur = homeControlleur;
    }

    @Override
    public void setUser(User user) {
        this.activeUser = user;
    }

    @Override
    public abstract void init(Object param);
}
