public class Car {
    //private int id;
    private String make;
    private String model;
    private String regnr;
    private int km;

    public Car(String make, String model, String regnr, int km) {
        //this.id = id;
        this.make = make;
        this.model = model;
        this.regnr = regnr;
        this.km = km;
    }

    /*public int getId() {
        return id;
    }*/

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRegnr() {
        return regnr;
    }

    public int getKm() {
        return km;
    }
}
