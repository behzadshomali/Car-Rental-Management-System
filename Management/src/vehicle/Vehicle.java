package vehicle;

import management.Garage;
import management.ManagementSystem;

public class Vehicle 
{
    private String modelName;
    private String manufacturer;
    private int year;
    private String information;
    private int passengersCount;
    private boolean isFixed;
    private static int code = 1003;
    private double basePrice;
    private int garageCode;

    public int getGarageCode() {
        return garageCode;
    }

//    public void setGarageCode(int garageCode) {
//        this.garageCode = garageCode;
//    }

//    public Garage getGarage() {
//        return garage;
//    }

//    public void setGarage(int garageCode) {
//        this.garage =  ManagementSystem.findGarageByCode(garageCode);
////        System.out.println("found " + this.garage.getGarageCode());
//    }

    public  double getPrice(){
        return this.basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        if(basePrice > 0)
            this.basePrice = basePrice;
        else
            this.basePrice = -1;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = makeFirstUpper(modelName);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = makeFirstUpper(manufacturer);
    }

    public int getManufactureYear() {
        return year;
    }

    public void setManufactureYear(int manufactureYear) {
        if(manufactureYear > 1950)
            this.year = manufactureYear;
        else
            this.year = -1; //means that we don't know the manufacture year
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        if(passengersCount > 0)
            this.passengersCount = passengersCount;
        else
            this.passengersCount = -1;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public boolean isFixed(){
        return this.isFixed;
    }
    
    public void setFixed(boolean fixed){
        this.isFixed = fixed;
    }
    
    public Vehicle(String manufacturer, String model, int year, int passengersCount, String info, double basePrice, int garageCode){
        this.setManufacturer(manufacturer);
        this.setModelName(model);
        this.setManufactureYear(year);
        this.setPassengersCount(passengersCount);
        this.setInformation(info);
        this.isFixed = true;
        this.code += 154;
        this.garageCode = garageCode;
    }
    
    
    
    public String makeFirstUpper(String input)
    {
        input = input.substring(0, 1).toUpperCase() + input.substring(1);
        return input;
    }
}