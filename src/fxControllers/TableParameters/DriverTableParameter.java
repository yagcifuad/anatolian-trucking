package fxControllers.TableParameters;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DriverTableParameter {
  private final SimpleIntegerProperty colId = new SimpleIntegerProperty();
  private final SimpleStringProperty colLogin = new SimpleStringProperty();
  private final SimpleStringProperty colName = new SimpleStringProperty();
  private final SimpleStringProperty colSurname = new SimpleStringProperty();
  private final SimpleStringProperty colPhoneNum = new SimpleStringProperty();

  public DriverTableParameter(){

  }

  public int getColId() {
    return colId.get();
  }

  public SimpleIntegerProperty colIdProperty() {
    return colId;
  }

  public void setColId(int colId) {
    this.colId.set(colId);
  }

  public String getColLogin() {
    return colLogin.get();
  }

  public SimpleStringProperty colLoginProperty() {
    return colLogin;
  }

  public void setColLogin(String colLogin) {
    this.colLogin.set(colLogin);
  }

  public String getColName() {
    return colName.get();
  }

  public SimpleStringProperty colNameProperty() {
    return colName;
  }

  public void setColName(String colName) {
    this.colName.set(colName);
  }

  public String getColSurname() {
    return colSurname.get();
  }

  public SimpleStringProperty colSurnameProperty() {
    return colSurname;
  }

  public void setColSurname(String colSurname) {
    this.colSurname.set(colSurname);
  }

  public String getColPhoneNum() {
    return colPhoneNum.get();
  }

  public SimpleStringProperty colPhoneNumProperty() {
    return colPhoneNum;
  }

  public void setColPhoneNum(String colPhoneNum) {
    this.colPhoneNum.set(colPhoneNum);
  }
}
