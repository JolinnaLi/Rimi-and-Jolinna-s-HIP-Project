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
    private ImageView avatarImageView;
    @FXML
    private Button button;
    @FXML
    private Pane stationaryPanelRimi;
    @FXML
    private CheckBox rimiCheckBox;
    @FXML
    private TitledPane rimiVideo;
    @FXML
    private Pane stationaryPanelJolinna;
    @FXML
    private CheckBox jolinnaCheckBox;
    @FXML
    private TitledPane jolinnaVideo;

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
            currentImage = teacherImageGatherView.getImage();
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
        int overlapThreshold= 40;
        int checkboxThreshold=80;
        System.out.println("you");
        newY = newY - step;
        System.out.println("doing");
        if ((newY >= upperBound)&&(!isCloseRimi(teacherImagePanel.getLayoutX(),newY,overlapThreshold)) && (!isCloseJolinna(teacherImagePanel.getLayoutX(), newY, overlapThreshold)))
        {
            teacherImagePanel.setLayoutY(newY);
        }
        System.out.println("I");
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        System.out.println("am");
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        System.out.println("fine");
        rimiCheckBox.setSelected(closeToRimi);
        System.out.println("today");
        jolinnaCheckBox.setSelected(closeToJolinna);
        System.out.println("I guess");
        rimiVideo.setExpanded(closeToRimi);
        System.out.println("wbu");
        jolinnaVideo.setExpanded(closeToJolinna);
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
        int checkboxThreshold = 80;
        newY = newY + step;
        if ((newY <= lowerBound) && (!isCloseRimi(teacherImagePanel.getLayoutX(), newY, overlapThreshold)) && (!isCloseJolinna(teacherImagePanel.getLayoutX(), newY, overlapThreshold)))
        {
            teacherImagePanel.setLayoutY(newY);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
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
        int checkboxThreshold = 80;
        newX = newX + step;
        if ((newX <= rightBound) && (!isCloseRimi(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseJolinna(newX, teacherImagePanel.getLayoutY(), overlapThreshold)))
        {
            teacherImagePanel.setLayoutX(newX);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
    }

    public void onMoveRightReleased(){ moveRightButton.setStyle("-fx-background-color: White"); }

    public void onMoveLeftClicked() {
        moveLeftButton.setStyle("-fx-background-color: Grey");
        double newX = teacherImagePanel.getLayoutX();
        int step = 10;
        int leftBound = 5;
        int overlapThreshold = 50;
        int checkboxThreshold = 80;
        newX = newX - step;
        if ((newX >= leftBound) && (!isCloseRimi(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseJolinna(newX, teacherImagePanel.getLayoutY(), overlapThreshold)))
        {
            teacherImagePanel.setLayoutX(newX);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
    }

    public void onMoveLeftReleased(){
        moveLeftButton.setStyle("-fx-background-color: White");
    }

    public boolean isCloseRimi(double movingLocationX, double movingLocationY, int range)
    {
        double stationaryLocationX = stationaryPanelRimi.getLayoutX();
        double stationaryLocationY = stationaryPanelRimi.getLayoutY();
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

    public boolean isCloseJolinna(double movingLocationX, double movingLocationY, int range)
    {
        double stationaryLocationX = stationaryPanelJolinna.getLayoutX();
        double stationaryLocationY = stationaryPanelJolinna.getLayoutY();
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

    public void onRimiChecked()
    {
        int checkboxThreshold = 80;
        teacherImagePanel.setLayoutX(170);
        teacherImagePanel.setLayoutY(186);
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
    }

    public void onJolinnaChecked()
    {
        int checkboxThreshold = 80;
        teacherImagePanel.setLayoutX(180);
        teacherImagePanel.setLayoutY(323);
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
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
                File tempDirectory = new File(System.getProperty("sample"));
                File outputImage = new File(tempDirectory.getAbsolutePath(),"teacherimage.jpg");
                ImageIO.write(bufferedImage, "jpg", outputImage);
                System.out.println(tempDirectory.getAbsolutePath());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    //end public methods
}

