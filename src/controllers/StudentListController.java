
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Student;

/**
 *
 * @author simon
 */
public class StudentListController implements Initializable{
    @FXML
    private TableView<Student> gradesTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> studentNumberColumn;
    @FXML
    private TableColumn<Student, Double> gradesObtainedColumn;
    
    
    
    public void newStudentButtonPushed(ActionEvent event) throws IOException
    {
        
      SceneChanger sc = new SceneChanger();
      sc.changeScene(event, "/views/GradesRegistration.fxml", "Create New Student");
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        studentNumberColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentNumber"));
        gradesObtainedColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("gradesObtained"));

        try {
            loadStudents();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }

    /**
     *
     * @throws SQLException
     */
    public void loadStudents() throws SQLException {
        ObservableList<Student> students = FXCollections.observableArrayList();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es:3306/gc200361569", "gc200361569", "n*-eSANP");
            //2.  create a statement object
            statement = conn.createStatement();

            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM grades");

            //4.  create students objects from each record
            while (resultSet.next()) {
                Student newStudent = new Student(resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("studentNumber"),
                        resultSet.getDouble("grades"));

                students.add(newStudent);                    
            }

            gradesTable.getItems().addAll(students);
            
            
            
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }
}
