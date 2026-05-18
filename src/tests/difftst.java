package tests;

import methods.phase3.NumericalDifferentiation;
import java.util.Scanner;

public class difftst {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of data points (n): ");
        int n = sc.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        System.out.println("\nEnter your data points (ensure spacing 'h' is uniform):");
        for (int i = 0; i < n; i++) {
            System.out.print("X[" + i + "]: ");
            x[i] = sc.nextDouble();
            System.out.print("Y[" + i + "]: ");
            y[i] = sc.nextDouble();
        }
        String answer = NumericalDifferentiation.solve(x, y);
        System.out.println("\n" + answer);
        sc.close();
    }
}