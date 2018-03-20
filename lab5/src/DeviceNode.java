/**
 *
 * @author TimoshenkoStanislav
 */
public class DeviceNode  {
    String type, brand, model, price;

    DeviceNode() {}

    DeviceNode(String type, String brand, String model, String price) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

     public String getType() {
        return type;
    }

    public String getBrand() { 
        return brand; 
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }
}