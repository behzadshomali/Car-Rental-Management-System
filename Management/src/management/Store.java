package management;

import java.util.ArrayList;
import java.util.List;


public class Store {
    private String storeName;
    private int storeCode;
    
    
    private List<Garage> garages = new ArrayList<>();

    public List<Garage> getGarages() {
        return garages;
    }

//    public void setGarages(List<Garage> garages) {
//        this.garages = garages;
//    }
    
    public Store(String storeName, int storeCode) {
        this.storeName = storeName;
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }
}
