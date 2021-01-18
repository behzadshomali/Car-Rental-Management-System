package management;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Motor;
import vehicle.Truck;

public class WriteJSON {
//    JSONObject jObject;
    Object object;
    JSONParser parser;

    public void writeBorrwed (String path)throws IOException, ParseException {
        JSONArray borrows = null;
        List<JSONObject> jObjects = new ArrayList<>();
        FileWriter file = new FileWriter(new File(path));
        JSONObject mainObj = new JSONObject();
        for(int i = 0; i < ManagementSystem.carsInRent.size(); i++){
            BorrowedVehicles bV = (BorrowedVehicles)ManagementSystem.carsInRent.get(i);
            jObjects.add(new JSONObject());
            JSONObject inner = new JSONObject();
            borrows = new JSONArray();
            Date tempDate = bV.getStartDate();
            String temp = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(tempDate);
            jObjects.get(i).put("startDate", temp);
            tempDate = bV.getEndDate();
            temp = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(tempDate);
            jObjects.get(i).put("endDate", temp);
            vehicle.Vehicle vehicle = bV.getVehicle();
            jObjects.get(i).put("renter", bV.getRenter());


            inner.put("name", vehicle.getModelName());
            inner.put("company", vehicle.getManufacturer());
            inner.put("year", vehicle.getManufactureYear());
            inner.put("info", vehicle.getInformation());
            inner.put("passengers", vehicle.getPassengersCount());
            inner.put("total-price", vehicle.getPassengersCount());
            String mode;
            if(vehicle instanceof Car)
                mode = "Car " + ((Car) vehicle).getMode();
            else if(vehicle instanceof Bus)
                mode = "Bus";
            else if(vehicle instanceof Truck)
                mode = "Truck";
            else
                mode = "Motor";
            inner.put("mode", mode);
            inner.put("code", vehicle.getGarageCode());
            jObjects.get(i).put("vehicle", inner);

        }
        for(int i = 0; i < jObjects.size(); i++){
            JSONObject temp = jObjects.get(i);
            borrows.add(temp);
        }
        mainObj.put("Borrowed", borrows);   
        file.write(mainObj.toJSONString());
        file.close();
    }
    
