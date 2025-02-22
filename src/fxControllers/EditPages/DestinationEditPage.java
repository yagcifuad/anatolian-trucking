package fxControllers.EditPages;

import fxControllers.LoginPage;
import fxControllers.MainPage;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import javax.persistence.EntityManagerFactory;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DestinationEditPage implements Initializable {
    public TextField startCityField;
    public TextField endCityField;
    public TextField startLnField;
    public TextField endLnField;
    public TextField startLtField;
    public TextField endLtField;
    public ComboBox managerListComboBox;
    public ComboBox statusComboBox;
    private Destination destination;
    private EntityManagerFactory entityManagerFactory;

    private HibernateController hibernateController;

    private User loggedUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws NullPointerException {

        statusComboBox.getItems().add("Waiting");
        statusComboBox.getItems().add("Active");
        statusComboBox.getItems().add("On Checkpoint");
        statusComboBox.getItems().add("Done");
    }

    public void save(ActionEvent actionEvent) throws Exception {
        destination.setStartCity(startCityField.getText());
        destination.setEndCity(endCityField.getText());
        destination.setStartLocation(startLtField.getText());
        destination.setEndLocation(endLtField.getText());
        destination.setDateUpdated(LocalDate.now());
        destination.setStatus(statusComboBox.getValue().toString());


        hibernateController.update(destination);
        returnToPrevious3();
    }


    public void setInfo(Destination destination, EntityManagerFactory entityManagerFactory, User loggedUser){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.destination=destination;
        this.loggedUser = loggedUser;
    }



    public void fillFields() {

        startCityField.setText(destination.getStartCity());
        endCityField.setText(destination.getEndCity());
        startLtField.setText(destination.getStartLocation());
        endLtField.setText(destination.getEndLocation());

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
            Stage stage = (Stage) startCityField.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
        }
    }
}
