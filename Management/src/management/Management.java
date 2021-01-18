package management;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;

import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import vehicle.Car;

public class Management extends Application {
    
    @Override
    public void start(Stage primaryStage) 
    {
        Gui gui = new Gui(primaryStage);
        ManagementSystem.timePasseing(primaryStage);
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException, java.text.ParseException {
        ReadJSON myJson = new ReadJSON();
        myJson.readGarages("src/vehicles.JSON");
        myJson.readStores("src/vehicles.JSON");
        myJson.readBorrowed("src/sales.JSON");
        myJson.readIncomes("src/incomes.JSON");
        myJson.readEmployees("src/employees.JSON");
        
//        
//        for(int i = 0 ; i < ManagementSystem.vehicles.size(); i++){
//            System.out.println((ManagementSystem.vehicles.get(i)).getGarage().getGarageCode());
//            System.out.println("Mode-> " + ((Car)ManagementSystem.vehicles.get(0)).getMode());
//        }
        launch(args);
    }
    
}
