package fxControllers;

import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Driver;
import model.Manager;
import model.User;

import javax.persistence.EntityManagerFactory;
import java.io.ByteArrayInputStream;

public class ProfilePage {
    public TextField loginField;
    public TextField nameField;
    public PasswordField pswField;
    public TextField managerEmailField;
    public RadioButton radioD;
    public ToggleGroup userType;
    public RadioButton radioM;
    public CheckBox isAdminChk;
    public TextField driverLicenseField;
    public Label photoField;
    public ImageView photoView;
    private EntityManagerFactory entityManagerFactory;
    private HibernateController hibernateController;
    private User loggedUser;



    public void setInfo(User user, EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.loggedUser = user;
        loadDriverData();
        loadManagerData();

        nameField.setText(loggedUser.getName());
        loginField.setText(loggedUser.getLogin());
        pswField.setText(loggedUser.getPassword());


        byte[] imageBytes = loggedUser.getUser_image();
        System.out.println(imageBytes);
        if (imageBytes != null) {
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            System.out.println(image);
            photoView.setImage(image);
        }
    }

    private void loadManagerData() {
    }

    private void loadDriverData() {
    }

    public void disableFields(ActionEvent actionEvent) {
    }

    public void returnToPrevious(ActionEvent actionEvent) throws Exception {
        User driver = hibernateController.findUserByCredentials(loginField.getText(), pswField.getText(), Driver.class);
        User manager = hibernateController.findUserByCredentials(loginField.getText(), pswField.getText(), Manager.class);

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

    public void photoChooseButton(ActionEvent actionEvent) {
    }
}
