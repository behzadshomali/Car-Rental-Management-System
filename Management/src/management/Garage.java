package management;

import vehicle.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class Garage {
    private List<Vehicle> vehicles = new ArrayList<>();
    private int garageCode;
    
    public int getGarageCode() {
        return this.garageCode;
    }

    public Garage(int garageCode){
        this.garageCode = garageCode;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
