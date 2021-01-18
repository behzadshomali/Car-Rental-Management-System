
package management;

import java.util.Date;
import vehicle.Vehicle;

public class BorrowedVehicles extends vehicle.Vehicle{

    private Date startDate = new Date();
    private Date endDate = new Date();
    private String renter;
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    public BorrowedVehicles(Vehicle vehicle, Double totalPrice,String renter, Date startDate, Date endDate) {
        super(vehicle.getManufacturer(), vehicle.getModelName(), vehicle.getManufactureYear(),
                vehicle.getPassengersCount(), vehicle.getInformation(), totalPrice, vehicle.getGarageCode());
        this.renter = renter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicle = vehicle;
        
        
//        ManagementSystem.vehicles.remove(vehicle);
//        
//        boolean flag = false;
//        for(int i = 0; i < ManagementSystem.garages.size(); i++){
//            for(int j = 0; j < ManagementSystem.garages.get(i).getVehicles().size(); j++){
//                if(vehicle.equals(ManagementSystem.garages.get(i).getVehicles().get(j))){
//                    ManagementSystem.garages.get(i).getVehicles().remove(vehicle);
//                    flag = true;
//                    break;
//                }
//                if(flag)
//                    break;
//            }
//        }
    }
    
}
