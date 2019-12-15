package ui;

import business.system.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MainLayout extends GridPane {

    private Text lastName;
    private Text firstName;

    public MainLayout(User u) {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        // Text display
        this.firstName = new Text(u.getFirstName());
        this.add(firstName, 1, 1);

        // Text display
        this.lastName = new Text(u.getLastName());
        this.add(lastName, 2, 1);
    }
}
