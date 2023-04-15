package org.NAI;
import java.util.Arrays;

class Iris {
    private double[] vector;
    private String name;
    private double distance;

    public Iris(double[] vector, String name) {
        this.vector = vector;
        this.name = name;
    }

    public double[] getVector() {
        return vector;
    }

    public String getName() {
        return name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "vector=" + Arrays.toString(vector) +
                ", name='" + name + '\'' +
                '}';
    }
}
