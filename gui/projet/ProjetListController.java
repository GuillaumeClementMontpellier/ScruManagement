package gui.projet;

import business.facade.GlobalFacade;
import business.system.Projet;
import business.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import main.Scrum;

import java.io.IOException;
import java.util.List;

public class ProjetListController {
    @FXML
    private VBox vertPane;

    private User user;
    private List<Projet> projectList;

    @FXML
    void goToProjectCreation() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("./CreateProject.fxml"));

        Scene scene = new Scene(root);
        Scrum.getStage().setScene(scene);
    }

    public void setUser(User u) throws IOException {
        this.user = u;
        this.projectList = GlobalFacade.getInstance().getProjectListFromUser(u);

        for (Projet p : projectList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Projet.fxml"));
            Parent root = loader.load();
            ProjetController cont = loader.<ProjetController>getController();

            cont.setProject(p);
            cont.setUser(u);

            vertPane.getChildren().add(root);
        }
        vertPane.autosize();
    }


}
