package OOD.parkinglot;

/**
 * Created by az on 4/18/2020.
 */
public enum VehicleSize {
    Compact(1),
    Regular(2),
    Large(3);

    private final int size;

    VehicleSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
