package fxControllers;

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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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


public class MainPageManager implements Initializable {
    @FXML
    public TextField makeField;
    @FXML
    public TextField modelField;
    @FXML
    public TextField yearField;
    @FXML
    public TextField odometerField;
    @FXML
    public TextField tankCapacityField;
    @FXML
    public ComboBox<TyreType> tyreTypeField;



    public TableView<ManagerTableParameter> managerTable;

    public TableColumn<ManagerTableParameter, Integer> colIdManager;
    public TableColumn<ManagerTableParameter, String> colLoginManager;
    public TableColumn<ManagerTableParameter, String> colNameManager;
    public TableColumn<ManagerTableParameter, String> colSurnameManager;
    public TableColumn<ManagerTableParameter, String> colPhoneNumManager;
    public Button driverDeleteButton;
    public Button managerDeleteButton;
    public Button returnButton2;
    public Button forumButton;
    public TableView <TruckTableParameter>truckTable;
    public TableColumn <TruckTableParameter,Integer>colIDTruck;
    public TableColumn <TruckTableParameter,String>colMakeTruck;
    public TableColumn <TruckTableParameter,String>colModelTruck;
    public TableColumn <TruckTableParameter,Integer>colModelYear;
    public TableColumn <TruckTableParameter,Integer>colOdometerTruck;
    public TableColumn <TruckTableParameter,Double>colTankTruck;
    public TableColumn <TruckTableParameter,TyreType>colTyreTruck;
    public TextField cargoTitleField;
    public TextField cargoWeightField;
    public TextArea cargoDescriptionField;
    public TextField cargoCustomerField;
    public ComboBox <CargoType> cargoTypeField;
    public TableView<CargoTableParameter> cargoTable;
    //<TruckTableParameter,Integer>
    // public TableView <TruckTableParameter>truckTable;
    public TableColumn<CargoTableParameter,Integer> colIDCargo;
    public TableColumn <CargoTableParameter,String>colTitleCargo;
    public TableColumn<CargoTableParameter, LocalDate> colCreatedCargo;
    public TableColumn<CargoTableParameter, LocalDate> colUpdatedCargo;
    public TableColumn <CargoTableParameter,Double>colWeightCargo;
    public TableColumn <CargoTableParameter,CargoType>colTypeCargo;
    public TableColumn<CargoTableParameter,String> colCustomerCargo;
    public TableColumn<CargoTableParameter,String> colDescriptionCargo;
    public TableColumn<CargoTableParameter,Destination> colDestinationCargo;
    public TextField cargoDestinationField;
    public ImageView photoView;
    public ListView checkPointListView;
    public TextField checkPointTitleField;
    public RadioButton isLong;
    public DatePicker arrivedDateField;
    public Button checkpointCreateButton;
    public Button checkpointEditButton;
    public Button checkpointDeleteButton;
    public TableView <DestinationTableParameter>destinationTable;
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

    private ObservableList<Manager> managerList = FXCollections.observableArrayList();
    private ObservableList<Driver> driverList = FXCollections.observableArrayList();
    private ObservableList<Truck> truckList = FXCollections.observableArrayList();
    private ObservableList<Cargo> cargoList = FXCollections.observableArrayList();

    //public ComboBox managerComboBox;

    private ObservableList<CargoTableParameter> dataCargo = FXCollections.observableArrayList();


    private ObservableList<TruckTableParameter> dataTruck = FXCollections.observableArrayList();


    private ObservableList<ManagerTableParameter> dataManager = FXCollections.observableArrayList();

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
        setComboBoxes(hibernateController);
//        List<Manager> managers = hibernateController.getAllRecords(Manager.class);
//        managerList.addAll(managers);
//        managerComboBox.setItems(managerList);
//
//        List<Driver> drivers = hibernateController.getAllRecords(Driver.class);
//        driverList.addAll(drivers);
//        driverComboBox.setItems(driverList);
//
//        List<Cargo> cargos = hibernateController.getAllRecords(Cargo.class);
//        cargoList.addAll(cargos);
//        cargoComboBox.setItems(cargoList);
//
//        List<Truck> trucks = hibernateController.getAllRecords(Truck.class);
//        truckList.addAll(trucks);
//        truckComboBox.setItems(truckList);

