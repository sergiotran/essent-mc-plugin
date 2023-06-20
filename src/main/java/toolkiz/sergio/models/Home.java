package toolkiz.sergio.models;

import java.util.Arrays;
import java.util.List;

public class Home {
    final String name;
    final String coordinate;
    final String owner;

    public Home(String name,double x, double y , double z, String owner) {
        List<String> list = Arrays.asList(Double.toString(x), Double.toString(y), Double.toString(z));

        this.name = name;
        this.coordinate = String.join(" ", list);
        this.owner = owner;
    }

    public String[] getCoordinate() {
        return this.coordinate.split(" ");
    }
}
