package management;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import vehicle.Bus;
import vehicle.Car;
import vehicle.Motor;
import vehicle.Truck;
import vehicle.Vehicle;

/**
 *
 * @author Nikam
 */
public class ReadJSON {
    
    Object object;
    JSONParser parser;
    
    public void readGarages(String path) throws FileNotFoundException, IOException, ParseException{
        ManagementSystem.vehicles.clear();
        object = parser.parse(new FileReader(path));
        JSONObject jSONObject = new JSONObject((Map) object); 
        
        JSONArray garages = (JSONArray)jSONObject.get("Garages");
        for(int i = 0; i < garages.size(); i++){
            jSONObject = (JSONObject)garages.get(i);
            int code = Integer.parseInt(jSONObject.get("code").toString());
            Garage garage = new Garage(code);
//            System.out.println("code;;;" + garage.getGarageCode());
            
            JSONArray vehicles = (JSONArray)jSONObject.get("cars");
            for(int j = 0; j < vehicles.size(); j++){
                JSONObject temp = (JSONObject)vehicles.get(j);
                Car car = new Car(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("price").toString()), code);
                car.setMode(temp.get("mode").toString());
                
                garage.getVehicles().add((Vehicle)car);
                ManagementSystem.vehicles.add(car);
                
            }
            
            vehicles = (JSONArray)jSONObject.get("buses");
            for(int j = 0; j < vehicles.size(); j++){
                JSONObject temp = (JSONObject)vehicles.get(j);
                Bus bus = new Bus(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("price").toString()), code);
//                System.out.println("bus: " + bus.getGarage().getGarageCode());
                garage.getVehicles().add(bus);
                ManagementSystem.vehicles.add(bus);
            }
            
            vehicles = (JSONArray)jSONObject.get("trucks");
            for(int j = 0; j < vehicles.size(); j++){
                JSONObject temp = (JSONObject)vehicles.get(j);
                Truck truck = new Truck(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("price").toString()), code);
                garage.getVehicles().add(truck);
                ManagementSystem.vehicles.add(truck);
            }
            
            vehicles = (JSONArray)jSONObject.get("motors");
            for(int j = 0; j < vehicles.size(); j++){
                JSONObject temp = (JSONObject)vehicles.get(j);
                Motor motor = new Motor(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("price").toString()), code);
                garage.getVehicles().add(motor);
                ManagementSystem.vehicles.add(motor);
//                System.out.println("motor: " + motor.getGarage().getGarageCode());
            }
            ManagementSystem.garages.add(garage);
