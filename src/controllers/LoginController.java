
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.PasswordHasher;


/**
 *
 * @author simon
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errMsgLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errMsgLabel.setText("");
    }  
    
     public void loginButtonPushed(ActionEvent event) throws IOException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        String userNum = userNameTextField.getText();
        
        try{
        
            conn = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es:3306/gc200361569", "gc200361569", "n*-eSANP");
            
            String sql = "SELECT password, salt FROM members WHERE userName = ?";
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, userNum);
            
            resultSet = ps.executeQuery();
            
            String dbPassword = null;
            byte[] salt = null;
            while(resultSet.next()){
                dbPassword = resultSet.getString("password");
                
                Blob blob = resultSet.getBlob("salt");
                
                int bloblength = (int) blob.length();
                salt = blob.getBytes(1, bloblength);
            }
            
            String userPW = PasswordHasher.getSHA512Password(passwordField.getText(), salt);
            
            SceneChanger sc = new SceneChanger();
            if(userPW.equals(dbPassword)){
                sc.changeScene(event, "/views/StudentList.fxml", "All Students");
            }
            else
                errMsgLabel.setText("User Name or Password is incorrect");
        
        } 
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
     
     public void homeButtonPushed(ActionEvent event) throws IOException{
        SceneChanger sc  = new SceneChanger();
        sc.changeScene(event,"/views/Dashboard.fxml","Home"); 
     }
    
}
