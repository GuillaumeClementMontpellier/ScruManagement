package gui.backlog;

import business.facade.GlobalFacade;
import business.system.Backlog;
import business.system.Column;
import business.system.Component;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class BacklogController extends AbstractController {
    @FXML
    private TextField nomColumnField;
    @FXML
    private GridPane tab;

    private Backlog backlog = null;
    private Column[] allColumns = null;
    private String type;

    @Override
    public void init(Object param) {

        this.type = (String) param;

        // Fetch the Columns
        try {
            if (type.equals("Product") || type.equals("Sprint")) {
                this.backlog = GlobalFacade.getInstance().getProductBacklog(this.getProject());
            } else {
                this.backlog = GlobalFacade.getInstance().getTicketBacklog(this.getProject());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            this.allColumns = GlobalFacade.getInstance().getColumn(this.backlog);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < allColumns.length; i++) { // For each Column

            // Set the Column Title
            FXMLLoader loaderTitle = new FXMLLoader(getClass().getResource("RowTitle.fxml"));
            Parent root;
            try {
                root = loaderTitle.load();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            RowTitleController rtc = loaderTitle.<RowTitleController>getController();
            rtc.setComponent(allColumns[i].getName());
            this.tab.add(root, i, 0);

            // Fetch the Components of the Column
            Component[] allComponents;
            try {
                if (type.equals("Product") || type.equals("Sprint")) {
                    allComponents = GlobalFacade.getInstance().getUserStory(allColumns[i]);
                } else {
                    allComponents = GlobalFacade.getInstance().getTickets(allColumns[i]);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                continue;
            }

            for (int j = 0; j < allComponents.length; j++) { // for Each component

                // DIsplay Component View
                Component us = allComponents[j];
                FXMLLoader loaderRow = new FXMLLoader(getClass().getResource("Row.fxml"));

                try {
                    root = loaderRow.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                RowController rc = loaderRow.<RowController>getController();
                rc.setComponent(us);
                rc.setController(getHomeController());

                this.tab.add(root, i, j + 1);
            }

        }
    }

    public void handleConfirm(ActionEvent actionEvent) {
        System.out.println("BacklogController.handleConfirm");
    }
}
