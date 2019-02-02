/**
 * This class demonstrates Tokimon attributes
 * Tokimon constructor
 * getters and a toString Override function.
 */
public class Tokimon {
    private String name;
    private double size;
    private String type;
    int strength=0;

    //Constructor
    Tokimon(String name, double size, String type){
        this.name=name;
        this.size=size;
        this.type=type;
        this.strength=0;
    }

    @Override
    public String toString() {
        return "Tokimon{" +
                "name='" + this.name + '\'' +
                ", size=" + this.size +
                ", type='" + this.type + '\'' +
                ", strength=" + this.strength +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

}
