
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author simon
 */
public class DashboardController implements Initializable{
    
    @FXML 
    private Button logInButton;
    @FXML 
    private Button SignUpButton;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * This lambda expression takes user to login view
         */
        logInButton.setOnAction((event) -> {
        SceneChanger sc  = new SceneChanger();
            try {
                sc.changeScene(event,"/views/Login.fxml","Login");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /**
         * This lambda expression takes user to sign up view
         */
         SignUpButton.setOnAction((event) -> {
        SceneChanger sc  = new SceneChanger();
            try {
                sc.changeScene(event,"/views/Signup.fxml","Login");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
