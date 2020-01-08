package business.facade;

import business.system.Projet;
import business.system.User;

import java.util.ArrayList;
import java.util.List;

public class ProjetFacade {
    public List<Projet> getProjectListFromUser(User u) {
        // TODO : use DAO a la place
        System.out.println("ProjetFacade.getProjectListFromUser");
        System.out.println("u = " + u);
        ArrayList<Projet> pList = new ArrayList<>();
        pList.add(new Projet(-1, "NomMock Projet"));
        return pList;
    }
}
