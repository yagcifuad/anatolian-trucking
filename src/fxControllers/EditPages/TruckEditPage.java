package fxControllers.EditPages;

import fxControllers.LoginPage;
import fxControllers.MainPage;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Driver;
import model.Manager;
import model.Truck;
import model.User;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;

public class TruckEditPage {
    public TextField makeField;
    public TextField modelField;
    public TextField yearField;
    public TextField odometerField;
    public TextField fuelTankField;
    public ComboBox tyreTypeComboBox;
    public TextField currentDestinationField;

    private Truck truck;
    private EntityManagerFactory entityManagerFactory;

    private HibernateController hibernateController;

    private User loggedUser;

    public void setInfo(Truck truck, EntityManagerFactory entityManagerFactory, User loggedUser){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.truck = truck;
        this.loggedUser = loggedUser;
    }

    public void save(ActionEvent actionEvent) throws Exception {
        truck.setMake(makeField.getText());
        truck.setModel(modelField.getText());
        truck.setYear(Integer.parseInt(yearField.getText()));
        truck.setOdometer(Double.parseDouble(odometerField.getText()));
        truck.setFuelTankCapacity(Double.parseDouble(fuelTankField.getText()));
        //truck.setCurrentDestination(currentDestinationField.getText());

        hibernateController.update(truck);
        returnToPrevious3();
    }

    public void fillFields() {
        makeField.setText(truck.getMake());
        modelField.setText(truck.getModel());
        yearField.setText(String.valueOf(truck.getYear()));
        odometerField.setText(String.valueOf(truck.getOdometer()));
        fuelTankField.setText(String.valueOf(truck.getFuelTankCapacity()));

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
            Stage stage = (Stage) fuelTankField.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
        }
    }
}
