package fxControllers.EditPages;

import fxControllers.LoginPage;
import fxControllers.MainPage;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cargo;
import model.Driver;
import model.Manager;
import model.User;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.time.LocalDate;

public class CargoEditPage {
    public TextField cargoTitleField;
    public TextField cargoWeightField;
    public TextArea cargoDescriptionField;
    public TextField cargpDestinationField;
    public TextField cargoCustomerField;
    public ComboBox cargoType;

    private Cargo cargo;
    private EntityManagerFactory entityManagerFactory;

    private HibernateController hibernateController;

    private User loggedUser;


    public void setInfo( EntityManagerFactory entityManagerFactory, User loggedUser,Cargo cargo){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.cargo = (Cargo) cargo;
        this.loggedUser = loggedUser;
    }
    public void fillFields() {
        cargoTitleField.setText(cargo.getTitle());
        cargoWeightField.setText(String.valueOf(cargo.getWeight()));
        cargoCustomerField.setText(cargo.getCustomer());
        cargoDescriptionField.setText(cargo.getDescription());
        cargpDestinationField.setText(String.valueOf(cargo.getCurrentDestination()));
    }

    public void saveButton(ActionEvent actionEvent) throws Exception {

            cargo.setTitle(cargoTitleField.getText());
            cargo.setCustomer(cargoCustomerField.getText());
            cargo.setDescription(cargoDescriptionField.getText());
            cargo.setCurrentDestination(null);
            cargo.setDateUpdated(LocalDate.now());


            hibernateController.update(cargo);

            returnToPrevious4();

        }

    public void returnToPrevious4() throws Exception {
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
            Stage stage = (Stage) cargoTitleField.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
        }
    }




}

