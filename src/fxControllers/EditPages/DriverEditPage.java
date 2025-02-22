package fxControllers.EditPages;

import fxControllers.LoginPage;
import fxControllers.MainPage;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Driver;
import model.Manager;
import model.User;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.time.LocalDate;

public class DriverEditPage {
  public TextField loginField;
  public TextField nameField;
  public TextField surnameField;
  public PasswordField pswField;
  public DatePicker bDateField;
  public TextField phoneNumField;
  public DatePicker medCertField;
  public TextField medCertNum;
  public TextField driverLicenseField;


  private Driver driver;
  private EntityManagerFactory entityManagerFactory;

  private HibernateController hibernateController;

  private User loggedUser;


  public void setInfo(User user, EntityManagerFactory entityManagerFactory, User loggedUser){
    this.entityManagerFactory = entityManagerFactory;
    this.hibernateController = new HibernateController(entityManagerFactory);
    this.driver = (Driver) user;
    this.loggedUser = loggedUser;
  }

  public void editDriver(ActionEvent actionEvent) throws Exception {
//    Driver driver = new Driver(loginField.getText(),pswField.getText(),nameField.getText(),surnameField.getText(),
//            LocalDate.parse(bDateField.getValue().toString()),phoneNumField.getText(),
//            LocalDate.parse(medCertField.getValue().toString()),medCertNum.getText(),driverLicenseField.getText());
    driver.setLogin(loginField.getText());
    driver.setPassword(pswField.getText());
    driver.setName(nameField.getText());
    driver.setSurname(surnameField.getText());
    driver.setBirthDate(bDateField.getValue());
    driver.setPhoneNum(phoneNumField.getText());
    driver.setMedCertificateDate(medCertField.getValue());
    driver.setMedCertificateNumber(medCertNum.getText());
    driver.setDriverLicense(driverLicenseField.getText());
    hibernateController.update(driver);

    returnToPrevious3();

  }

  public void fillFields() {
    loginField.setText(driver.getLogin());
    nameField.setText(driver.getName());
    surnameField.setText(driver.getSurname());
    pswField.setText(driver.getPassword());
    phoneNumField.setText(driver.getPhoneNum());
    medCertNum.setText(driver.getMedCertificateNumber());
    driverLicenseField.setText(driver.getDriverLicense());
    bDateField.setValue(driver.getBirthDate());
    medCertField.setValue(driver.getMedCertificateDate());
  }

  public void returnToPrevious3( ) throws Exception {
    User driver = hibernateController.findUserByCredentials(loggedUser.getLogin(), loggedUser.getPassword(), Driver.class);
    User manager = hibernateController.findUserByCredentials(loggedUser.getLogin(), loggedUser.getPassword(), Manager.class);

    if (driver != null || manager != null) {

      FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Main/main-page.fxml"));
      Parent parent = fxmlLoader.load();

      MainPage mainPage= fxmlLoader.getController();

      if (driver == null) mainPage.setInfo(manager, entityManagerFactory);
      else mainPage.setInfo(driver, entityManagerFactory);

      Scene scene = new Scene(parent);
      //Scene scene = new Scene(fxmlLoader.load());
      Stage stage = (Stage) loginField.getScene().getWindow();
      stage.setTitle("FreightSys");
      stage.setScene(scene);
      stage.show();
    } else {
      hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
    }
  }

}
