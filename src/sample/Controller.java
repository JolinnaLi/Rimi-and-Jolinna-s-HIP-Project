package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class Controller{

    //private declarations
    @FXML
    private Button speakBtn;
    @FXML
    private Button gatherBtn;
    @FXML
    private Label welcomeLabel;

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

    public void onSpeakClicked()
    {
        System.out.println("Speaking");
    }
    //end public methods
}
