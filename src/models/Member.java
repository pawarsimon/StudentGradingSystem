
package models;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author simon
 */
public class Member {
    
    private String userName, password;
    private byte[] salt;
    private int userId;
    
    public Member(String userName, String password) throws NoSuchAlgorithmException
    {
        setUserName(userName);
        salt = PasswordHasher.getSalt();
        this.password = PasswordHasher.getSHA512Password(password, salt);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void connDb() throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try
        {
            
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es:3306/gc200361569", "gc200361569", "n*-eSANP");

            
            String sql = "INSERT INTO members(userName, password, salt)"+
                    "VALUES(?,?,?)";

            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setBlob(3, new javax.sql.rowset.serial.SerialBlob(salt));

            preparedStatement.executeUpdate();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if(preparedStatement != null)
                preparedStatement.close();
            if(conn != null)
                conn.close();
        }
    
    }
}
