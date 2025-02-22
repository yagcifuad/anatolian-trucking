package utils;

import javafx.scene.control.Alert;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;
import model.Driver;
import model.Manager;
import model.User;

import java.sql.*;
import java.time.LocalDate;

public class DbUtils {
  public static Connection connectToDb() {
    Connection conn = null;

    String DB_URL = "localhost:3306/Freight_sys_hibernate3";
    String USER = "root";
    String PASS = "";
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return conn;

  }

  public static void disconnect(Connection connection, Statement statement){
    try {
      if(connection != null && statement != null) {
        statement.close();
        connection.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static User validateUser(String login, String password) throws SQLException {
    Connection connection = connectToDb();
    String sql = "SELECT * FROM drivers WHERE login=? AND password=?";
    // dont forget do it for managers too

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, login);
    preparedStatement.setString(2, password);
    ResultSet rs = preparedStatement.executeQuery();
    Driver driver = null;
    while (rs.next()){
      driver = new Driver(rs.getString("login"),
              rs.getString("password"),
              rs.getString("name")
              , rs.getString("surname"),
              rs.getDate("birthDate").toLocalDate(),
              rs.getString("phoneNum"),
              rs.getBytes("userImage"),
              rs.getDate("medCertificateDate").toLocalDate(),
              rs.getString("medCertificateNumber"),
              rs.getString("driverLicense"));
    }

    disconnect(connection, preparedStatement);
    return driver;
  }


}
