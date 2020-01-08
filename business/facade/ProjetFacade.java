package business.facade;

import business.system.Projet;
import business.system.User;

import java.util.ArrayList;
import java.util.List;

public class ProjetFacade {
    public List<Projet> getProjectListFromUser(User u) {
        // TODO : use DAO
        System.out.println("ProjetFacade.getProjectListFromUser");
        System.out.println("u = " + u);
        return new ArrayList<Projet>();
    }
}
