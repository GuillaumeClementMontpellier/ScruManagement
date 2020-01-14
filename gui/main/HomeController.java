package gui.main;

import business.system.Project;
import business.system.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.Scrum;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class HomeController {

    private User activeUser;
    private Project project;

    @FXML
    private GridPane listSprint;

    @FXML
    private Pane childContent;

    public void setUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Change la sub-scene (volet de droite) de la fenetre
     *
     * @param pathToFxml Chemin d'un fxml dont le controlleur implemente MainControlleur
     * @param param      Objet qui sera passé a la fonction init du controlleur
     * @throws IOException
     **/
    public void changeSubScene(String pathToFxml, Object param) throws IOException {

        URL resource = getClass().getResource(pathToFxml);
        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        MainController childContentController = loader.getController();

        childContentController.setHomeController(this);
        childContentController.setProject(this.project);
        childContentController.setUser(this.activeUser);
        childContentController.init(param);

        ObservableList<Node> children = childContent.getChildren();
        System.out.println(children);

        if (children.size() > 0) {
        children.set(0, root);
        } else {
            children.add(root);
        }

    }

    public void handleChat() {
        // TODO
        System.out.println("HomeController.handleChat");
    }

    public void handleUserSetting(MouseEvent mouseEvent) throws IOException, SQLException {
        // TODO
        System.out.println("HomeController.handleUserSetting");
        Scrum.goToProjectList(activeUser, getClass().getResource("../project/ProjectList.fxml"));

    }

    public void handleProjectSetting(MouseEvent mouseEvent) throws IOException, SQLException {
        this.changeSubScene("../project/EditProject.fxml", null);
    }


    public void goToProductBacklog(ActionEvent event) {
        try {
            this.changeSubScene("../backlog/Backlog.fxml", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToTicketBacklog(ActionEvent event) {
        // TODO
        System.out.println("HomeController.goToTicketBacklog");
    }

    public void handleCreationTicket(MouseEvent event) {
        try {
            this.changeSubScene("../ticket/CreateTicket.fxml", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCreationUserStory(MouseEvent mouseEvent) {
        try {
            this.changeSubScene("../userstory/CreateUserStory.fxml", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleCreationSprint(MouseEvent mouseEvent) {
        System.out.println("HomeController.handleCreationSprint");
    }

    public void goToSprints(ActionEvent event) {
        System.out.println("HomeController.goToSprints");
    }
}
