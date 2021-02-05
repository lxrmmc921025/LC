package OOD.parkinglot;

/**
 * Created by az on 4/19/2020.
 */
public abstract class Vehicle {
    //M1 : create size & constructor
    /*
    private VehicleSize size;
    Vehicle(VehicleSize size) {
        this.size = size;
    }
    public VehicleSize getSize(){
        return size;
    };
    */

    //M2 : create size & setter, if use setter, cannot use keyword "final"
    /*
    private VehicleSize size;
    public void setter(VehicleSize size) {
        this.size = size;
    }
    */

    //M3: no size constructor and setter, only use abstract class getSize
    public abstract VehicleSize getSize();
}
