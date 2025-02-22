package fxControllers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Driver;
import model.Manager;
import model.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrationPage implements Initializable {
    public TextField loginField;
    public TextField nameField;
    public TextField surnameField;
    public PasswordField pswField;
    public PasswordField repPswField;
    public DatePicker bDateField;
    public TextField managerEmailField;
    public TextField phoneNumField;
    public RadioButton radioD;
    public RadioButton radioM;
    public CheckBox isAdminChk;
    public DatePicker medCertField;
    public TextField medCertNum;
    public TextField driverLicenseField;
    public ToggleGroup userType;
    public Label photoField;
    public Button photoChooseButton;
    public DatePicker employmentDateField;

    private EntityManagerFactory entityManagerFactory;
    private HibernateController hibernateController;

    private static DatabaseReference databaseReference;
    //////////////////////////////////////////////////////////////////////////////////

    String filename2= null;
    byte[] user_image2=null;
    //////////////////////////////////////////////////////////////////////////////////



    // Initialize the Firebase app and database reference----------------------------------------------------------------------------------------------------------------------------------------------------------------
    static {
        try {
            FileInputStream serviceAccount = new FileInputStream("/Users/fuadyagci/IdeaUltimateProjects/AnatolianTrucking/src/FIREBASE/mydatabase-firebase-adminsdk.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mydatabase-c591e-default-rtdb.europe-west1.firebasedatabase.app/") //.setDatabaseUrl("https://mydatabase-c591e.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
            //databaseReference = FirebaseDatabase.getInstance().getReference("new");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




    public void setData(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioM.setSelected(true);
        isAdminChk.setVisible(false);
        disableFields();


    }

    public void createNewUser() throws Exception {

        if (user_image2 == null) { user_image2 = Files.readAllBytes(Path.of("/Users/fuadyagci/IdeaUltimateProjects/SmallSizeExample/src/empty.jpg"));
            try {
                user_image2 = Files.readAllBytes(Path.of("empty.jpg"));
            } catch (NoSuchFileException e) {
                // Handle the exception
            }}

        String password1 = pswField.getText();
        String password2 = repPswField.getText();

        String login1 = loginField.getText();
        String name1 = nameField.getText();
        String phone1 = phoneNumField.getText();


        if (!password1.equals(password2)) {
             register2();pswMessage();
        } else {
            if (password1.equals("")||login1.equals("") ||name1.equals("")||phone1.equals("")) {
                register2();emptyMessage();
            } else {



        String login = loginField.getText();
        String password = pswField.getText();

        if (hibernateController.findUserByCredentials(login, password, Driver.class) != null
                || hibernateController.findUserByCredentials(login, password, Manager.class) != null ){
            hibernateController.alertDialog("A user with this login already exists.", "Login Error");
            return;
        }


        if (radioD.isSelected()){
           // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                    .setEmail(loginField.getText())
//                    .setEmailVerified(false)
//                    .setPassword(pswField.getText())
//                    .setDisplayName(nameField.getText());
//
//            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
//            System.out.println("Successfully created new user: " + userRecord.getUid());
                //    ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


            Driver driver = new Driver(loginField.getText(),pswField.getText(),nameField.getText(),surnameField.getText(),
                    bDateField.getValue(),phoneNumField.getText(),user_image2,
                    LocalDate.parse(medCertField.getValue().toString()),medCertNum.getText(),driverLicenseField.getText());

            hibernateController.create(driver);
          //  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            databaseReference = FirebaseDatabase.getInstance().getReference("driver");
//            databaseReference.push().setValueAsync(driver);
          //  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        } else {
            //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                    .setEmail(loginField.getText())
//                    .setEmailVerified(false)
//                    .setPassword(pswField.getText())
//                    .setDisplayName(nameField.getText());
//
//            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
  //          System.out.println("Successfully created new user: " + userRecord.getUid());
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            Manager manager = new Manager(loginField.getText(), pswField.getText(), nameField.getText(), surnameField.getText(),
                    bDateField.getValue(),phoneNumField.getText(), user_image2, managerEmailField.getText(), employmentDateField.getValue(),isAdminChk.isSelected());
            hibernateController.create(manager);

                //public Manager(String login, String password, String name, String surname, LocalDate birthDate, String phoneNum, byte[] user_image, String email, LocalDate employmentDate, boolean isAdmin) {

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//            databaseReference = FirebaseDatabase.getInstance().getReference("/manager");
//            databaseReference.push().setValueAsync(manager);
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        }
        }}

        returnToPrevious();

    }

    public void returnToPrevious() throws IOException {
       // User user = new Manager ("admin", "admin","admin","admin", LocalDate.parse("2000-01-01"),"dafaf","sdfgsdfgfsd","sdfg");
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setTitle("FreightSys");
        stage.setScene(scene);
        stage.show();
    }

    public void disableFields() {
        if (radioD.isSelected()){
            medCertField.setDisable(false);
            medCertNum.setDisable(false);
            driverLicenseField.setDisable(false);
            managerEmailField.setDisable(true);
        }else {
            medCertField.setDisable(true);
            medCertNum.setDisable(true);
            driverLicenseField.setDisable(true);
            managerEmailField.setDisable(false);

        }
    }

            public void register2() throws IOException {
              //  User user = new Manager ("admin", "admin","admin","admin", LocalDate.parse("2000-01-01"),"dafaf","fuadyagci@gmail.com","345");
                FXMLLoader fxmlLoader = new FXMLLoader(RegistrationPage.class.getResource("../view/registration-page.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) loginField.getScene().getWindow();
                stage.setTitle("FreightSys");
                stage.setScene(scene);
                stage.show();
            }

    public static void pswMessage() throws IOException {

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Passwords do not match");
        alert.showAndWait();

    }

    public static void emptyMessage() throws IOException {

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Can not creat empty user!");
        alert.showAndWait();

    }

    public void photoChooseButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image File");

        // Set the file extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            filename2 =selectedFile.getAbsolutePath() ;
            photoField.setText(selectedFile.getAbsolutePath());
            user_image2 = Files.readAllBytes(Path.of(selectedFile.toURI()));
        } else { user_image2 = Files.readAllBytes(Path.of("empty.jpg"));}



    }
}
