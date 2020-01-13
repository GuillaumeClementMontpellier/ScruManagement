package gui.main;

import business.system.Project;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class HomeController {

    private User activeUser;
    private Project project;

    @FXML
    private GridPane listSprint;

    @FXML
    private Node childContent;

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

        childContent.getScene().setRoot(root);
    }

    public void handleChat() {
        // TODO
        System.out.println("HomeController.handleChat");
    }

    public void handleUserSetting(MouseEvent mouseEvent) {
        // TODO
        System.out.println("HomeController.handleUserSetting");
    }

    public void handleProjectSetting(MouseEvent mouseEvent) throws IOException {
        changeSubScene("../project/EditProject.fxml", null);
    }

    public void handleComment(MouseEvent mouseEvent) {
        // TODO
        System.out.println("HomeController.handleComment");
    }

    public void goToProductBacklog(ActionEvent event) {
        try {
            this.changeSubScene("../backlog/ProductBacklog.fxml",null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToTicketBacklog(ActionEvent event) {
        // TODO
        System.out.println("HomeController.goToTicktBacklog");
    }

    public void handleCreationTicket(MouseEvent event) {
        try {
            this.changeSubScene("../ticket/CreateTicket.fxml",null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
