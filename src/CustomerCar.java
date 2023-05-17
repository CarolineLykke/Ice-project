public class CustomerCar {

    private String customerId;
    private String forName;
    private String lastName;
    private String carID;
    private String carMake;
    private String carModel;
    private String carRegnr;
    private String carKm;

    public CustomerCar(String customerId, String forName, String lastName, String carID, String carMake, String carModel, String carRegnr, String carKm) {
        this.customerId = customerId;
        this.forName = forName;
        this.lastName = lastName;
        this.carID = carID;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carRegnr = carRegnr;
        this.carKm = carKm;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getForName() {
        return forName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCarID() {
        return carID;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarRegnr() {
        return carRegnr;
    }

    public String getCarKm() {
        return carKm;
    }
}
