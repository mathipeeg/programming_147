package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main_JFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Graphics graphics = new Graphics();

        primaryStage.setTitle("Lottery! Crazy");
        primaryStage.setScene(new Scene(graphics.P20(), 1000, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
