package management;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Motor;
import vehicle.Truck;
import vehicle.Vehicle;


public class ManagementSystem {
    public static List<Store> stores = new ArrayList<>();
    public static List<Employee> employees = new ArrayList<>();
    public static List<Vehicle> vehicles = new ArrayList<>();
    public static List<Garage> garages = new ArrayList<>();
    public static List<BorrowedVehicles> carsInRent = new ArrayList<>(); 
    public static List<Double> carIncomes = new ArrayList<>();
    public static List<Double> busIncomes = new ArrayList<>();
    public static List<Double> truckIncomes = new ArrayList<>();
    public static List<Double> motorIncomes = new ArrayList<>();
    
    public static  boolean loggingIn(String userInput, String passInput){
        String userName = "behzadShomali";
        String password = "9722762451";
        
        return userInput.equalsIgnoreCase(userName) && passInput.equalsIgnoreCase(password);
    }
    
    public static boolean removeEmployee(String name){
        int i;
        String fullName;
        for(i = 0; i < ManagementSystem.employees.size(); i++){
            fullName = ManagementSystem.employees.get(i).getName() + " " + ManagementSystem.employees.get(i).getLastName();
            if(name.equalsIgnoreCase(fullName)){
                ManagementSystem.employees.remove(i);
                return true;
            }    
        }
        return false;
    }
    
    public static Store findStoreByName(String name){
        Store store = null;
        for(int i = 0; i < ManagementSystem.stores.size(); i++){
            if(name.equals(ManagementSystem.stores.get(i).getStoreName())){
                store = ManagementSystem.stores.get(i);
                break;
            }
        }
        return store;
    }
    
    public static List<Store> findStoresByGarageCode(int code){
        Garage garage = findGarageByCode(code);
        List<Store> outputStores = new ArrayList<>();
        for(int i = 0; i < stores.size(); i++){
            if(stores.get(i).getGarages().contains(garage)){
                outputStores.add(stores.get(i));
            }
        }
        return outputStores;
    }
     
    public static Vehicle findVehicleByName(String name){
        Vehicle vehicle = null;
        for(int i = 0; i < vehicles.size(); i++){
            if(name.equalsIgnoreCase(vehicles.get(i).getModelName())){
                vehicle = vehicles.get(i);
                break;
            }
        } 
        return vehicle;
    }
    public static Vehicle findVehicleByCode(int code){
        Vehicle vehicle = null;
        for(int i = 0; i < vehicles.size(); i++){
            if(code == vehicles.get(i).getCode()){
                vehicle = vehicles.get(i);
                break;
            }
        } 
        return vehicle;
    }
    
    public static void listViewRemoveAbility(ListView<String> list){
        list.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
            list.setOnKeyPressed(e2 -> {
                if(e2.getCode() == KeyCode.DELETE && newValue != null){
                    Vehicle vehicle = findVehicleByName(newValue);
                    vehicles.remove(vehicle);
                    boolean flag;
                    for(int i = 0; i < ManagementSystem.garages.size(); i++){
                        flag = false;
                        for(int j = 0; j < ManagementSystem.garages.get(i).getVehicles().size(); j++){
                            if(vehicle.equals(ManagementSystem.garages.get(i).getVehicles().get(j))){
                                ManagementSystem.garages.get(i).getVehicles().remove(vehicle);
                                flag = true;
                                break;
                            }
                            if(flag)
                                break;
                        }
                    }
                    list.getItems().remove(newValue);
                }
            });
        });
    }
    
    public static void timePasseing(Stage window){
        Thread timeThread = new Thread(() -> {
            Date now = new Date();
            Random random = new Random();
            while(window.isShowing()){
                for(int x = 0; x < carsInRent.size(); x++){
                    Vehicle vehicle = carsInRent.get(x).getVehicle(); 
                    if(now.equals(carsInRent.get(x).getStartDate())){
                        if(vehicle instanceof Car)
                            carIncomes.add(vehicle.getPrice());
                        else if(vehicle instanceof Bus)
                            busIncomes.add(vehicle.getPrice());
                        else if(vehicle instanceof Truck)
                            truckIncomes.add(vehicle.getPrice());
                        else if(vehicle instanceof Motor)
                            motorIncomes.add(vehicle.getPrice());
                        ManagementSystem.vehicles.remove(vehicle); 
                        boolean flag = false;
                        for(int i = 0; i < ManagementSystem.garages.size(); i++){
                            for(int j = 0; j < ManagementSystem.garages.get(i).getVehicles().size(); j++){
                                if(vehicle.equals(ManagementSystem.garages.get(i).getVehicles().get(j))){
                                    ManagementSystem.garages.get(i).getVehicles().remove(vehicle);
                                    flag = true;
                                    break;
                                }
                                if(flag)
                                    break;
                            }
                        }
                    }
                    else if(now.equals(carsInRent.get(x).getEndDate())){
                        boolean isOk;
                        if(random.nextInt(2) == 1)
                            isOk = true;
                        else{
                            isOk = false;
                            repair(vehicle);
                        }
                        ManagementSystem.vehicles.add(vehicle);
                        ManagementSystem.carsInRent.remove(carsInRent.get(x));
                        ManagementSystem.findGarageByCode(vehicle.getGarageCode()).getVehicles().add(vehicle);
                    }
                }
            }
        });
        timeThread.start();
    }
    
    public static void repair(Vehicle vehicle){
        vehicle.setFixed(true);
    }

    public static Store findStoreByCode(int code) {
        Store store = null;
        for(int i = 0; i < ManagementSystem.stores.size(); i++){
            if(code == ManagementSystem.stores.get(i).getStoreCode()){
                store = ManagementSystem.stores.get(i);
                break;
            }
        }
        return store;
    }
    
    public static Garage findGarageByCode(int code){
        Garage garage = null;
        for(int i = 0; i < ManagementSystem.garages.size(); i++){
            if(code == ManagementSystem.garages.get(i).getGarageCode()){
                garage = ManagementSystem.garages.get(i);
                break;
            }
        }
        return garage;
    }
}