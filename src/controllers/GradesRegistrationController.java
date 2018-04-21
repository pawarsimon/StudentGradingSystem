
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import models.Student;

/**
 *
 * @author simon
 */
public class GradesRegistrationController implements Initializable{
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField studentNumberTextField;

    @FXML
    private TextField gradesObtainedTextField;
    
    /**
     * This method takes user to student list view
     * @param event
     * @throws IOException 
     */
    public void cancelButtonClicked(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScene(event, "/views/StudentList.fxml","All students");
    }
    
    /**
     * This method creates a 
     * @param event 
     */
     public void createStudentButtonClicked(ActionEvent event)
    {
        try
        {
            Student student = new Student(firstNameTextField.getText(), lastNameTextField.getText(),
                                                studentNumberTextField.getText(), Double.parseDouble(gradesObtainedTextField.getText()));
            
            student.connDB();
            
            SceneChanger sc = new SceneChanger();
            sc.changeScene(event, "/views/StudentList.fxml", "All Students");
            
            System.out.println(event.toString());
        }
        
        
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }
     
     public void arithimeticsButtonPushed(ActionEvent event) throws IOException
     {
         SceneChanger sc  = new SceneChanger();
        sc.changeScene(event,"/views/Arithmetics.fxml","Logout");
     }
     
     public void logoutButtonPushed(ActionEvent event) throws IOException
     {
         SceneChanger sc  = new SceneChanger();
        sc.changeScene(event,"/views/Dashboard.fxml","Logout");
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
}
