package tests;

import methods.ExponentialRegression;
import java.util.Scanner;

public class exprtst {
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
        String answer = ExponentialRegression.solve(x, y);
        System.out.println("\n" + answer);
        sc.close();
    }
}