package fxControllers;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import fxControllers.EditPages.*;
import fxControllers.TableParameters.*;
import hibernateControllers.HibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import javax.persistence.EntityManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class MainPageDriver implements Initializable {

    public Button returnButton2;
    public Button forumButton;


    public TextField cargoDestinationField;
    public ImageView photoView;
    public ListView checkPointListView;
    public TextField checkPointTitleField;
    public RadioButton isLong;
    public DatePicker arrivedDateField;
    public Button checkpointCreateButton3;
    public Button checkpointEditButton;
    public Button checkpointDeleteButton;
    public TableView <DestinationTableParameter>destinationTable1;

    public TextField startCityField;
    public TextField endCityField;
    public TextField endLongitudeField;
    public TextField startLatitudeField;
    public TextField endLatitudeField;
    public TextField startLongitudeField;

    @FXML
    public ComboBox<Manager> managerComboBox;
    @FXML
    public ComboBox<Cargo> cargoComboBox;
    public ComboBox<Driver> driverComboBox;
    public ComboBox<Truck> truckComboBox;

    //public TableColumn<CargoTableParameter,String> colDescriptionCargo;
    public TableColumn <DestinationTableParameter,Integer>colIDDestination;
    public TableColumn <DestinationTableParameter,String>colStartCityDestination;
    public TableColumn <DestinationTableParameter,String>colEndCityDestination;
    public TableColumn <DestinationTableParameter,String>colManagerDestination;
    public TableColumn <DestinationTableParameter,String>colDriverDestination;
    public TableColumn <DestinationTableParameter,String>colTruckDestination;
    public TableColumn <DestinationTableParameter,String>colCargoDestination;
    public TableColumn <DestinationTableParameter,String>colStatusDestination;
    public TableColumn <DestinationTableParameter,String>colArrivalDestination;
    public TableColumn <DestinationTableParameter,String>colDepartureDestination;
    public TextField searchDestinationField;
    public Button searchDestinationButton;
    public ComboBox destinationStatusField;

    public DatePicker departureField;
    public DatePicker arrivalField;
    public ListView taskList;
    public TextArea taskArea;


    private ObservableList<DestinationTableParameter> dataDestination = FXCollections.observableArrayList();





    public TableView<DriverTableParameter> driverTable;

    public TableColumn<DriverTableParameter, Integer> colId;
    public TableColumn<DriverTableParameter, String> colLogin;
    public TableColumn<DriverTableParameter, String> colName;
    public TableColumn<DriverTableParameter, String> colSurname;
    public TableColumn<DriverTableParameter, String> colPhoneNum;


    private ObservableList<DriverTableParameter> data = FXCollections.observableArrayList();
    private User loggedUser;
    private EntityManagerFactory entityManagerFactory;
    private HibernateController hibernateController;

    public void setInfo(User user, EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.loggedUser = user;


        byte[] imageBytes = loggedUser.getUser_image();
        System.out.println(imageBytes);
        if (imageBytes != null) {
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            System.out.println(image);
            photoView.setImage(image);
        }

        setTaskList();
        loadDestinationData();
    }

    private void loadDestinationData() {
        List<Destination> destinationList = hibernateController.getAllRecords(Destination.class);


        Driver driver = hibernateController.getEntityByID(Driver.class,loggedUser.getId());

        for (Destination c: destinationList) {

            if( c.getDriver()==null || c.getDriver().getId()==driver.getId()){
            DestinationTableParameter destinationTableParameter= new DestinationTableParameter();
            destinationTableParameter.setColIDDestination(c.getId());
            destinationTableParameter.setColStartCityDestination(c.getStartCity());
            destinationTableParameter.setColEndCityDestination(c.getEndCity());
            destinationTableParameter.setColManagerDestination(c.getRManager() != null ? c.getRManager().toString() : "");
            destinationTableParameter.setColCargoDestination(c.getCargo() != null ? c.getCargo().getTitle() : "");
            destinationTableParameter.setColDriverDestination(c.getDriver() != null ? c.getDriver().getName() : "");
            destinationTableParameter.setColArrivalDestination(c.getArrivalDate() != null ? c.getArrivalDate().toString() : "");
            destinationTableParameter.setColDepartureDestination(c.getDepartureDate() != null ? c.getDepartureDate().toString() : "");
            destinationTableParameter.setColTruckDestination(c.getTruck() != null ? c.getTruck().getMake() : "");
            destinationTableParameter.setColStatusDestination(c.getStatus()!= null ? c.getStatus() : "");
            destinationTableParameter.setColDriverDestination(c.getDriver()!= null ? c.getDriver().getName() : "");


            dataDestination.add(destinationTableParameter);}
        }
        destinationTable1.setItems(dataDestination);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        destinationStatusField.getItems().add("Waiting");
        destinationStatusField.getItems().add("Active");
        destinationStatusField.getItems().add("Done");



        destinationTable1.setEditable(true);
        colIDDestination.setCellValueFactory(new PropertyValueFactory<>("colIDDestination"));
        colStartCityDestination.setCellValueFactory(new PropertyValueFactory<>("colStartCityDestination"));
        colEndCityDestination.setCellValueFactory(new PropertyValueFactory<>("colEndCityDestination"));
        colManagerDestination.setCellValueFactory(new PropertyValueFactory<>("colManagerDestination"));
        colCargoDestination.setCellValueFactory(new PropertyValueFactory<>("colCargoDestination"));
        colDriverDestination.setCellValueFactory(new PropertyValueFactory<>("colDriverDestination"));
        colTruckDestination.setCellValueFactory(new PropertyValueFactory<>("colTruckDestination"));
        colArrivalDestination.setCellValueFactory(new PropertyValueFactory<>("colArrivalDestination"));
        colDepartureDestination.setCellValueFactory(new PropertyValueFactory<>("colDepartureDestination"));
        colStatusDestination.setCellValueFactory(new PropertyValueFactory<>("colStatusDestination"));

    }

    public boolean isAdmin(User user) {
        if (user instanceof Manager) {
            if (((Manager) user).isAdmin()) {
                return true;
            }
        }
        return false;
    }



    public void returnToPrevious2() throws IOException {
        //User user = new Manager("admin", "admin", "admin", "admin", LocalDate.parse("2000-01-01"), "dafaf", "fuadyagci@gmail.com");

        // Step 1: Get the resource URL of the FXML file
        URL loginPageUrl = MainPage.class.getResource("../view/login-page.fxml");

        // Step 2: Create a new instance of the FXMLLoader class
        FXMLLoader fxmlLoader = new FXMLLoader(loginPageUrl);

        // Step 3: Load the FXML file and create a new Scene object
        Scene scene = new Scene(fxmlLoader.load());

        // Step 4: Set the Scene object as the current scene of the Stage object
        Stage stage = (Stage) returnButton2.getScene().getWindow();
        stage.setScene(scene);

    }

    public void forumButton(ActionEvent actionEvent) throws IOException {
        // Step 1: Get the resource URL of the FXML file
        URL forumPageUrl = getClass().getResource("../view/forum-page.fxml");

        // Step 2: Create a new instance of the FXMLLoader class
        FXMLLoader fxmlLoader = new FXMLLoader(forumPageUrl);

        // Step 3: Load the FXML file and create a new Scene object
        Scene scene = new Scene(fxmlLoader.load());

        // Step 4: Create a new Stage object
        Stage newStage = new Stage();

        // Step 5: Set the Scene object as the current scene of the new Stage object
        newStage.setScene(scene);
        // Step 6: Show the new stage
        newStage.show();
        ForumPage forumPage= fxmlLoader.getController();
        forumPage.setInfo(loggedUser, entityManagerFactory);

    }



public void setTaskList() throws RuntimeException{
        String startLocation=null;
        String endLocation = null;

    List<Destination> destinationList = hibernateController.getAllRecords(Destination.class);


    Driver driver = hibernateController.getEntityByID(Driver.class,loggedUser.getId());

    for (Destination c: destinationList) {

        if( c.getDriver()==null || c.getDriver().getId()==driver.getId()) {
            startLocation= c.getStartLocation();
            endLocation=c.getEndLocation();
        }
    }

    if (!startLocation.equals(null)||!endLocation.equals(null)){ calculateRoute(startLocation,endLocation);}


}

    private void calculateRoute(String startField, String endField) {
        System.out.println("HEEEEEY---------------------------------------");
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("add-your-google-api-here")
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, startField, endField);
        req.mode(TravelMode.DRIVING);
        try {
            DirectionsResult res = req.await();
            if (res.routes.length > 0) {
                String directions = "";
                for (int i = 0; i < res.routes[0].legs.length; i++) {
                    directions += res.routes[0].legs[i].startAddress + " to ";
                    directions += res.routes[0].legs[i].endAddress + "\n";
                    directions += res.routes[0].legs[i].distance + "\n";
                    directions += res.routes[0].legs[i].duration + "\n";
                    directions += "----------------------------------\n";
                    directions += "You have x2 of estimated time.\n";
                    directions += "----------------------------------\n";
                    directions += "OBEY SAFETY RULES!";

                }
                taskArea.setText(directions);
            } else {
                taskArea.setText("No routes found");
            }
        } catch (InterruptedException | IOException e) {
            taskArea.setText("Error: " + e.getMessage());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void profileButton(MouseEvent mouseEvent) throws Exception {

    }





    public void destinationUpdateButton(ActionEvent actionEvent) throws IOException {

    }

    public void destinationSelectClick(MouseEvent mouseEvent) {
        listCheckpointField();
    }

    public void checkpointCreateButton3(ActionEvent actionEvent) throws RuntimeException {

        DestinationTableParameter selectedDestination = destinationTable1.getSelectionModel().getSelectedItem();

        if (selectedDestination == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }
        Destination destination = hibernateController.getEntityByID(Destination.class, selectedDestination.getColIDDestination());
        Checkpoint newCheckpoint = new Checkpoint(checkPointTitleField.getText(), isLong.isSelected(),arrivedDateField.getValue(),destination);

        hibernateController.create(newCheckpoint);

        destination.getCheckpoints().add(newCheckpoint);


        checkPointTitleField.setText(null);



        ObservableList<Checkpoint> checkpoints = FXCollections.observableArrayList(destination.getCheckpoints());
        Collections.sort(checkpoints, Comparator.comparingInt(Checkpoint::getId));
        checkPointListView.setItems(checkpoints);
    }




    public void listCheckpointField(){
        DestinationTableParameter selectedForum = destinationTable1.getSelectionModel().getSelectedItem();
        if (selectedForum == null) {
            return;
        }
        Destination destination = hibernateController.getEntityByID(Destination.class, selectedForum.getColIDDestination());
        ObservableList<Checkpoint> checkpoints = FXCollections.observableArrayList(destination.getCheckpoints());
        Collections.sort(checkpoints, Comparator.comparingInt(Checkpoint::getId));

        checkPointListView.setItems(checkpoints);


    }

    public void clearDestinationButton(ActionEvent actionEvent) {
    }

    public void searchDestinationButton(ActionEvent actionEvent) {
    }

    public void destinationTakeButton(ActionEvent actionEvent) {

        DestinationTableParameter selectDestination = destinationTable1.getSelectionModel().getSelectedItem();
        Destination destination = hibernateController.getEntityByID(Destination.class, selectDestination.getColIDDestination());

        Driver driver = hibernateController.getEntityByID(Driver.class,loggedUser.getId());
            destination.setDriver(driver);
            hibernateController.update(destination);


    }

}


