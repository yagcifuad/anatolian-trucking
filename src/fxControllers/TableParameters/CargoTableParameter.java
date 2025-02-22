package fxControllers.TableParameters;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.CargoType;

import javax.management.openmbean.SimpleType;
import java.text.SimpleDateFormat;

public class CargoTableParameter {

    private final SimpleIntegerProperty colIDCargo = new SimpleIntegerProperty();
    private final SimpleStringProperty colTitleCargo = new SimpleStringProperty();
    private final SimpleStringProperty colCreatedCargo = new SimpleStringProperty();
    private final SimpleStringProperty colUpdatedCargo = new SimpleStringProperty();
    private final SimpleDoubleProperty colWeightCargo = new SimpleDoubleProperty();
    private final SimpleStringProperty colTypeCargo = new SimpleStringProperty();
    private final SimpleStringProperty colCustomerCargo = new SimpleStringProperty();
    private final SimpleStringProperty colDescriptionCargo = new SimpleStringProperty();
    private final SimpleStringProperty colDestinationCargo = new SimpleStringProperty();

    public CargoTableParameter() {
    }

    public int getColIDCargo() {
        return colIDCargo.get();
    }

    public SimpleIntegerProperty colIDCargoProperty() {
        return colIDCargo;
    }

    public void setColIDCargo(int colIDCargo) {
        this.colIDCargo.set(colIDCargo);
    }

    public String getColTitleCargo() {
        return colTitleCargo.get();
    }

    public SimpleStringProperty colTitleCargoProperty() {
        return colTitleCargo;
    }

    public void setColTitleCargo(String colTitleCargo) {
        this.colTitleCargo.set(colTitleCargo);
    }

    public String getColCreatedCargo() {
        return colCreatedCargo.get();
    }

    public SimpleStringProperty colCreatedCargoProperty() {
        return colCreatedCargo;
    }

    public void setColCreatedCargo(String colCreatedCargo) {
        this.colCreatedCargo.set(colCreatedCargo);
    }

    public String getColUpdatedCargo() {
        return colUpdatedCargo.get();
    }

    public SimpleStringProperty colUpdatedCargoProperty() {
        return colUpdatedCargo;
    }

    public void setColUpdatedCargo(String colUpdatedCargo) {
        this.colUpdatedCargo.set(colUpdatedCargo);
    }

    public double getColWeightCargo() {
        return colWeightCargo.get();
    }

    public SimpleDoubleProperty colWeightCargoProperty() {
        return colWeightCargo;
    }

    public void setColWeightCargo(double colWeightCargo) {
        this.colWeightCargo.set(colWeightCargo);
    }

    public String getColTypeCargo() {
        return colTypeCargo.get();
    }

    public SimpleStringProperty colTypeCargoProperty() {
        return colTypeCargo;
    }

    public void setColTypeCargo(String colTypeCargo) {
        this.colTypeCargo.set(colTypeCargo);
    }

    public String getColCustomerCargo() {
        return colCustomerCargo.get();
    }

    public SimpleStringProperty colCustomerCargoProperty() {
        return colCustomerCargo;
    }

    public void setColCustomerCargo(String colCustomerCargo) {
        this.colCustomerCargo.set(colCustomerCargo);
    }

    public String getColDescriptionCargo() {
        return colDescriptionCargo.get();
    }

    public SimpleStringProperty colDescriptionCargoProperty() {
        return colDescriptionCargo;
    }

    public void setColDescriptionCargo(String colDescriptionCargo) {
        this.colDescriptionCargo.set(colDescriptionCargo);
    }

    public String getColDestinationCargo() {
        return colDestinationCargo.get();
    }

    public SimpleStringProperty colDestinationCargoProperty() {
        return colDestinationCargo;
    }

    public void setColDestinationCargo(String colDestinationCargo) {
        this.colDestinationCargo.set(colDestinationCargo);
    }
}
