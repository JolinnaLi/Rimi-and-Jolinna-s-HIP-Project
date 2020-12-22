package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            //load main screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Scene splashScene = new Scene(loader.load());
            primaryStage.setScene(splashScene);
            primaryStage.setTitle("NAME OF PROJECT GOES HERE");
            primaryStage.setResizable(false); //NOTE: You probably want to leave this as false but will need to specify proper window size for your project.
            primaryStage.show();

        } catch (Exception e) {
            //If something fails in your program, this will print out where the error is.
            e.printStackTrace();
        }
    }

}
