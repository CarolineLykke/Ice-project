public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private int km;

    public Car(int id, String make, String model, int year, int km) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.km = km;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getKm() {
        return km;
    }
}
