package gui.main;

import business.system.Project;
import business.system.User;

public interface MainControlleur {

    void setProject(Project project);

    void setUser(User user);

    void setHomeControlleur(HomeController homeControlleur);

    void init(Object param);

    // TODO : add comment handle ?

}
