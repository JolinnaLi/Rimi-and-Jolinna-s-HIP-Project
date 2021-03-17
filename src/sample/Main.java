package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            primaryStage.setAlwaysOnTop(true);
            primaryStage.requestFocus();

            Controller controller = new Controller();
            //Jolinna - This is where we need those cases for if certain keys are typed.
            //By having the methods called like this, you are moving images on start-up
            //rather than when something specific occurs.  I'll do the first two for you.
            //Delete these comments when you have completed adding the additional cases.
            walkAroundMode.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event) {
                    switch (event.getCode())
                    {
                        case UP:
                            controller.onMoveUpClicked();
                            break;
                        case DOWN:
                            controller.onMoveDownClicked();
                            break;
                    }
                }
            });
        }

        catch (Exception e)
        {
            //If something fails in your program, this will print out where the error is.
            e.printStackTrace();
        }
    }

}
