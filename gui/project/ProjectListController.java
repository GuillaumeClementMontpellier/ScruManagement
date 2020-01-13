package gui.project;

import business.facade.GlobalFacade;
import business.system.Projet;
import business.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import main.Scrum;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProjectListController {
    @FXML
    private GridPane listPane;

    private User user;
    private List<Projet> projectList;

    @FXML
    void goToProjectCreation() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./CreateProject.fxml"));
        Parent root = loader.load();

        CreateProjectController cont = loader.<CreateProjectController>getController();
        cont.setUser(user);

        Scene scene = new Scene(root);
        Scrum.getStage().setScene(scene);

    }

    public void setUser(User u) throws IOException, SQLException {
        this.user = u;
        this.projectList = GlobalFacade.getInstance().getProjectListFromUser(u);

        for (int i = 0; i < projectList.size(); i++) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Project.fxml"));
            Parent root = loader.load();
            ProjectController cont = loader.<ProjectController>getController();

            cont.setProject(projectList.get(i));
            cont.setUser(u);

            listPane.add(root, 0, i);
        }

        listPane.autosize();
    }


}
