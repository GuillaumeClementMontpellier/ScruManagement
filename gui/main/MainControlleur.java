package gui.main;

import business.system.Projet;
import business.system.User;

public interface MainControlleur {

    void setProjet(Projet projet);

    void setUser(User user);

    void setHomeControlleur(HomeController homeControlleur);

    void init(Object param);

    // TODO : add comment handle ?

}
