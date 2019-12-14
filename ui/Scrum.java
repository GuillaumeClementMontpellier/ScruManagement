package ui;

import DAO.factory.FactoryDAOMariaDB;
import business.facade.GlobalFacade;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.parent.LoginLayout;

public class Scrum extends Application {

    public static void main(String[] args) {
        //parse env var
        String url = "jdbc:mysql://address=(host=nkpl8b2jg68m87ht.cbetxkdyhwsb.us-east-1.rds.amazonaws.com)(port=3306)/gh1gh9s8lix3nsqp";
        String dbUser = "vw3gwm1rplh4gm50";
        String dbPwd = "v9v5u8y2eipsxqla";

        //create factory and add it to facade
        GlobalFacade.getInstance().setFactory(new FactoryDAOMariaDB(url, dbUser, dbPwd));

        //start
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scrum Welcome");

        // Setup
        Scene scene = new Scene(new LoginLayout(), 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
