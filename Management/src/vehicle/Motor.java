package vehicle;

public class Motor extends Vehicle{
    private double basePrice;

    @Override
    public double getPrice() {
        return this.basePrice;
    }

    public void setBasePrice(double basePrice) {
        if(basePrice > 0)
            this.basePrice = basePrice;
        else
            this.basePrice = -1;
    }
    
    public Motor(String manufacturer, String model, int year, int passengersCount, String info, double basePrice, int garageCode){
        super(manufacturer, model, year, passengersCount, info, basePrice, garageCode);
        this.setBasePrice(basePrice);
    }
            
}
