package fxControllers.EditPages;

import fxControllers.ForumPage;
import fxControllers.LoginPage;
import fxControllers.MainPage;
import hibernateControllers.HibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import javax.persistence.EntityManagerFactory;
import java.net.URL;
import java.time.LocalDate;

public class CommentEditPage {


    public TextField commentField;
    private Comment comment;
    private EntityManagerFactory entityManagerFactory;

    private HibernateController hibernateController;

    private User loggedUser;

    public void save(ActionEvent actionEvent) throws Exception {
        comment.setCommentText(commentField.getText());

        hibernateController.update(comment);

        returnToPrevious3();
    }

    public void setInfo( Comment comment,EntityManagerFactory entityManagerFactory, User loggedUser){
        this.entityManagerFactory = entityManagerFactory;
        this.hibernateController = new HibernateController(entityManagerFactory);
        this.comment = (Comment) comment;
        this.loggedUser = loggedUser;
    }
    public void fillFields() {
        commentField.setText(comment.getCommentText());

    }

    public void returnToPrevious3( ) throws Exception {
        User driver = hibernateController.findUserByCredentials(loggedUser.getLogin(), loggedUser.getPassword(), Driver.class);
        User manager = hibernateController.findUserByCredentials(loggedUser.getLogin(), loggedUser.getPassword(), Manager.class);

        if (driver != null || manager != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(LoginPage.class.getResource("../view/forum-page.fxml"));
            Parent parent = fxmlLoader.load();

            ForumPage forumPage= fxmlLoader.getController();

            if (driver == null) forumPage.setInfo(manager, entityManagerFactory);
            else forumPage.setInfo(driver, entityManagerFactory);

            Scene scene = new Scene(parent);
            //Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) commentField.getScene().getWindow();
            stage.setTitle("FreightSys");
            stage.setScene(scene);
            stage.show();
        } else {
            hibernateController.alertDialog("No such user", "User error");
//
        }


    }
}
