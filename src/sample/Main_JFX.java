package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_JFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Graphics graphics = new Graphics();

        primaryStage.setTitle("Lottery! Crazy");
        primaryStage.setScene(new Scene(graphics.createLottery(), 1000, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
