package fxControllers.TableParameters;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TruckTableParameter {
    private final SimpleIntegerProperty colIDTruck = new SimpleIntegerProperty();
    private final SimpleStringProperty colMakeTruck = new SimpleStringProperty();
    private final SimpleStringProperty colModelTruck = new SimpleStringProperty();
    private final SimpleIntegerProperty colModelYear = new SimpleIntegerProperty();
    private final SimpleDoubleProperty colOdometerTruck = new SimpleDoubleProperty();
    private final SimpleDoubleProperty colTankTruck = new SimpleDoubleProperty();
    private final SimpleStringProperty colTyreTruck = new SimpleStringProperty();
    private final SimpleStringProperty colDestinationTruck = new SimpleStringProperty();

    public TruckTableParameter(){

    }

    public int getColIDTruck() {
        return colIDTruck.get();
    }

    public SimpleIntegerProperty colIDTruckProperty() {
        return colIDTruck;
    }

    public void setColIDTruck(int colIDTruck) {
        this.colIDTruck.set(colIDTruck);
    }

    public String getColMakeTruck() {
        return colMakeTruck.get();
    }

    public SimpleStringProperty colMakeTruckProperty() {
        return colMakeTruck;
    }

    public void setColMakeTruck(String colMakeTruck) {
        this.colMakeTruck.set(colMakeTruck);
    }

    public String getColModelTruck() {
        return colModelTruck.get();
    }

    public SimpleStringProperty colModelTruckProperty() {
        return colModelTruck;
    }

    public void setColModelTruck(String colModelTruck) {
        this.colModelTruck.set(colModelTruck);
    }

    public int getColModelYear() {
        return colModelYear.get();
    }

    public SimpleIntegerProperty colModelYearProperty() {
        return colModelYear;
    }

    public void setColModelYear(int colModelYear) {
        this.colModelYear.set(colModelYear);
    }

    public double getColOdometerTruck() {
        return colOdometerTruck.get();
    }

    public SimpleDoubleProperty colOdometerTruckProperty() {
        return colOdometerTruck;
    }

    public void setColOdometerTruck(double colOdometerTruck) {
        this.colOdometerTruck.set(colOdometerTruck);
    }

    public double getColTankTruck() {
        return colTankTruck.get();
    }

    public SimpleDoubleProperty colTankTruckProperty() {
        return colTankTruck;
    }

    public void setColTankTruck(double colTankTruck) {
        this.colTankTruck.set(colTankTruck);
    }

    public String getColTyreTruck() {
        return colTyreTruck.get();
    }

    public SimpleStringProperty colTyreTruckProperty() {
        return colTyreTruck;
    }

    public void setColTyreTruck(String colTyreTruck) {
        this.colTyreTruck.set(colTyreTruck);
    }

    public String getColDestinationTruck() {
        return colDestinationTruck.get();
    }

    public SimpleStringProperty colDestinationTruckProperty() {
        return colDestinationTruck;
    }

    public void setColDestinationTruck(String colDestinationTruck) {
        this.colDestinationTruck.set(colDestinationTruck);
    }
}
