package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;



public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            //load main screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherWalkAroundMode.fxml"));
            Scene walkAroundMode = new Scene(loader.load());
            primaryStage.setScene(walkAroundMode);
            primaryStage.setTitle("Teacher Mode");
            primaryStage.setResizable(false); //NOTE: You probably want to leave this as false but will need to specify proper window size for your project.
            Controller.setPrevStage(primaryStage);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(true);
            primaryStage.requestFocus();
            Controller.isGatherMode = false;


            Controller controller = new Controller();

            walkAroundMode.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.W){
                            controller.onMoveUpClicked();
                        }
                        else if (event.getCode() == KeyCode.S){
                            controller.onMoveDownClicked();
                        }
                        else if (event.getCode() == KeyCode.D){
                            controller.onMoveRightClicked();
                        }
                        else if (event.getCode() == KeyCode.A) {
                            controller.onMoveLeftClicked();
                        }
                    }
            );

        } catch (Exception e) {
            //If something fails in your program, this will print out where the error is.
            e.printStackTrace();
        }

    }
}