package fxControllers;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import fxControllers.EditPages.CommentEditPage;
import fxControllers.EditPages.DestinationEditPage;
import fxControllers.TableParameters.*;
import hibernateControllers.HibernateController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import org.hibernate.LazyInitializationException;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class ForumPage  implements Initializable {
    public ListView listForum;
    public TreeView commentTree;
    public TextArea commentBody;
    @FXML
    public Button returnFromForumButton;
    public TextField forumTitleField;
    public TextArea commentField;
    public Button createForumTitleButton;
    public ListView commentListField;
    private ObservableList<ForumTableParameter> dataForum = FXCollections.observableArrayList();

    public TableView<ForumTableParameter> forumTable;

    public TableColumn<ForumTableParameter, String> colForumTitle;

    private User loggedUser;
    private EntityManagerFactory entityManagerFactory;
    private HibernateController hibernateController;

    private Timeline refreshTimeline;
    private static DatabaseReference databaseReference;


// Create a Timeline object that will refresh the comment list every second
//static {
//    try {
//        FileInputStream serviceAccount = new FileInputStream("/Users/fuadyagci/IdeaUltimateProjects/AnatolianTrucking/src/FIREBASE/mydatabase-firebase-adminsdk.json");
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://mydatabase-c591e-default-rtdb.europe-west1.firebasedatabase.app/") //.setDatabaseUrl("https://mydatabase-c591e.firebaseio.com")
//                .build();
//        FirebaseApp.initializeApp(options);
//        //databaseReference = FirebaseDatabase.getInstance().getReference("new");
//
//
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        forumTable.setEditable(true);
        colForumTitle.setCellValueFactory(new PropertyValueFactory<>("colForumTitle"));

        Duration javaDuration = Duration.ofSeconds(3);
        javafx.util.Duration javafxDuration = javafx.util.Duration.millis(javaDuration.toMillis());

        refreshTimeline = new Timeline(new KeyFrame((javafxDuration), event -> {
            refreshComments();
        }));
        refreshTimeline.setCycleCount(Timeline.INDEFINITE);
        refreshTimeline.play();



    }

    private void updateCommentList(ActionEvent actionEvent) {
    }



    public void setInfo(User user, EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.loggedUser = user;
        loadForumData();
    }

    private void loadForumData(){
        List<Forum> forumList = hibernateController.getAllRecords(Forum.class);
        for (Forum c: forumList){
            ForumTableParameter forumTableParameter= new ForumTableParameter();
            forumTableParameter.setColForumTitle(c.getTitle());
            forumTableParameter.setColForumID(c.getId());

            dataForum.add(forumTableParameter);
        }
        forumTable.setItems(dataForum);
    }

    public void returnFromForum(ActionEvent actionEvent) throws Exception {

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
            Stage stage = (Stage) commentListField.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//      alertDialog("No such user", "User error");
        }

    }

    public void createForumTitleButton() {

        List<Comment> comments = new ArrayList<Comment>();
        Forum forum = new Forum(forumTitleField.getText(),loggedUser);
        hibernateController.create(forum);
//        databaseReference = FirebaseDatabase.getInstance().getReference("forum");
//        databaseReference.push().setValueAsync(forum);
        forumTable.getItems().clear();
        loadForumData();
        forumTitleField.setText(null);

    }


    public void commentSentButton(ActionEvent actionEvent) throws LazyInitializationException {

        ForumTableParameter selectedForum = forumTable.getSelectionModel().getSelectedItem();
        if (selectedForum == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }

        Forum forum = hibernateController.getEntityByID(Forum.class, selectedForum.getColForumID());
        System.out.println(forum.getTitle());
        Comment selectedComment = (Comment) commentListField.getSelectionModel().getSelectedItem();
        Comment newComment;
        if (selectedComment == null) {
            // If no comment is selected, create a new comment
            newComment = new Comment(commentField.getText(), loggedUser, forum, null);
        } else {
            // If a comment is selected, create a new reply to that comment
            newComment = new Comment(commentField.getText(), loggedUser, forum, selectedComment);
        }

        //Comment newComment = new Comment(commentField.getText(), loggedUser, forum);
        System.out.println(forum.getComments());
        hibernateController.create(newComment);


        forum.getComments().add(newComment);

        //hibernateController.update(forum);
        commentField.setText(null);

        if (selectedForum == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }

        ObservableList<Comment> comments = FXCollections.observableArrayList(forum.getComments());
        Collections.sort(comments, Comparator.comparingInt(Comment::getId));
        commentListField.setItems(comments);


    }



    public void commentEditButton(ActionEvent actionEvent) throws IOException {
        ForumTableParameter selectedForum = forumTable.getSelectionModel().getSelectedItem();
        if (selectedForum == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }
        Forum forum = hibernateController.getEntityByID(Forum.class, selectedForum.getColForumID());
        Comment selectedComment = (Comment) commentListField.getSelectionModel().getSelectedItem();
        if (selectedComment == null) {
            // No comment is selected, display an error message
            // TODO: implement error message handling
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/Edit/CommentEditPage.fxml"));
        Parent parent = fxmlLoader.load();
        CommentEditPage commentEditPage= fxmlLoader.getController();
        commentEditPage.setInfo(selectedComment,entityManagerFactory, loggedUser);
        commentEditPage.fillFields();

        Scene scene = new Scene(parent);
        Stage stage = (Stage) commentField.getScene().getWindow();
        stage.setTitle("Edit Cargo");
        stage.setScene(scene);
        stage.show();



    }



    public void commentDeleteButton(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;

        ForumTableParameter selectedForum = forumTable.getSelectionModel().getSelectedItem();
        if (selectedForum == null) {
            // No Forum is selected, display an error message
            // TODO: implement error message handling
            return;
        }
        Forum forum = hibernateController.getEntityByID(Forum.class, selectedForum.getColForumID());
        Comment selectedComment = (Comment) commentListField.getSelectionModel().getSelectedItem();
        if (selectedComment == null) {
            // No comment is selected, display an error message
            // TODO: implement error message handling
            return;
        }

        hibernateController.delete(selectedComment);
        forum.getComments().remove(selectedComment);
        ObservableList<Comment> comments = FXCollections.observableArrayList(forum.getComments());
        Collections.sort(comments, Comparator.comparingInt(Comment::getId));
        commentListField.setItems(comments);
    }

    public void tableSelected(MouseEvent mouseEvent) {
        listCommentField();

        //ForumTableParameter selectedForum = forumTable.getSelectionModel().getSelectedItem();
        // Forum forum=hibernateController.getEntityByID(Forum.class,selectedForum.getColForumID());
    }




    public void deleteForumTitleButton(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;
        ForumTableParameter selectedForum = forumTable.getSelectionModel().getSelectedItem();
        Forum forum = hibernateController.getEntityByID(model.Forum.class, selectedForum.getColForumID());

        hibernateController.delete(forum);
        forumTable.getItems().remove(selectedForum);

    }


    private void refreshComments() {
        listCommentField();
    }

    public void listCommentField(){
        ForumTableParameter selectedForum = forumTable.getSelectionModel().getSelectedItem();
        if (selectedForum == null) {
            return;
        }
        Forum forum = hibernateController.getEntityByID(Forum.class, selectedForum.getColForumID());
        ObservableList<Comment> comments = FXCollections.observableArrayList(forum.getComments());
        Collections.sort(comments, Comparator.comparingInt(Comment::getId));

        commentListField.setItems(comments);

    }

    public void editForumButton(ActionEvent actionEvent) {
        if (!(isAdmin(loggedUser))) return;
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