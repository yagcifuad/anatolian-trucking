package fxControllers.TableParameters;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ForumTableParameter {

    private final SimpleStringProperty colForumTitle = new SimpleStringProperty();
    private final SimpleIntegerProperty colForumID = new SimpleIntegerProperty();


    public ForumTableParameter() {
    }


    public String getColForumTitle() {
        return colForumTitle.get();
    }

    public SimpleStringProperty colForumTitleProperty() {
        return colForumTitle;
    }

    public void setColForumTitle(String colForumTitle) {
        this.colForumTitle.set(colForumTitle);
    }

    public int getColForumID() {
        return colForumID.get();
    }

    public SimpleIntegerProperty colForumIDProperty() {
        return colForumID;
    }

    public void setColForumID(int colForumID) {
        this.colForumID.set(colForumID);
    }
}
