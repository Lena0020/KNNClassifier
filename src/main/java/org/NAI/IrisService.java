package org.NAI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IrisService {
    public static List<Iris> loadIris(String filePath) throws IOException {
        List<Iris> irises = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            double[] vector = new double[values.length - 1];
            String name = null;
            for (int i = 0; i < values.length; i++) {
                if (i < values.length - 1) {
                    vector[i] = Double.parseDouble(values[i]);
                } else {
                    try {
                        Double.parseDouble(values[i]);
                        vector = Arrays.copyOf(vector, vector.length + 1);
                        vector[vector.length - 1] = Double.parseDouble(values[i]);
                    } catch (NumberFormatException e) {
                        name = values[i];
                    }
                }
            }
            Iris iris = new Iris(vector, name);
            irises.add(iris);
        }
        br.close();

        return irises;
    }

}


