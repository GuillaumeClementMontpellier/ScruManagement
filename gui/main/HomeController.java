package gui.main;

import business.system.User;
import gui.userstory.NewUserStoryCreation;
import javafx.fxml.FXML;
import javafx.stage.Window;

public class HomeController {


    @FXML
    private Window childContent;

    @FXML
    private NewUserStoryCreation childContentController;

    private User activeUser;
}
