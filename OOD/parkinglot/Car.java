package OOD.parkinglot;

/**
 * Created by az on 4/19/2020.
 */
public class Car extends Vehicle {
    //M1: constructor
    /*
    public Car() {
        super(VehicleSize.Compact);
    }
    */

    //M2: setter
    /*
    @Override
    public void setter(VehicleSize size) {
        super.setter(size);
    }
    */

    //M3: no constructor or setter, parent class no size define
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}
