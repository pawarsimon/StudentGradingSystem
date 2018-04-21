
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Student;

/**
 *
 * @author simon
 */
public class ArithmeticsController implements Initializable{
      @FXML
    private Label avgLabel;

    @FXML
    private Label sumLabel;

    @FXML
    private Label maxLabel;

    @FXML
    private Label minLabel;
    
    
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

            List<Double> numbers = new ArrayList<>();
            for(Student student : students)
            {   
                numbers.add(student.getGradesObtained());
            }
            
            DoubleSummaryStatistics stats = numbers.stream().mapToDouble((x) -> x).summaryStatistics();
            this.avgLabel.setText(Double.toString(stats.getAverage()));
            this.sumLabel.setText(Double.toString(stats.getSum()));
            this.maxLabel.setText(Double.toString(stats.getMax()));
            this.minLabel.setText(Double.toString(stats.getMin()));
            
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
    /**
     * This method takes user to Student List view 
     * @param event
     * @throws IOException 
     */
    public void backButtonPushed(ActionEvent event) throws IOException
    {
       SceneChanger sc = new SceneChanger();
      sc.changeScene(event, "/views/StudentList.fxml", "All Students");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
          try {
              loadStudents();
          } catch (SQLException ex) {
              Logger.getLogger(ArithmeticsController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
