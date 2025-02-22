package fxControllers.EditPages;

import com.google.protobuf.StringValue;
import fxControllers.LoginPage;
import fxControllers.MainPage;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ManagerEditPage {
  public TextField loginField;
  public TextField nameField;
  public TextField surnameField;
  public PasswordField pswField;
  public DatePicker bDateField;
  public TextField managerEmailField;
  public TextField phoneNumField;
  public Button managerEditButton;

  private Manager manager;
  private EntityManagerFactory entityManagerFactory;

  private HibernateController hibernateController;

  private User loggedUser;


  public void setInfo(User user, EntityManagerFactory entityManagerFactory, User loggedUser){
    this.entityManagerFactory = entityManagerFactory;
    this.hibernateController = new HibernateController(entityManagerFactory);
    this.manager = (Manager) user;
    this.loggedUser = loggedUser;
  }


  public void editManager(ActionEvent actionEvent) throws Exception {
    manager.setLogin(loginField.getText());
    manager.setPassword(pswField.getText());
    manager.setName(nameField.getText());
    manager.setSurname(surnameField.getText());
    manager.setBirthDate(bDateField.getValue());
    manager.setPhoneNum(phoneNumField.getText());
    manager.setEmail(managerEmailField.getText());

    hibernateController.update(manager);
    returnToPrevious3();
  }

  public void fillFields() {
    loginField.setText(manager.getLogin());
    nameField.setText(manager.getName());
    surnameField.setText(manager.getSurname());
    pswField.setText(manager.getPassword());
    phoneNumField.setText(manager.getPhoneNum());
    bDateField.setValue(LocalDate.parse(String.valueOf(manager.getBirthDate())));
    managerEmailField.setText(manager.getEmail());
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
