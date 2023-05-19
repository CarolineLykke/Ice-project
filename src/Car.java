public class Car {
    //private int id;
    private String make;
    private String model;
    private String regnr;
    private int km;
    private int id;

    public Car(String make, String model, String regnr, int km,int id) {
        //this.id = id;
        this.make = make;
        this.model = model;
        this.regnr = regnr;
        this.km = km;
        this.id=id;
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
    public int getid() {
        return id;
    }
}