//            System.out.println(garage.getGarageCode() + "   " + "count " + garage.getVehicles().size());
        }
        
    }
    public void readIncomes(String path) throws FileNotFoundException, IOException, ParseException{
        object = parser.parse(new FileReader(new File(path)));
        JSONObject jSONObject = new JSONObject((Map) object); 
        
        JSONArray carIncomes = (JSONArray)jSONObject.get("Cars_Income");
        for(int i = 0; i < carIncomes.size(); i++){
            JSONObject temp = (JSONObject)carIncomes.get(i);
            ManagementSystem.carIncomes.add(Double.parseDouble(temp.get("income").toString()));
        }
        JSONArray busIncomes = (JSONArray)jSONObject.get("Buses_Income");
        for(int i = 0; i < busIncomes.size(); i++){
            JSONObject temp = (JSONObject)busIncomes.get(i);
            ManagementSystem.busIncomes.add(Double.parseDouble(temp.get("income").toString()));
        }
        JSONArray truckIncomes = (JSONArray)jSONObject.get("Trucks_Income");
        for(int i = 0; i < truckIncomes.size(); i++){
            JSONObject temp = (JSONObject)truckIncomes.get(i);
            ManagementSystem.truckIncomes.add(Double.parseDouble(temp.get("income").toString()));
        }
        JSONArray motorIncomes = (JSONArray)jSONObject.get("Motors_Income");
        for(int i = 0; i < motorIncomes.size(); i++){
            JSONObject temp = (JSONObject)motorIncomes.get(i);
            ManagementSystem.motorIncomes.add(Double.parseDouble(temp.get("income").toString()));
        }
    }
    
    public void readEmployees(String path) throws FileNotFoundException, IOException, ParseException{
        object = parser.parse(new FileReader(new File(path)));
        JSONObject jSONObject = new JSONObject((Map) object); 
        JSONArray employees = (JSONArray)jSONObject.get("Employees");
        for(int i = 0; i < employees.size(); i++){
            JSONObject temp = (JSONObject)employees.get(i);
            boolean gender;
            if(temp.get("gender").toString().equalsIgnoreCase("female"))
                gender = true;
            else 
                gender = false;
            Employee employee = new Employee(temp.get("name").toString(), temp.get("lastname").toString(), Integer.parseInt(temp.get("age").toString()), gender);
            ManagementSystem.employees.add(employee);
        }
    }
    
    public void readStores(String path) throws FileNotFoundException, IOException, ParseException{
        object = parser.parse(new FileReader(path));
        JSONObject jSONObject = new JSONObject((Map) object);
        JSONArray stores = (JSONArray)jSONObject.get("Stores");
        for(int i = 0; i < stores.size(); i++){
            JSONObject temp = (JSONObject)stores.get(i);
            Store store = new Store(temp.get("name").toString(), Integer.parseInt(temp.get("store_code").toString()));
            JSONArray storeGarages = (JSONArray)temp.get("garages");

            for(int j = 0; j < storeGarages.size(); j++){
                JSONObject temp2 = (JSONObject)storeGarages.get(j);
                Garage storeGarage = ManagementSystem.findGarageByCode(Integer.parseInt(temp2.get("code").toString()));
                store.getGarages().add(storeGarage);
            }
            ManagementSystem.stores.add(store);
        }   
    }
    
    public void readBorrowed(String path) throws java.text.ParseException ,FileNotFoundException, IOException, ParseException{
        object = parser.parse(new FileReader(path));
        JSONObject jSONObject = new JSONObject((Map) object);
        JSONArray borrows = (JSONArray)jSONObject.get("Borrowed");
        for(int i = 0; i < borrows.size(); i++){
            JSONObject temp = (JSONObject)borrows.get(i);
            String renter = temp.get("renter").toString();
            Date startDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(temp.get("startDate").toString());
            
            Date endDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").parse(temp.get("endDate").toString());
            temp = (JSONObject)temp.get("vehicle");
            if(temp.get("mode").toString().split(" ")[0].equalsIgnoreCase("car")){
                Car car = new Car(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("total-price").toString()), Integer.parseInt(temp.get("code").toString()));
                car.setMode(temp.get("mode").toString().split(" ")[1]);
                BorrowedVehicles borrowedVehicle = new BorrowedVehicles(car, car.getPrice(), renter, startDate, endDate);
                ManagementSystem.carsInRent.add(borrowedVehicle);
            }
            else if(temp.get("mode").toString().split(" ")[0].equalsIgnoreCase("bus")){
                Bus bus = new Bus(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("total-price").toString()), Integer.parseInt(temp.get("code").toString()));
                BorrowedVehicles borrowedVehicle = new BorrowedVehicles(bus, bus.getPrice(), renter, startDate, endDate);
                ManagementSystem.carsInRent.add(borrowedVehicle);
            }
            else if(temp.get("mode").toString().split(" ")[0].equalsIgnoreCase("truck")){
                Truck truck = new Truck(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("total-price").toString()), Integer.parseInt(temp.get("code").toString()));
                BorrowedVehicles borrowedVehicle = new BorrowedVehicles(truck, truck.getPrice(), renter, startDate, endDate);
                ManagementSystem.carsInRent.add(borrowedVehicle);
            }
            else if(temp.get("mode").toString().split(" ")[0].equalsIgnoreCase("motor")){
                Motor motor = new Motor(temp.get("company").toString(), temp.get("name").toString(), Integer.parseInt(temp.get("year").toString()), Integer.parseInt(temp.get("passengers").toString()), temp.get("info").toString(), Double.parseDouble(temp.get("total-price").toString()), Integer.parseInt(temp.get("code").toString()));
                BorrowedVehicles borrowedVehicle = new BorrowedVehicles(motor, motor.getPrice(), renter, startDate, endDate);
                ManagementSystem.carsInRent.add(borrowedVehicle);
            }
        }   
    }
    
    public ReadJSON() throws FileNotFoundException, IOException, ParseException
    {
        
        parser = new JSONParser();
        
    }
    
}
