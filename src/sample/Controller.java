package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.text.Element;


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
    @FXML
    private Button uploadImageBtn;
    @FXML
    private ImageView teacherImageView;
    @FXML
    private Pane imagePanel;
    @FXML
    private Button moveUpButton;
    @FXML
    private Button moveDownButton;
    @FXML
    private Button moveRightButton;
    @FXML
    private Button moveLeftButton;


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

    public void onMoveUpClicked() {
        double variable = imagePanel.getLayoutY();
        variable = variable - 5;
        if (variable >= 28)
        {
            imagePanel.setLayoutY(variable);
        }
    }

    public void onMoveDownClicked() {
        double variable = imagePanel.getLayoutY();
        variable = variable + 10;
        if (variable <= 403)
        {
            imagePanel.setLayoutY(variable);
        }
    }

    public void onMoveRightClicked() {
        double variable = imagePanel.getLayoutX();
        variable = variable + 10;
        if (variable >= 210) ;
        {
            imagePanel.setLayoutX(variable);
        }
    }

    public void onMoveLeftClicked() {
        double variable = imagePanel.getLayoutX();
        variable = variable - 10;
        if (variable >= 5)
        {
            imagePanel.setLayoutX(variable);
        }
    }

    public void onUploadImageClicked()
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            //Creating an image
            Image image = null;
            try {
                image = new Image(new FileInputStream(chooser.getSelectedFile()));
                teacherImageView.setImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //end public methods
}
