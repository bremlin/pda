package pda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Аналитика данных проекта");
        primaryStage.setScene(new Scene(root, 1800, 950));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
