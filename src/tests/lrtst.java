package tests;

import methods.LinearRegression;
import java.util.Scanner;

public class lrtst {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of data points (n): ");
        int n = sc.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        System.out.println("\nEnter your data points:");
        for (int i = 0; i < n; i++) {
            System.out.print("X[" + (i + 1) + "]: ");
            x[i] = sc.nextDouble();
            System.out.print("Y[" + (i + 1) + "]: ");
            y[i] = sc.nextDouble();
        }
        String answer = LinearRegression.solve(x, y);
        System.out.println("\n" + answer);
        System.out.println("Do you want to estimate a Y value for a specific X? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("Enter X to estimate: ");
            double xEst = sc.nextDouble();
            double sumX = 0, sumY = 0, sumX2 = 0, sumXY = 0;
            for (int i = 0; i < n; i++) {
                sumX += x[i]; sumY += y[i];
                sumX2 += x[i]*x[i]; sumXY += x[i]*y[i];
            }
            double b = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
            double a = (sumY - b * sumX) / n;
            double yEst = a + b * xEst;
            System.out.printf("Estimated y(%.4f) = %.4f\n", xEst, yEst);
        }
        sc.close();
    }
}