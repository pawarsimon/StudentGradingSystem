/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Member;



/**
 *
 * @author simon
 */
public class SignUpController implements Initializable{
       @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML private Label errMsgLabel;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       errMsgLabel.setText("");
    }
    
     public void signUpButtonPushed(ActionEvent event){
        
         
        if(validPasswords()){
            try{
                
               
            Member member = new Member(userNameTextField.getText(), passwordField.getText());

            errMsgLabel.setText("");
            member.connDb();

            SceneChanger sc  = new SceneChanger();
            sc.changeScene(event,"/views/Login.fxml","Login");
            }
            catch(Exception e)
            {
                errMsgLabel.setText(e.getMessage());
            }
        }
    }
     
        public void homeButtonPushed(ActionEvent event) throws IOException{
        SceneChanger sc  = new SceneChanger();
        sc.changeScene(event,"/views/Dashboard.fxml","Home"); 
     }
        public boolean validPasswords(){
        if (passwordField.getText().length() < 8){
            errMsgLabel.setText("Password should be greater than 8 characters");
            return false;
        }
        if (passwordField.getText().equals(confirmPasswordField.getText()))
            return true;
        else
            errMsgLabel.setText("Please match your passwords");
            return false;
    }
}
