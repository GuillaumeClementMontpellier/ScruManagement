package gui.main;

import business.system.Projet;
import business.system.User;

public interface MainController {

    void setProject(Projet project);

    void setUser(User user);

    void setHomeController(HomeController homeController);

    void init(Object param);

    // TODO : add comment handle ?

}
