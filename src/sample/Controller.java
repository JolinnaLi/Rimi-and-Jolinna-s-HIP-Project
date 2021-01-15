package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class Controller{

    //private declarations
    @FXML
    private Button speakBtn;
    @FXML
    private Button gatherBtn;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button walkAroundBtn;
    @FXML
    private Pane webPanel;


    static Stage prevStage; //maintains which stage is being used.
    //end private declarations

    public static void setPrevStage(Stage stage)
    {
        prevStage = stage;
    }

    public void handleButtonAction()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherGatherMode.fxml"));
            Parent gatherMode = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(gatherMode));
            prevStage.close(); //close the previous stage
            setPrevStage(stage); //set current stage to previous
            stage.show();
        } catch(Exception e) {
            System.out.println("Can't load new window");
            e.printStackTrace();
        }
    }
    public void onWalkAroundClicked()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherWalkAroundMode.fxml"));
            Parent walkAroundMode = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(walkAroundMode));
            prevStage.close(); //close the previous stage
            setPrevStage(stage); //set current stage to previous
            stage.show();
        } catch(Exception e) {
            System.out.println("Can't load new window");
            e.printStackTrace();
        }
    }
    public void onSpeakClicked()
    {
        System.out.println("Speaking");
    }

    //end public methods
}
