package fxControllers.TableParameters;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ManagerTableParameter {
  private final SimpleIntegerProperty colIdManager = new SimpleIntegerProperty();
  private final SimpleStringProperty colLoginManager = new SimpleStringProperty();
  private final SimpleStringProperty colNameManager = new SimpleStringProperty();
  private final SimpleStringProperty colSurnameManager = new SimpleStringProperty();
  private final SimpleStringProperty colPhoneNumManager = new SimpleStringProperty();

  public ManagerTableParameter(){

  }

  public int getColIdManager() {
    return colIdManager.get();
  }

  public SimpleIntegerProperty colIdManagerProperty() {
    return colIdManager;
  }

  public void setColIdManager(int colIdManager) {
    this.colIdManager.set(colIdManager);
  }

  public String getColLoginManager() {
    return colLoginManager.get();
  }

  public SimpleStringProperty colLoginManagerProperty() {
    return colLoginManager;
  }

  public void setColLoginManager(String colLoginManager) {
    this.colLoginManager.set(colLoginManager);
  }

  public String getColNameManager() {
    return colNameManager.get();
  }

  public SimpleStringProperty colNameManagerProperty() {
    return colNameManager;
  }

  public void setColNameManager(String colNameManager) {
    this.colNameManager.set(colNameManager);
  }

  public String getColSurnameManager() {
    return colSurnameManager.get();
  }

  public SimpleStringProperty colSurnameManagerProperty() {
    return colSurnameManager;
  }

  public void setColSurnameManager(String colSurnameManager) {
    this.colSurnameManager.set(colSurnameManager);
  }

  public String getColPhoneNumManager() {
    return colPhoneNumManager.get();
  }

  public SimpleStringProperty colPhoneNumManagerProperty() {
    return colPhoneNumManager;
  }

  public void setColPhoneNumManager(String colPhoneNumManager) {
    this.colPhoneNumManager.set(colPhoneNumManager);
  }

}

