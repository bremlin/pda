package pda;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    public Tab projectStructure;
    @FXML
    public Tab factMoreThenPlan;
    @FXML
    public Tab factLessThenPlanComplete;
    @FXML
    public Tab notEqualsStartDate;

    public void fix(ActionEvent actionEvent) {

    }

    public void fixAll(ActionEvent actionEvent) {

    }

    public void openProject(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/openProject.fxml"));
            loader.load();
            OpenProject openProject = loader.getController();

            root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Выберите проект");
            stage.setScene(new Scene(root, 900, 800));
            stage.showAndWait();

            System.out.println("get: " + openProject.getProjectId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openProjectStructure(Event event) {

    }

    public void openFactMoreThenPlan(Event event) {

    }

    public void openFactLessThenPlanComplete(Event event) {

    }

    public void openNotEqualsStartDate(Event event) {

    }
}
