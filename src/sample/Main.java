package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        try {
            //load main screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherWalkAroundMode.fxml"));
            Scene walkAroundMode = new Scene(loader.load());
            primaryStage.setScene(walkAroundMode);
            primaryStage.setTitle("Teacher Mode");
            primaryStage.setResizable(false); //NOTE: You probably want to leave this as false but will need to specify proper window size for your project.
            Controller.setPrevStage(primaryStage);
            primaryStage.show();

            Controller controller = new Controller();
            controller.onMoveUpClicked();
            controller.onMoveDownClicked();
            controller.onMoveRightClicked();
            controller.onMoveLeftClicked();
        }

        catch (Exception e)
        {
            //If something fails in your program, this will print out where the error is.
            e.printStackTrace();
        }
    }

}
