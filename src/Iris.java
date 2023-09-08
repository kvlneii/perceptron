import java.util.Arrays;

public class Iris {
    private double[] irisAttributes;
    private String name;

    public Iris(double[] irisAttributes, String name) {
        this.irisAttributes = irisAttributes;
        this.name = name;
    }

    public double[] getIrisAttributes() {
        return irisAttributes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Iris [" + Arrays.toString(irisAttributes) + "]";
    }
}
