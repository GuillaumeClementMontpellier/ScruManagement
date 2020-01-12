package main;

import DAO.factory.AbstractFactoryDAO;
import DAO.mariadb.FactoryDAOMariaDB;
import business.system.User;
import gui.project.ProjectListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

public class Scrum extends Application {

    private static Stage stageSingleton = null;

    public static Stage getStage() {
        return stageSingleton;
    }

    public static void goToProjectList(User user, URL resource) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        ProjectListController cont = loader.<ProjectListController>getController();

        cont.setUser(user);

        Scene scene = new Scene(root);
        Scrum.getStage().setScene(scene);

    }

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();

        //parse java.env var
        String url = env.get("DATA_BASE");
        String dbUser = env.get("USER");
        String dbPwd = env.get("PASSWORD");

        //create factory and add it to facade
        AbstractFactoryDAO.setInstance(new FactoryDAOMariaDB(url, dbUser, dbPwd));

        //start
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);

        stageSingleton = stage;
        stage.setTitle("ScruManagement");

        //load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login/Login.fxml"));
        Parent root = loader.load();

        //display fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
