package vehicle;

import management.Garage;

public class Car extends Vehicle implements CarModes{
    private String mode;
    
//    @Override
//    public double getPrice() {
//      return this.basePrice;
//    }
    
    
    
    public Car(String manufacturer, String model, int year, int passengersCount, String info, double basePrice, int garageCode){
        super(manufacturer, model, year, passengersCount, info, basePrice, garageCode);
        this.setBasePrice(basePrice);
        
    }
    

    
//    @Override
    public void setMode(String mode) {
        
        if(mode.equalsIgnoreCase("sport"))
            this.mode = "Sport";
        else if(mode.equalsIgnoreCase("economic"))
            this.mode = "Economic";
        else
            this.mode = "Luxury";
    }

//    @Override
    public String getMode() {
        return this.mode;
    }
}
