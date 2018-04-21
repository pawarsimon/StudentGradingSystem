
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author simon
 */
public class Student {
    
    private String firstName;
    private String lastName;
    private String studentNumber;
    private double gradesObtained;

    public Student(String firstName, String lastName, String studentNumber, double gradesObtained)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setStudentNumber(studentNumber);
        setGradesObtained(gradesObtained);
    }

    public Student() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public double getGradesObtained() {
        return gradesObtained;
    }

    public void setGradesObtained(double gradesObtained) {
        this.gradesObtained = gradesObtained;
    }
    
    public void connDB() throws SQLException
    {
                
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        
        try
        {
           conn = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es:3306/gc200361569", "gc200361569", "n*-eSANP");
           
           String sql = "INSERT INTO grades (firstName, lastName, studentNumber, grades)"
                        + "VALUES(?,?,?,?)";
                   
           preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
           
           
           preparedStatement.setString(1, firstName);
           preparedStatement.setString(2, lastName);
           preparedStatement.setString(3, studentNumber);
           preparedStatement.setDouble(4, gradesObtained);

           preparedStatement.executeUpdate();
           
           
        }
        
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.out.println("Gotcha!");
        }
        
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }
    
    }
}
