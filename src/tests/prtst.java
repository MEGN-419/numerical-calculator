package tests;

import methods.PolynomialRegression;
import java.util.Scanner;

public class prtst {
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
        String answer = PolynomialRegression.solve(x, y);
        System.out.println("\n" + answer);
        //System.out.println("Do you want to estimate a Y value for a specific X? (y/n): ");
       /* if (sc.next().equalsIgnoreCase("y")) {
            System.out.print("Enter X to estimate: ");
            double xEst = sc.nextDouble();
            double sumX = 0, sumY = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumXY = 0, sumX2Y = 0;
            for (int i = 0; i < n; i++) {
                double x2 = x[i]*x[i];
                sumX += x[i]; sumY += y[i]; sumX2 += x2;
                sumX3 += x2*x[i]; sumX4 += x2*x2;
                sumXY += x[i]*y[i]; sumX2Y += x2*y[i];
            }
            System.out.println("\nRefer to the equation above and plug in X = " + xEst + " manually.");
        }*/
        sc.close();
    }
}