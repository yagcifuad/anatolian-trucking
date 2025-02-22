package fxControllers;


import hibernateControllers.HibernateController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Driver;
import model.Manager;
import model.User;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginPage {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FreightCompany");
    HibernateController hibernateController = new HibernateController(entityManagerFactory);
//    private static DatabaseReference databaseReference;
//    // Initialize the Firebase app and database reference----------------------------------------------------------------------------------------------------------------------------------------------------------------
//    static {
//        try {
//            FileInputStream serviceAccount = new FileInputStream("Users/...your adminsdk address on your computer");
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://yourfirabasedatabaseaddress")
//                    .build();
//            FirebaseApp.initializeApp(options);
//            //databaseReference = FirebaseDatabase.getInstance().getReference("new");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



    public void validate() throws Exception {


//        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(loginField.getText());
//        System.out.println(userRecord.getp);

        User driver = hibernateController.findUserByCredentials(loginField.getText(), passwordField.getText(), Driver.class);
        User manager = hibernateController.findUserByCredentials(loginField.getText(), passwordField.getText(), Manager.class);


        if (driver != null || manager != null) {
           Parent parent = null;


            if (driver != null) {//driver
                FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Main/main-page-driver.fxml"));
                parent = fxmlLoader.load();
                MainPageDriver mainPageDriver= fxmlLoader.getController();
                mainPageDriver.setInfo(driver, entityManagerFactory);
            } else if (!isAdmin(manager)) {//normal manager
                FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Main/main-page-manager.fxml"));
                 parent = fxmlLoader.load();
                MainPageManager mainPageManager= fxmlLoader.getController();
                mainPageManager.setInfo(manager, entityManagerFactory);
            }else{//admin
                FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Main/main-page.fxml"));
                parent = fxmlLoader.load();
                MainPage mainPage= fxmlLoader.getController();
                mainPage.setInfo(manager, entityManagerFactory);}
//            FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Main/main-page.fxml"));
//            parent = fxmlLoader.load();
//            MainPage mainPage= fxmlLoader.getController();
//            mainPage.setInfo(manager, entityManagerFactory);


            Scene scene = new Scene(parent);
            Stage stage = (Stage) loginField.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
        }
    }

    public void register() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/registration-page.fxml"));
        Parent parent = fxmlLoader.load();
        RegistrationPage registrationPage = fxmlLoader.getController();
        registrationPage.setData(entityManagerFactory);
        Scene scene = new Scene(parent);


        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setTitle("FreightSys");
        stage.setScene(scene);
        stage.show();
    }
    public static void alertDialog(String message, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }


    public boolean isAdmin(User user) {
        if (user instanceof Manager) {
            if (((Manager) user).isAdmin()) {
                return true;
            }
        }
        return false;
    }
}
