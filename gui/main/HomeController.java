package gui.main;

import business.system.Projet;
import business.system.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Window;

import java.io.IOException;

public class HomeController {

    private User activeUser;
    private Projet projet;

    @FXML
    private Window childContent;

    @FXML
    private MainControlleur childContentController;


    /**
     * Change la sub-scene (volet de droite) de la fenetre
     *
     * @param pathToFxml Chemin d'un fxml dont le controlleur implemente MainControlleur
     * @param param      Objet qui sera passé a la fonction init du controlleur
     * @throws IOException
     **/
    public void changeSubScene(String pathToFxml, Object param) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToFxml));

        Parent root = loader.load();

        childContentController = loader.getController();

        childContentController.setHomeControlleur(this);
        childContentController.setProjet(this.projet);
        childContentController.setUser(this.activeUser);
        childContentController.init(param);

        childContent.getScene().setRoot(root);
    }

}