    public void writeGaragesStores(String path) throws IOException{
        JSONObject mainObject = new JSONObject();
        List<JSONObject> garagesObjects = new ArrayList<>();
        JSONArray carsArray;
        JSONArray busesArray;
        JSONArray trucksArray;
        JSONArray motorsArray;
        JSONArray garagesArray = new JSONArray();
        FileWriter file = new FileWriter(new File(path));
        
        for(int i = 0 ; i < ManagementSystem.garages.size(); i++){
//            garagesObjects.clear();
            carsArray = new JSONArray();
            busesArray = new JSONArray();
            trucksArray = new JSONArray();
            motorsArray = new JSONArray();
            garagesArray = new JSONArray();
            List<JSONObject> carsObjects = new ArrayList<>();
            List<JSONObject> busesObjects = new ArrayList<>();
            List<JSONObject> trucksObjects = new ArrayList<>();
            List<JSONObject> motorsObjects = new ArrayList<>();
            Garage garage = ManagementSystem.garages.get(i);
            garagesObjects.add(new JSONObject());
            garagesObjects.get(i).put("code", garage.getGarageCode());
            for(int j = 0; j < garage.getVehicles().size(); j++){
                if(garage.getVehicles().get(j) instanceof Car){
                    Car car = (Car)garage.getVehicles().get(j);
                    carsObjects.add(new JSONObject());
                    int x = carsObjects.size() - 1;
                    carsObjects.get(x).put("name", car.getModelName());
                    carsObjects.get(x).put("company", car.getManufacturer());
                    carsObjects.get(x).put("year", car.getManufactureYear());
                    carsObjects.get(x).put("info", car.getInformation());
                    carsObjects.get(x).put("passengers", car.getPassengersCount());
                    carsObjects.get(x).put("price", car.getPrice());
                    carsObjects.get(x).put("mode", car.getMode());
                }
                else if(garage.getVehicles().get(j) instanceof Bus){
                    
                    Bus bus = (Bus)garage.getVehicles().get(j);
                    busesObjects.add(new JSONObject());
                    int x = busesObjects.size() - 1;
                    busesObjects.get(x).put("name", bus.getModelName());
                    busesObjects.get(x).put("company", bus.getManufacturer());
                    busesObjects.get(x).put("year", bus.getManufactureYear());
                    busesObjects.get(x).put("info", bus.getInformation());
                    busesObjects.get(x).put("passengers", bus.getPassengersCount());
                    busesObjects.get(x).put("price", bus.getPrice());
                }
                else if(garage.getVehicles().get(j) instanceof Truck){
                    
                    Truck truck = (Truck)garage.getVehicles().get(j);
                    trucksObjects.add(new JSONObject());
                    int x = trucksObjects.size() - 1;
                    trucksObjects.get(x).put("name", truck.getModelName());
                    trucksObjects.get(x).put("company", truck.getManufacturer());
                    trucksObjects.get(x).put("year", truck.getManufactureYear());
                    trucksObjects.get(x).put("info", truck.getInformation());
                    trucksObjects.get(x).put("passengers", truck.getPassengersCount());
                    trucksObjects.get(x).put("price", truck.getPrice());
                }
                else{
                    Motor motor = (Motor)garage.getVehicles().get(j);
                    motorsObjects.add(new JSONObject());
                    int x = motorsObjects.size() - 1;
                    motorsObjects.get(x).put("name", motor.getModelName());
                    motorsObjects.get(x).put("company", motor.getManufacturer());
                    motorsObjects.get(x).put("year", motor.getManufactureYear());
                    motorsObjects.get(x).put("info", motor.getInformation());
                    motorsObjects.get(x).put("passengers", motor.getPassengersCount());
                    motorsObjects.get(x).put("price", motor.getPrice());
                }
            }
            for(int j = 0; j < carsObjects.size(); j++){
                JSONObject temp = carsObjects.get(j);
                carsArray.add(temp);
            }
            for(int j = 0; j < busesObjects.size(); j++){
                JSONObject temp = busesObjects.get(j);
                busesArray.add(temp);
            }
            for(int j = 0; j < trucksObjects.size(); j++){
                JSONObject temp = trucksObjects.get(j);
                trucksArray.add(temp);
            }
            for(int j = 0; j < motorsObjects.size(); j++){
                JSONObject temp = motorsObjects.get(j);
                motorsArray.add(temp);
            }
            garagesObjects.get(i).put("cars", carsArray);
            garagesObjects.get(i).put("buses", busesArray);
            garagesObjects.get(i).put("trucks", trucksArray);
            garagesObjects.get(i).put("motors", motorsArray);
        }
        for(int i = 0; i < garagesObjects.size(); i++){
            JSONObject temp = garagesObjects.get(i);
            garagesArray.add(temp);
        }
        mainObject.put("Garages", garagesArray);
        
        List<JSONObject> storesObjects = new ArrayList<>();
        JSONArray storesArray = new JSONArray();
        JSONArray storeGaragesArray = new JSONArray();
//        FileWriter file = new FileWriter(new File(path));
        for(int i = 0; i < ManagementSystem.stores.size(); i++){
            Store store = ManagementSystem.stores.get(i);
            storesObjects.add(new JSONObject());
            storesObjects.get(i).put("name", store.getStoreName());
            storesObjects.get(i).put("store_code", store.getStoreCode());
            List<JSONObject> storeGaragesObjects = new ArrayList<>();
            for(int j = 0; j < store.getGarages().size(); j++){
                Garage garage = store.getGarages().get(j);
                storeGaragesObjects.add(new JSONObject());
                storeGaragesObjects.get(j).put("code", garage.getGarageCode());
            }
            for(int j = 0; j < storeGaragesObjects.size(); j++){
                JSONObject temp;
                temp = storeGaragesObjects.get(j);
                storeGaragesArray.add(temp);
            }
            storesObjects.get(i).put("garages", storeGaragesArray);
        }
        for(int i = 0; i < storesObjects.size(); i++){
                JSONObject temp;
                temp = storesObjects.get(i);
                storesArray.add(temp);
        }

//        mainObject.put("Garages", garagesArray);
        mainObject.put("Stores", storesArray);
        file.write(mainObject.toJSONString());
        file.close();
    }
    public void writeIncomes(String path) throws IOException{
        FileWriter file = new FileWriter(new File(path));
        List<JSONObject> jObjects = new ArrayList<>();
        JSONArray carsArray = new JSONArray();
        JSONArray busesArray = new JSONArray();
        JSONArray trucksArray = new JSONArray();
        JSONArray motorsArray = new JSONArray();
        
        for(int  i = 0; i < ManagementSystem.carIncomes.size(); i++){
            Double temp = ManagementSystem.carIncomes.get(i);
            jObjects.add(new JSONObject());
            jObjects.get(i).put("income", temp);
        }
        for(int i = 0; i < jObjects.size(); i++){
            JSONObject temp = jObjects.get(i);
            carsArray.add(temp);
        }
        
        jObjects.clear();
        for(int  i = 0; i < ManagementSystem.truckIncomes.size(); i++){
            jObjects.add(new JSONObject());
            Double temp = ManagementSystem.truckIncomes.get(i);
            jObjects.get(i).put("income", temp);
        }
        for(int i = 0; i < jObjects.size(); i++){
            JSONObject temp = jObjects.get(i);
            trucksArray.add(temp);
        }
        
        jObjects.clear();
        for(int  i = 0; i < ManagementSystem.busIncomes.size(); i++){
            jObjects.add(new JSONObject());
            Double temp = ManagementSystem.busIncomes.get(i);
            jObjects.get(i).put("income", temp);
        }
        for(int i = 0; i < jObjects.size(); i++){
            JSONObject temp = jObjects.get(i);
            busesArray.add(temp);
        }
        jObjects.clear();
        for(int  i = 0; i < ManagementSystem.motorIncomes.size(); i++){
            jObjects.add(new JSONObject());
            Double temp = ManagementSystem.motorIncomes.get(i);
            jObjects.get(i).put("income", temp);
        }
        for(int i = 0; i < jObjects.size(); i++){
            JSONObject temp = jObjects.get(i);
            motorsArray.add(temp);
        }
        JSONObject temp = new JSONObject();
        temp.put("Cars_Income", carsArray);
        temp.put("Buses_Income", busesArray);
        temp.put("Trucks_Income", trucksArray);
        temp.put("Motors_Income", motorsArray);
        
        file.write(temp.toJSONString());
        file.close();
    }
    public void writeEmployees(String path) throws IOException{
        FileWriter file = new FileWriter(new File(path));
        List<JSONObject> jObjects = new ArrayList<>();
        JSONArray employees = new JSONArray();
        for(int i = 0; i < ManagementSystem.employees.size(); i++){
            Employee employee = ManagementSystem.employees.get(i);
            jObjects.add(new JSONObject());
            jObjects.get(i).put("name", employee.getName());
            jObjects.get(i).put("lastname", employee.getLastName());
            jObjects.get(i).put("age", employee.getAge());
            String gender;
            if(employee.isMale())
                gender = "Male";
            else
                gender = "Female";
            jObjects.get(i).put("gender", gender);
        }
        for(int i = 0; i < jObjects.size(); i++){
            JSONObject temp = jObjects.get(i);
            employees.add(temp);
        }
        JSONObject temp = new JSONObject();
        temp.put("Employees", employees);
        
        file.write(temp.toJSONString());
        file.close();
    }
}


