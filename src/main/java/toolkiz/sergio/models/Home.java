package toolkiz.sergio.models;

import java.util.Arrays;
import java.util.List;

public class Home {
    final String name;
    final String coordinate;
    final String owner;

    public Home(String name, String coordinate , String owner) {
        this.name = name;
        this.coordinate = coordinate;
        this.owner = owner;
    }

    public String[] getCoordinate() {
        return this.coordinate.split(" ");
    }
    public String getName() {
        return this.name;
    }
    public String getOwnerName() {
        return this.owner;
    }

    public double[] toDoubleArray() {
        double[] coorinates = new double[3];
        String[] splittedCoordinates = this.coordinate.split(" ");

        for(int i = 0; i < 3; i ++) {
            coorinates[i] = Double.parseDouble(splittedCoordinates[i]);
        }

        return coorinates;
    }
}
