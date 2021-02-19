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
    @FXML
    private Pane stationaryImagePanel;
    @FXML
    private Label rimiLabel;


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
        double newY = imagePanel.getLayoutY();
        newY = newY - 5;
        if ((newY >= 28)&&(checkAvatarLocationY(imagePanel.getLayoutX(), newY)))
        {
            imagePanel.setLayoutY(newY);
        }
        System.out.println("Y: " +imagePanel.getLayoutY());
    }

    public void onMoveDownClicked() {
        double newY = imagePanel.getLayoutY();
        newY = newY + 10;
        if ((newY <= 403)&&(checkAvatarLocationY(imagePanel.getLayoutX(), newY)))
        {
            imagePanel.setLayoutY(newY);
        }
        System.out.println("Y: " +imagePanel.getLayoutY());
    }

    public void onMoveRightClicked() {
        double newX = imagePanel.getLayoutX();
        newX = newX + 10;
        if ((newX <= 220)&&(checkAvatarLocationX(newX, imagePanel.getLayoutY())))
        {
            imagePanel.setLayoutX(newX);
        }
        System.out.println("X: " +imagePanel.getLayoutX());
    }

    public void onMoveLeftClicked() {
        double newX = imagePanel.getLayoutX();
        newX = newX - 10;
        if ((newX >= 5)&&(checkAvatarLocationX(newX, imagePanel.getLayoutY())))
        {
            imagePanel.setLayoutX(newX);
        }
        System.out.println("X: " +imagePanel.getLayoutX());
    }

    public boolean checkAvatarLocationY(double movingLocationX, double movingLocationY)
    {
        double stationaryLocationX = stationaryImagePanel.getLayoutX();
        double stationaryLocationY = stationaryImagePanel.getLayoutY();
        boolean canMoveY=false;
        if (stationaryLocationX-movingLocationX>50)
        {
            canMoveY=true;
        }
        if (stationaryLocationX-movingLocationX<-50)
        {
            canMoveY=true;
        }
        if (stationaryLocationY-movingLocationY>50)
        {
            canMoveY=true;
        }
        if (stationaryLocationY-movingLocationY<-50)
        {
            canMoveY=true;
        }
        return canMoveY;
    }

    public boolean checkAvatarLocationX(double movingLocationX, double movingLocationY)
    {
        double stationaryLocationX = stationaryImagePanel.getLayoutX();
        double stationaryLocationY = stationaryImagePanel.getLayoutY();
        boolean canMoveX=false;
        if (stationaryLocationX-movingLocationX>50)
        {
            canMoveX=true;
        }
        if (stationaryLocationX-movingLocationX<-50)
        {
            canMoveX=true;
        }
        if (stationaryLocationY-movingLocationY>50)
        {
            canMoveX=true;
        }
        if (stationaryLocationY-movingLocationY<-50) {
            canMoveX = true;
        }
        return canMoveX;
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