        byte[] imageBytes = loggedUser.getUser_image();
        System.out.println(imageBytes);
        if (imageBytes != null) {
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            System.out.println(image);
            photoView.setImage(image);
        }
        loadDriverData();
        loadManagerData();
        loadTruckData();
        loadCargoData();
        loadDestinationData();
    }

    public void setComboBoxes(HibernateController hibernateController){
        this.hibernateController = hibernateController;

        List<Manager> managers = hibernateController.getAllRecords(Manager.class);
        managerList.clear();
        managerList.addAll(managers);
        managerComboBox.setItems(managerList);

        List<Driver> drivers = hibernateController.getAllRecords(Driver.class);
        driverList.clear();
        driverList.addAll(drivers);
        driverComboBox.setItems(driverList);

        List<Cargo> cargos = hibernateController.getAllRecords(Cargo.class);
        cargoList.clear();
        cargoList.addAll(cargos);
        cargoComboBox.setItems(cargoList);

        List<Truck> trucks = hibernateController.getAllRecords(Truck.class);
        truckList.clear();
        truckList.addAll(trucks);
        truckComboBox.setItems(truckList);

    }

    private void loadCargoData(){
        List<Cargo> cargoList = hibernateController.getAllRecords(Cargo.class);
        for (Cargo c: cargoList){
            CargoTableParameter cargoTableParameter= new CargoTableParameter();
            cargoTableParameter.setColIDCargo(c.getId());
            cargoTableParameter.setColTitleCargo(c.getTitle());
            cargoTableParameter.setColTypeCargo(c.getCargoType().toString());
            cargoTableParameter.setColWeightCargo(c.getWeight());
            cargoTableParameter.setColCustomerCargo(c.getCustomer());
            cargoTableParameter.setColCreatedCargo(c.getDateCreated().toString());
            cargoTableParameter.setColDescriptionCargo(c.getDescription());
            cargoTableParameter.setColUpdatedCargo(c.getDateUpdated().toString());
            //cargoTableParameter.setColDestinationCargo(c.getDestination().toString());

            dataCargo.add(cargoTableParameter);
        }
        cargoTable.setItems(dataCargo);
    }
    private void loadDestinationData() {
        List<Destination> destinationList = hibernateController.getAllRecords(Destination.class);
        for (Destination c: destinationList) {
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


            dataDestination.add(destinationTableParameter);
        }
        destinationTable.setItems(dataDestination);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tyreTypeField.getItems().add(TyreType.TYPE_1);
        tyreTypeField.getItems().add(TyreType.TYPE_2);

        destinationStatusField.getItems().add("Waiting");
        destinationStatusField.getItems().add("Active");
        destinationStatusField.getItems().add("Done");



        // managerComboBox.getItems().add()



        cargoTypeField.getItems().add(CargoType.ALCOHOL);
        cargoTypeField.getItems().add(CargoType.MIX);
        cargoTypeField.getItems().add(CargoType.FOOD);
        cargoTypeField.getItems().add(CargoType.PAPER);
        cargoTypeField.getItems().add(CargoType.ELECTRONIC);
        cargoTypeField.getItems().add(CargoType.CONSUMER_ELECTRONICS);
        //cargoTypeField.getItems().add(CargoType.valueOf(cargoTitleField.getId()));


        driverTable.setEditable(true);
        colId.setCellValueFactory(new PropertyValueFactory<>("colId"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("colLogin"));
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("colSurname"));
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("colPhoneNum"));

        managerTable.setEditable(true);
        colIdManager.setCellValueFactory(new PropertyValueFactory<>("colIdManager"));
        colLoginManager.setCellValueFactory(new PropertyValueFactory<>("colLoginManager"));
        colNameManager.setCellValueFactory(new PropertyValueFactory<>("colNameManager"));
        colSurnameManager.setCellValueFactory(new PropertyValueFactory<>("colSurnameManager"));
        colPhoneNumManager.setCellValueFactory(new PropertyValueFactory<>("colPhoneNumManager"));

        truckTable.setEditable(true);
        colIDTruck.setCellValueFactory(new PropertyValueFactory<>("colIDTruck"));
        colMakeTruck.setCellValueFactory(new PropertyValueFactory<>("colMakeTruck"));
        colModelTruck.setCellValueFactory(new PropertyValueFactory<>("colModelTruck"));
        colModelYear.setCellValueFactory(new PropertyValueFactory<>("colModelYear"));
        colOdometerTruck.setCellValueFactory(new PropertyValueFactory<>("colOdometerTruck"));
        colTankTruck.setCellValueFactory(new PropertyValueFactory<>("colTankTruck"));
        colTyreTruck.setCellValueFactory(new PropertyValueFactory<>("colTyreTruck"));

        cargoTable.setEditable(true);
        colIDCargo.setCellValueFactory(new PropertyValueFactory<>("colIDCargo"));
        colTitleCargo.setCellValueFactory(new PropertyValueFactory<>("colTitleCargo"));
        colTypeCargo.setCellValueFactory(new PropertyValueFactory<>("colTypeCargo"));
        colWeightCargo.setCellValueFactory(new PropertyValueFactory<>("colWeightCargo"));
        colCustomerCargo.setCellValueFactory(new PropertyValueFactory<>("colCustomerCargo"));
        colCreatedCargo.setCellValueFactory(new PropertyValueFactory<>("colCreatedCargo"));
        colUpdatedCargo.setCellValueFactory(new PropertyValueFactory<>("colUpdatedCargo"));
        //colDestinationCargo.setCellValueFactory(new PropertyValueFactory<>("colDestinationCargo"));
        colDescriptionCargo.setCellValueFactory(new PropertyValueFactory<>("colDescriptionCargo"));

        destinationTable.setEditable(true);
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

    private void loadDriverData(){
        List<Driver> driverList = hibernateController.getAllRecords(Driver.class);
        for (Driver d: driverList){
            DriverTableParameter driverTableParameters = new DriverTableParameter();
            driverTableParameters.setColId(d.getId());
            driverTableParameters.setColLogin(d.getLogin());
            driverTableParameters.setColName(d.getName());
            driverTableParameters.setColSurname(d.getSurname());
            driverTableParameters.setColPhoneNum(d.getPhoneNum());

            data.add(driverTableParameters);
        }

        driverTable.setItems(data);
    }

    private void loadManagerData(){
        List<Manager> managerList = hibernateController.getAllRecords(Manager.class);
        for (Manager m: managerList){
            ManagerTableParameter managerTableParameters = new ManagerTableParameter();
            managerTableParameters.setColIdManager(m.getId());
            managerTableParameters.setColLoginManager(m.getLogin());
            managerTableParameters.setColNameManager(m.getName());
            managerTableParameters.setColSurnameManager(m.getSurname());
            managerTableParameters.setColPhoneNumManager(m.getPhoneNum());

            dataManager.add(managerTableParameters);
        }

        managerTable.setItems(dataManager);
    }

    private void loadTruckData(){
        List<Truck> truckList = hibernateController.getAllRecords(Truck.class);
        for (Truck t: truckList){
            TruckTableParameter truckTableParameter = new TruckTableParameter();
            truckTableParameter.setColIDTruck(t.getId());
            truckTableParameter.setColMakeTruck(t.getMake());
            truckTableParameter.setColModelTruck(t.getModel());
            truckTableParameter.setColOdometerTruck(t.getOdometer());
            truckTableParameter.setColModelYear(t.getYear());
            truckTableParameter.setColTankTruck(t.getFuelTankCapacity());
            truckTableParameter.setColTyreTruck((t.getTyreType()).toString());
            dataTruck.add(truckTableParameter);
        }
        truckTable.setItems(dataTruck);
    }

    public void deleteDriver(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;

        DriverTableParameter selectedDriver = driverTable.getSelectionModel().getSelectedItem();
        Driver driver = hibernateController.getEntityByID(Driver.class, selectedDriver.getColId());
        hibernateController.delete(driver);

        driverTable.getItems().remove(selectedDriver);
        setComboBoxes(hibernateController);

    }

    public void deleteManager(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;


        ManagerTableParameter selectedManager = managerTable.getSelectionModel().getSelectedItem();
        Manager manager = hibernateController.getEntityByID(Manager.class, selectedManager.getColIdManager());
        if (manager.isAdmin()){
            hibernateController.alertDialog("Admin can't delete admin", "Bruh");
            return;
        }

        hibernateController.delete(manager);
        managerTable.getItems().remove(selectedManager);
        setComboBoxes(hibernateController);

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
    public void createTruck(ActionEvent actionEvent) {
        Truck truck = new Truck(makeField.getText(), modelField.getText(),Integer.parseInt(yearField.getText()),Double.parseDouble(odometerField.getText()),Double.parseDouble(tankCapacityField.getText()), tyreTypeField.getSelectionModel().getSelectedItem());
        hibernateController.create(truck);
        truckTable.getItems().clear();
        loadTruckData();

        makeField.setText(null);
        modelField.setText(null);
        yearField.setText(null);
        odometerField.setText(null);
        tyreTypeField.setItems(null);
        tankCapacityField.setText(null);
        setComboBoxes(hibernateController);

    }
    public void editTruck(ActionEvent actionEvent) throws IOException {
        if (!(isAdmin(loggedUser))) return;

        TruckTableParameter selectedTruck = truckTable.getSelectionModel().getSelectedItem();
        Truck truck = hibernateController.getEntityByID(Truck.class, selectedTruck.getColIDTruck());

        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Edit/TruckEditPage.fxml"));
        Parent parent = fxmlLoader.load();
        TruckEditPage truckEditPage= fxmlLoader.getController();
        truckEditPage.setInfo(truck,entityManagerFactory, loggedUser);
        truckEditPage.fillFields();

        Scene scene = new Scene(parent);
        Stage stage = (Stage) truckTable.getScene().getWindow();
        stage.setTitle("Edit Cargo");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteTruck(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;

        TruckTableParameter selectedTruck = truckTable.getSelectionModel().getSelectedItem();
        Truck truck = hibernateController.getEntityByID(Truck.class, selectedTruck.getColIDTruck());
        hibernateController.delete(truck);
        truckTable.getItems().remove(selectedTruck);
        setComboBoxes(hibernateController);

    }

    public void createCargo(ActionEvent actionEvent) {
        // public Cargo(String title, LocalDate dateCreated, LocalDate dateUpdated, double weight, CargoType cargoType, String description, String customer, Destination destination) {
        Cargo cargo = new Cargo(cargoTitleField.getText(),LocalDate.now() ,LocalDate.now(),Double.parseDouble(cargoWeightField.getText()),cargoTypeField.getSelectionModel().getSelectedItem(),cargoCustomerField.getText(),cargoDescriptionField.getText(),null);
        hibernateController.create(cargo);
        cargoTable.getItems().clear();
        loadCargoData();

        cargoTitleField.setText(null);
        cargoWeightField.setText(null);
        cargoCustomerField.setText(null);
        cargoTypeField.setItems(null);
        cargoDescriptionField.setText(null);
        cargoDestinationField.setText(null);
        setComboBoxes(hibernateController);
    }

    public void editCargo(ActionEvent actionEvent) throws IOException {
        if (!(isAdmin(loggedUser))) return;

        CargoTableParameter selectCargo = cargoTable.getSelectionModel().getSelectedItem();
        Cargo cargo = hibernateController.getEntityByID(Cargo.class, selectCargo.getColIDCargo());

        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Edit/CargoEditPage.fxml"));
        Parent parent = fxmlLoader.load();
        CargoEditPage cargoEditPage= fxmlLoader.getController();
        cargoEditPage.setInfo(entityManagerFactory, loggedUser,cargo);
        cargoEditPage.fillFields();

        Scene scene = new Scene(parent);
        Stage stage = (Stage) driverDeleteButton.getScene().getWindow();
        stage.setTitle("Edit Cargo");
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCargo(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;

        CargoTableParameter selectedCargo = cargoTable.getSelectionModel().getSelectedItem();
        Cargo cargo = hibernateController.getEntityByID(Cargo.class, selectedCargo.getColIDCargo());
        hibernateController.delete(cargo);
        //cargoTable.getItems().remove(cargo);
        cargoTable.getItems().clear();
        loadCargoData();
        setComboBoxes(hibernateController);
    }

    public void profileButton(MouseEvent mouseEvent) throws Exception {

        User driver = hibernateController.findUserByCredentials(loggedUser.getLogin(), loggedUser.getPassword(), Driver.class);
        User manager = hibernateController.findUserByCredentials(loggedUser.getLogin(), loggedUser.getPassword(), Manager.class);

        if (driver != null || manager != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/profile-page.fxml"));
            Parent parent = fxmlLoader.load();

            ProfilePage profilePage= fxmlLoader.getController();

            if (driver == null) profilePage.setInfo(manager, entityManagerFactory);
            else profilePage.setInfo(driver, entityManagerFactory);

            Scene scene = new Scene(parent);
            Stage stage = (Stage) photoView.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
        }

    }

    public void destinationCreateButton(ActionEvent actionEvent) {

        // destinationTableParameter.setColManagerDestination(c.getRManager() != null ? c.getRManager().toString() : "");

        Manager manager = hibernateController.getEntityByID(Manager.class, loggedUser.getId());
        if(destinationStatusField.getValue()==null){ hibernateController.alertDialog("Destination status must be selected!", "Error"); return;}

        Destination destination= new Destination(startCityField.getText(),
                startLatitudeField.getText(),
                endLatitudeField.getText(),
                endCityField.getText(),
                manager,
                driverComboBox.getValue(),
                cargoComboBox.getValue()!= null ? cargoComboBox.getValue() : null,
                truckComboBox.getValue() !=null ? truckComboBox.getValue() : null,
                departureField.getValue(),
                arrivalField.getValue(),
                destinationStatusField.getValue().toString());

        hibernateController.create(destination);

        if(cargoComboBox.getValue()!=null){
            Cargo cargo = hibernateController.getEntityByID(Cargo.class, cargoComboBox.getValue().getId());
            cargo.setCurrentDestination(destination);
            hibernateController.update(cargo);
            cargoComboBox.getValue().setCurrentDestination(destination);}

        if(truckComboBox.getValue()!=null){
            Truck truck = hibernateController.getEntityByID(Truck.class, truckComboBox.getValue().getId());
            truck.setCurrentDestination(destination);
            hibernateController.update(truck);
            truckComboBox.getValue().setCurrentDestination(destination);}


        destinationTable.getItems().clear();
        loadDestinationData();

        startCityField.setText(null);
        endCityField.setText(null);
        startLatitudeField.setText(null);
        startLongitudeField.setText(null);
        endLatitudeField.setText(null);
        endLongitudeField.setText(null);
        arrivedDateField.setChronology(null);
        destinationStatusField.getSelectionModel().clearSelection();
        managerComboBox.getSelectionModel().clearSelection();
        driverComboBox.getSelectionModel().clearSelection();
        cargoComboBox.getSelectionModel().clearSelection();
        truckComboBox.getSelectionModel().clearSelection();
        setComboBoxes(hibernateController);

    }

    public void destinationDeleteButton(ActionEvent actionEvent) {
        DestinationTableParameter selectedDestination = destinationTable.getSelectionModel().getSelectedItem();
        Destination destination = hibernateController.getEntityByID(model.Destination.class, selectedDestination.getColIDDestination());

        if(destination.getCargo()!=null){
            Cargo cargo = hibernateController.getEntityByID(Cargo.class, destination.getCargo().getId());
            cargo.setCurrentDestination(null);
            hibernateController.update(cargo);}

        if(destination.getTruck()!=null){
            Truck truck = hibernateController.getEntityByID(Truck.class, destination.getTruck().getId());
            truck.setCurrentDestination(null);
            hibernateController.update(truck);}

        hibernateController.delete(destination);
        destinationTable.getItems().remove(selectedDestination);
        setComboBoxes(hibernateController);
    }

    public void destinationUpdateButton(ActionEvent actionEvent) throws IOException {
        if (!(isAdmin(loggedUser))) return;

        DestinationTableParameter selectDestination = destinationTable.getSelectionModel().getSelectedItem();
        Destination destination = hibernateController.getEntityByID(Destination.class, selectDestination.getColIDDestination());

        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Edit/DestinationEditPage.fxml"));
        Parent parent = fxmlLoader.load();
        DestinationEditPage destinationEditPage= fxmlLoader.getController();
        destinationEditPage.setInfo(destination,entityManagerFactory, loggedUser);
        destinationEditPage.fillFields();

        Scene scene = new Scene(parent);
        Stage stage = (Stage) destinationTable.getScene().getWindow();
        stage.setTitle("Edit Cargo");
        stage.setScene(scene);
        stage.show();
    }

    public void destinationSelectClick(MouseEvent mouseEvent) {
        listCheckpointField();
    }

    public void checkpointCreateButton(ActionEvent actionEvent) {

        DestinationTableParameter selectedForum = destinationTable.getSelectionModel().getSelectedItem();
        if (selectedForum == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }

        Destination destination = hibernateController.getEntityByID(Destination.class, selectedForum.getColIDDestination());
        Checkpoint newCheckpoint = new Checkpoint(checkPointTitleField.getText(), isLong.isSelected(),arrivedDateField.getValue(),destination);

        hibernateController.create(newCheckpoint);


        destination.getCheckpoints().add(newCheckpoint);


        checkPointTitleField.setText(null);



        ObservableList<Checkpoint> checkpoints = FXCollections.observableArrayList(destination.getCheckpoints());
        Collections.sort(checkpoints, Comparator.comparingInt(Checkpoint::getId));
        checkPointListView.setItems(checkpoints);
    }

    public void checkpointEditButton(ActionEvent actionEvent) {
    }

    public void checkpointDeleteButton1(ActionEvent actionEvent) {
        DestinationTableParameter selectedDestination = destinationTable.getSelectionModel().getSelectedItem();
        if (selectedDestination == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }
        Destination destination = hibernateController.getEntityByID(Destination.class, selectedDestination.getColIDDestination());
        Checkpoint selectedCheckpoint = (Checkpoint) checkPointListView.getSelectionModel().getSelectedItem();
        if (selectedCheckpoint == null) {
            // No comment is selected, display an error message
            // TODO: implement error message handling
            return;
        }
        System.out.println(selectedCheckpoint.getTitle());
        hibernateController.delete(selectedCheckpoint);
        System.out.println(selectedCheckpoint.getTitle());
        destination.getCheckpoints().remove(selectedCheckpoint);
        System.out.println(selectedCheckpoint.getTitle());
        ObservableList<Checkpoint> checkPoints = FXCollections.observableArrayList(destination.getCheckpoints());
        Collections.sort(checkPoints, Comparator.comparingInt(Checkpoint::getId));
        checkPointListView.setItems(checkPoints);



    }
    public void checkpointDeleteButton(ActionEvent actionEvent) {

    }

    public void listCheckpointField(){
        DestinationTableParameter selectedForum = destinationTable.getSelectionModel().getSelectedItem();
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

}


