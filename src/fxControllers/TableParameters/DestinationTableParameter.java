package fxControllers.TableParameters;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DestinationTableParameter {
    private final SimpleIntegerProperty colIDDestination = new SimpleIntegerProperty();
    private final SimpleStringProperty colStartCityDestination = new SimpleStringProperty();
    private final SimpleStringProperty colEndCityDestination = new SimpleStringProperty();
    private final SimpleStringProperty colManagerDestination = new SimpleStringProperty();
    private final SimpleStringProperty colDriverDestination = new SimpleStringProperty();

    private final SimpleStringProperty colCargoDestination = new SimpleStringProperty();

    private final SimpleStringProperty colTruckDestination = new SimpleStringProperty();

    private final SimpleStringProperty colStatusDestination = new SimpleStringProperty();

    private final SimpleStringProperty colArrivalDestination = new SimpleStringProperty();

    private final SimpleStringProperty colDepartureDestination = new SimpleStringProperty();


    public DestinationTableParameter(){

    }

    public int getColIDDestination() {
        return colIDDestination.get();
    }

    public SimpleIntegerProperty colIDDestinationProperty() {
        return colIDDestination;
    }

    public void setColIDDestination(int colIDDestination) {
        this.colIDDestination.set(colIDDestination);
    }

    public String getColStartCityDestination() {
        return colStartCityDestination.get();
    }

    public SimpleStringProperty colStartCityDestinationProperty() {
        return colStartCityDestination;
    }

    public void setColStartCityDestination(String colStartCityDestination) {
        this.colStartCityDestination.set(colStartCityDestination);
    }

    public String getColEndCityDestination() {
        return colEndCityDestination.get();
    }

    public SimpleStringProperty colEndCityDestinationProperty() {
        return colEndCityDestination;
    }

    public void setColEndCityDestination(String colEndCityDestination) {
        this.colEndCityDestination.set(colEndCityDestination);
    }

    public String getColManagerDestination() {
        return colManagerDestination.get();
    }

    public SimpleStringProperty colManagerDestinationProperty() {
        return colManagerDestination;
    }

    public void setColManagerDestination(String colManagerDestination) {
        this.colManagerDestination.set(colManagerDestination);
    }

    public String getColDriverDestination() {
        return colDriverDestination.get();
    }

    public SimpleStringProperty colDriverDestinationProperty() {
        return colDriverDestination;
    }

    public void setColDriverDestination(String colDriverDestination) {
        this.colDriverDestination.set(colDriverDestination);
    }

    public String getColCargoDestination() {
        return colCargoDestination.get();
    }

    public SimpleStringProperty colCargoDestinationProperty() {
        return colCargoDestination;
    }

    public void setColCargoDestination(String colCargoDestination) {
        this.colCargoDestination.set(colCargoDestination);
    }

    public String getColTruckDestination() {
        return colTruckDestination.get();
    }

    public SimpleStringProperty colTruckDestinationProperty() {
        return colTruckDestination;
    }

    public void setColTruckDestination(String colTruckDestination) {
        this.colTruckDestination.set(colTruckDestination);
    }

    public String getColStatusDestination() {
        return colStatusDestination.get();
    }

    public SimpleStringProperty colStatusDestinationProperty() {
        return colStatusDestination;
    }

    public void setColStatusDestination(String colStatusDestination) {
        this.colStatusDestination.set(colStatusDestination);
    }

    public String getColArrivalDestination() {
        return colArrivalDestination.get();
    }

    public SimpleStringProperty colArrivalDestinationProperty() {
        return colArrivalDestination;
    }

    public void setColArrivalDestination(String colArrivalDestination) {
        this.colArrivalDestination.set(colArrivalDestination);
    }

    public String getColDepartureDestination() {
        return colDepartureDestination.get();
    }

    public SimpleStringProperty colDepartureDestinationProperty() {
        return colDepartureDestination;
    }

    public void setColDepartureDestination(String colDepartureDestination) {
        this.colDepartureDestination.set(colDepartureDestination);
    }
}
