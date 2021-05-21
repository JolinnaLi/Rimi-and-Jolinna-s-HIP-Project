package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
import javafx.scene.text.Text;

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
    private ImageView teacherImageGatherView = new ImageView();
    @FXML
    private ImageView teacherImageWalkAroundView = new ImageView();
    @FXML
    private Pane teacherImagePanel = new Pane();
    @FXML
    private Button moveUpButton = new Button();
    @FXML
    private Button moveDownButton = new Button();
    @FXML
    private Button moveRightButton = new Button();
    @FXML
    private Button moveLeftButton = new Button();
    @FXML
    private ImageView avatarImageView;
    @FXML
    private Button button;
    @FXML
    private Pane stationaryPanelRimi = new Pane();
    @FXML
    private CheckBox rimiCheckBox = new CheckBox();
    @FXML
    private TitledPane rimiVideo = new TitledPane();
    @FXML
    private Pane stationaryPanelJolinna = new Pane();
    @FXML
    private CheckBox jolinnaCheckBox = new CheckBox();
    @FXML
    private TitledPane jolinnaVideo = new TitledPane();
    @FXML
    private Pane stationaryPanelKevin = new Pane();
    @FXML
    private CheckBox kevinCheckBox = new CheckBox();
    @FXML
    private TitledPane kevinVideo = new TitledPane();
    @FXML
    private Pane stationaryPanelConnor = new Pane();
    @FXML
    private CheckBox connorCheckBox = new CheckBox();
    @FXML
    private TitledPane connorVideo = new TitledPane();

    private Image currentImage;
    static Stage prevStage; //maintains which stage is being used.

    //end private declarations

    public static boolean isGatherMode = false;

    public static void setPrevStage(Stage stage)
    {
        prevStage = stage;
    }

    public void startProgram(Stage primaryStage)
    {
        try {
            //load main screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherWalkAroundMode.fxml"));
            Scene walkAroundMode = new Scene(loader.load());
            primaryStage.setScene(walkAroundMode);
            primaryStage.setTitle("Teacher Mode");
            primaryStage.setResizable(false); //NOTE: You probably want to leave this as false but will need to specify proper window size for your project.
            setPrevStage(primaryStage);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(true);
            primaryStage.requestFocus();
            isGatherMode = false;

            walkAroundMode.setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.W) {
                            onMoveUpClicked();
                        } else if (event.getCode() == KeyCode.S) {
                            onMoveDownClicked();
                        } else if (event.getCode() == KeyCode.D) {
                            onMoveRightClicked();
                        } else if (event.getCode() == KeyCode.A) {
                            onMoveLeftClicked();
                        }
                    }
            );

        } catch (Exception e) {
            //If something fails in your program, this will print out where the error is.
            e.printStackTrace();
        }
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
        moveUpButton.setStyle("-fx-background-color: Grey");
        double newY = teacherImagePanel.getLayoutY();
        int step=10;
        int upperBound=28;
        int overlapThreshold= 40;
        int checkboxThreshold=100;
        newY = newY - step;
        if ((newY >= upperBound)&&(!isCloseRimi(teacherImagePanel.getLayoutX(),newY,overlapThreshold)) && (!isCloseKevin(teacherImagePanel.getLayoutX(), newY, overlapThreshold)) && (!isCloseJolinna(teacherImagePanel.getLayoutX(), newY, overlapThreshold)) && (!isCloseConnor(teacherImagePanel.getLayoutX(), newY, overlapThreshold)))
        {
            teacherImagePanel.setLayoutY(newY);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        kevinVideo.setExpanded(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        connorVideo.setExpanded(closeToConnor);
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
        if ((newY <= lowerBound) && (!isCloseRimi(teacherImagePanel.getLayoutX(), newY, overlapThreshold)) && (!isCloseJolinna(teacherImagePanel.getLayoutX(), newY, overlapThreshold)) && (!isCloseKevin(teacherImagePanel.getLayoutX(), newY, overlapThreshold)) && (!isCloseConnor(teacherImagePanel.getLayoutX(), newY, overlapThreshold)))
        {
            teacherImagePanel.setLayoutY(newY);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
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
        if ((newX <= rightBound) && (!isCloseRimi(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseJolinna(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseKevin(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseConnor(newX, teacherImagePanel.getLayoutY(), overlapThreshold)))
        {
            teacherImagePanel.setLayoutX(newX);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
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
        if ((newX >= leftBound) && (!isCloseRimi(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseJolinna(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) &&(!isCloseKevin(newX, teacherImagePanel.getLayoutY(), overlapThreshold)) && (!isCloseConnor(newX, teacherImagePanel.getLayoutY(), overlapThreshold)))
        {
            teacherImagePanel.setLayoutX(newX);
        }
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
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

    public boolean isCloseKevin(double movingLocationX, double movingLocationY, int range)
    {
        double stationaryLocationX = stationaryPanelKevin.getLayoutX();
        double stationaryLocationY = stationaryPanelKevin.getLayoutY();
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

    public boolean isCloseConnor(double movingLocationX, double movingLocationY, int range)
    {
        double stationaryLocationX = stationaryPanelConnor.getLayoutX();
        double stationaryLocationY = stationaryPanelConnor.getLayoutY();
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
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
    }

    public void onJolinnaChecked()
    {
        int checkboxThreshold = 80;
        teacherImagePanel.setLayoutX(180);
        teacherImagePanel.setLayoutY(323);
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
    }

    public void onKevinChecked()
    {
        int checkboxThreshold = 80;
        teacherImagePanel.setLayoutX(89);
        teacherImagePanel.setLayoutY(299);
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
    }

    public void onConnorChecked()
    {
        int checkboxThreshold = 80;
        teacherImagePanel.setLayoutX(210);
        teacherImagePanel.setLayoutY(120);
        boolean closeToRimi = isCloseRimi(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToJolinna = isCloseJolinna(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToKevin = isCloseKevin(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        boolean closeToConnor = isCloseConnor(teacherImagePanel.getLayoutX(), teacherImagePanel.getLayoutY(), checkboxThreshold);
        rimiCheckBox.setSelected(closeToRimi);
        jolinnaCheckBox.setSelected(closeToJolinna);
        kevinCheckBox.setSelected(closeToKevin);
        connorCheckBox.setSelected(closeToConnor);
        rimiVideo.setExpanded(closeToRimi);
        jolinnaVideo.setExpanded(closeToJolinna);
        kevinVideo.setExpanded(closeToKevin);
        connorVideo.setExpanded(closeToConnor);
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

