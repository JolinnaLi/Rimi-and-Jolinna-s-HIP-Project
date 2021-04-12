package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Controller {

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
    private ImageView teacherImageGatherView;
    @FXML
    private ImageView teacherImageWalkAroundView;
    @FXML
    private Pane teacherImagePanel;
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
    private ImageView avatarImageView;
    @FXML
    private CheckBox studentCheckBox;
    @FXML
    private Button button;
    @FXML
    private TitledPane studentVideo;

    private Image currentImage;
    static Stage prevStage; //maintains which stage is being used.

    //end private declarations

    public static boolean isGatherMode = false;

    public static void setPrevStage(Stage stage)
    {
        prevStage = stage;
    }

    public void onGatherModeClicked()
    {
        try{
            currentImage = teacherImageWalkAroundView.getImage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherGatherMode.fxml"));
            Parent gatherMode = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(gatherMode));
            prevStage.close(); //close the previous stage
            setPrevStage(stage); //set current stage to previous
            stage.show();
            isGatherMode = true;
            if (currentImage != null)
            {
                teacherImageGatherView.setImage(currentImage);
            }
	} 
	catch(Exception e) 
	{
            System.out.println("Can't load new window");
            e.printStackTrace();
        }
    }

    public void onWalkAroundClicked()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherWalkAroundMode.fxml"));
            Parent walkAroundMode = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(walkAroundMode));
            prevStage.close(); //close the previous stage
            setPrevStage(stage); //set current stage to previous
            stage.show();
            isGatherMode = false;
            if (currentImage != null)
            {
                teacherImageWalkAroundView.setImage(currentImage);
            }

        } catch (Exception e) {
            System.out.println("Can't load new window");
            e.printStackTrace();
        }
    }

    public void onSpeakClicked()
    {
        System.out.println("Speaking");
    }

    public void onMoveUpClicked()
    {
        System.out.println("hi");
        moveUpButton.setStyle("-fx-background-color: Grey");
        System.out.println("how");
        double newY = teacherImagePanel.getLayoutY();
        System.out.println("are");
        int step=10;
        int upperBound=28;
        int overlapThreshold= 50;
        int checkboxThreshold=100;
        System.out.println("you");
        newY = newY - step;
        System.out.println("doing");
        if ((newY >= upperBound)&&(!isClose(teacherImagePanel.getLayoutX(),newY,overlapThreshold)))
        {
           teacherImagePanel.setLayoutY(newY);
        }
        boolean closeToStudent=isClose(teacherImagePanel.getLayoutX(),teacherImagePanel.getLayoutY(),checkboxThreshold);
        studentCheckBox.setSelected(closeToStudent);
        studentVideo.setExpanded(closeToStudent);
    }

    public void onMoveUpReleased()
    {
        moveUpButton.setStyle("-fx-background-color: White");
    }

    public void onMoveDownClicked()
    {
        moveDownButton.setStyle("-fx-background-color: Grey"); //problem here
        double newY = teacherImagePanel.getLayoutY(); //and here
        int step = 10;
        int lowerBound = 400;
        int overlapThreshold = 50;
        int checkboxThreshold = 100;
        newY = newY + step;
        if ((newY <= lowerBound) && (!isClose(teacherImagePanel.getLayoutX(), newY, overlapThreshold)))
        {
           teacherImagePanel.setLayoutY(newY);
        }
        boolean closeToStudent = isClose(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        studentCheckBox.setSelected(closeToStudent);
        studentVideo.setExpanded(closeToStudent);
    }

    public void onMoveDownReleased()
    {
        moveDownButton.setStyle("-fx-background-color: White");
    }

    public void onMoveRightClicked()
    {
        moveRightButton.setStyle("-fx-background-color: Grey");
        double newX = teacherImagePanel.getLayoutX();
        int step = 10;
        int rightBound = 220;
        int overlapThreshold = 50;
        int checkboxThreshold = 100;
        newX = newX + step;
        if ((newX <= rightBound) && (!isClose(newX, teacherImagePanel.getLayoutY(), overlapThreshold)))
        {
           teacherImagePanel.setLayoutX(newX);
        }
        boolean closeToStudent = isClose(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        studentCheckBox.setSelected(closeToStudent);
        studentVideo.setExpanded(closeToStudent);
    }

    public void onMoveRightReleased(){ moveRightButton.setStyle("-fx-background-color: White"); }

    public void onMoveLeftClicked() {
        moveLeftButton.setStyle("-fx-background-color: Grey");
        double newX = teacherImagePanel.getLayoutX();
        int step = 10;
        int leftBound = 5;
        int overlapThreshold = 50;
        int checkboxThreshold = 100;
        newX = newX - step;
        if ((newX >= leftBound) && (!isClose(newX, teacherImagePanel.getLayoutY(), overlapThreshold)))
        {
           teacherImagePanel.setLayoutX(newX);
        }
        boolean closeToStudent = isClose(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        studentCheckBox.setSelected(closeToStudent);
        studentVideo.setExpanded(closeToStudent);
    }

    public void onMoveLeftReleased(){
        moveLeftButton.setStyle("-fx-background-color: White");
    }

    public boolean isClose(double movingLocationX, double movingLocationY,int range)
    {
        double stationaryLocationX = stationaryImagePanel.getLayoutX();
        double stationaryLocationY = stationaryImagePanel.getLayoutY();
        boolean closeToY = false;
        boolean closeToX = false;
        if (Math.abs(stationaryLocationY-movingLocationY)<range)
        {
            closeToY = true;
        }
        if (Math.abs(stationaryLocationX - movingLocationX)<range)
        {
            closeToX = true;
        }
        return closeToX&&closeToY;
    }


    public void onUploadImageClicked() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            //Creating an image
            Image image = null;
            try {
                image = new Image(new FileInputStream(chooser.getSelectedFile()));
                if (isGatherMode) {
                    teacherImageGatherView.setImage(image);
                } else {
                    teacherImageWalkAroundView.setImage(image);
                    avatarImageView.setImage(image);
                }
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                File outputImage = new File("teacherimage.JPG");
                ImageIO.write(bufferedImage, "jpg", outputImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //end public methods
}

