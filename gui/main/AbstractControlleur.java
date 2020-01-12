package gui.main;

import business.system.Projet;
import business.system.User;

public abstract class AbstractControlleur implements MainControlleur {

    private Projet projet;
    private User activeUser;
    private HomeController homeControlleur;

    protected Projet getProjet() {
        return projet;
    }

    @Override
    public void setProjet(Projet projet) {
        this.projet = projet;
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
