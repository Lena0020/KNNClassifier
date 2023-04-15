package org.NAI;

import java.io.FileWriter;
import java.util.*;

public class KNNClassifier {

    public static List<Iris> kNN(int k, String csvFile, String csvTestFile, String outputFilePath) throws Exception {
        List<Iris> result = new ArrayList<>();
        List<Iris> trainingSet = IrisService.loadIris(csvFile);
        List<Iris> testSet = IrisService.loadIris(csvTestFile);
        int correctPredictions = 0;
        for (Iris testIris : testSet) {
            for (Iris trainIris : trainingSet) {
                double distance = 0;
                for (int i = 0; i < testIris.getVector().length; i++) {
                    double diff = testIris.getVector()[i] - trainIris.getVector()[i];
                    distance += diff * diff;
                }
                trainIris.setDistance(Math.sqrt(distance));
            }
            trainingSet.sort(Comparator.comparingDouble(Iris::getDistance));
            String predictedClass = getPredictedClass(k, trainingSet);
            result.add(new Iris(testIris.getVector(), predictedClass));
            if (predictedClass.equals(testIris.getName())) {
                correctPredictions++;
            }
        }
        double accuracy = (double) correctPredictions / testSet.size();
        System.out.println("Accuracy: " + (accuracy * 100) + "%");

        FileWriter csvWriter = new FileWriter(outputFilePath);

        for (Iris iris : result) {
            double[] vector = iris.getVector();
            String name = iris.getName();
            StringBuilder stringBuilder = new StringBuilder();
            for (double d : vector) {
                stringBuilder.append(d).append(",");
            }
            stringBuilder.append(name);
            csvWriter.append(stringBuilder.toString());
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
        return result;
    }


    private static String getPredictedClass(int k, List<Iris> trainingSet) {
        Map<String, Integer> classCount = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Iris iris = trainingSet.get(i);
            String className = iris.getName();
            classCount.put(className, classCount.getOrDefault(className, 0) + 1);
        }
        int maxCount = -1;
        String predictedClass = null;
        for (Map.Entry<String, Integer> entry : classCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                predictedClass = entry.getKey();
            }
        }
        return predictedClass;
    }

    public static String kNNForSingleVector(int k, String csvFile, double[] vector) throws Exception {
        List<Iris> trainingSet = IrisService.loadIris(csvFile);
        for (Iris trainIris : trainingSet) {
            double distance = 0;
            for (int i = 0; i < vector.length; i++) {
                double diff = vector[i] - trainIris.getVector()[i];
                distance += diff * diff;
            }
            trainIris.setDistance(Math.sqrt(distance));
        }
        trainingSet.sort(Comparator.comparingDouble(Iris::getDistance));

        return getPredictedClass(k, trainingSet);
    }
    public static void kNNWithDifferentK(String csvFile, String csvTestFile, String outputFilePath) throws Exception {
        List<Iris> testSet = IrisService.loadIris(csvTestFile);
        FileWriter csvWriter = new FileWriter(outputFilePath);

        for (int k = 1; k <= 10; k++) {
            int correctPredictions = 0;
            for (Iris testIris : testSet) {
                double[] vector = testIris.getVector();
                String predictedClass = kNNForSingleVector(k, csvFile, vector);
                if (predictedClass.equals(testIris.getName())) {
                    correctPredictions++;
                }
            }
            double accuracy = (double) correctPredictions / testSet.size();
            String line = k + "," + accuracy + "\n";
            csvWriter.append(line);
        }

        csvWriter.flush();
        csvWriter.close();
    }

}
