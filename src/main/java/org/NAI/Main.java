package org.NAI;
import java.util.List;
import java.util.Scanner;
import static org.NAI.KNNClassifier.kNNForSingleVector;
import static org.NAI.KNNClassifier.kNNWithDifferentK;

public class Main {
    public static void main(String[] args) throws Exception {
        String csvFile = "C:\\Users\\lczer\\OneDrive\\Pulpit\\School\\NaiProject\\iris.csv";
        String csvTestFile = "C:\\Users\\lczer\\OneDrive\\Pulpit\\School\\NaiProject\\iris.test.csv";
        String csvFile2 = "C:\\Users\\lczer\\OneDrive\\Pulpit\\School\\NaiProject\\wdbc.csv";
        String csvTestFile2 = "C:\\Users\\lczer\\OneDrive\\Pulpit\\School\\NaiProject\\wdbc.test1.csv";
        List<Iris> result = KNNClassifier.kNN(3,csvFile2,csvTestFile2,"resultFile.csv");
        for (Iris iris : result) {
            System.out.println(iris);
        }
        kNNWithDifferentK(csvFile,csvTestFile,"accuracy.csv");
/*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value 1: ");
        double feature1 = scanner.nextDouble();
        System.out.print("Enter the value 2: ");
        double feature2 = scanner.nextDouble();
        System.out.print("Enter the value 3: ");
        double feature3 = scanner.nextDouble();
        System.out.print("Enter the value 4: ");
        double feature4 = scanner.nextDouble();

        double[] inputVector = {feature1, feature2, feature3, feature4};
        String predictedClass = kNNForSingleVector(3, csvFile, inputVector);
        System.out.println("The predicted class for the input vector is: " + predictedClass);


 */
    }

}