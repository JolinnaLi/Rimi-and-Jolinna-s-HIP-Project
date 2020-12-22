package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    //private declarations
    @FXML
    private Button runBtn;
    //end private declarations

    //public methods
    public void onRunClicked(){
        System.out.println("Hello.");
    }
    //end public methods
}
