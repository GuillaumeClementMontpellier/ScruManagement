package gui.main;

import business.system.Project;
import business.system.User;

public abstract class AbstractController implements MainController {

    private Project project;
    private User activeUser;
    private HomeController homeController;

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

    protected HomeController getHomeController() {
        return homeController;
    }

    @Override
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void setUser(User user) {
        this.activeUser = user;
    }

    @Override
    public abstract void init(Object param);
}
