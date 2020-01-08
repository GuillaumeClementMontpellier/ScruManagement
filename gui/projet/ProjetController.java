package gui.projet;

import business.system.Projet;
import business.system.User;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProjetController {

    private Projet projet;
    private User user;

    @FXML
    private Text projectName;

    @FXML
    void loadProjet() {
        System.out.println("ProjetController.loadProjet");
    }

    public void setProject(Projet p) {
        this.projet = p;
        this.projectName.setText(p.getName());
    }

    public void setUser(User u) {
        this.user = u;
    }
}
