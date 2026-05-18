package tests.ph3tsts;

import methods.phase3.NewtonDividedDifference;
import java.util.Scanner;

public class newtontst {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of data points (n): ");
        int n = sc.nextInt();

        double[] x = new double[n];
        double[] y = new double[n];

        System.out.println("\nEnter your data points:");
        for (int i = 0; i < n; i++) {
            System.out.print("X[" + i + "]: ");
            x[i] = sc.nextDouble();
            System.out.print("Y[" + i + "]: ");
            y[i] = sc.nextDouble();
        }

        System.out.println("\nEnter the X value you want to estimate: ");
        double xp = sc.nextDouble();

        String answer = NewtonDividedDifference.solve(x, y, xp);
        System.out.println("\n" + answer);

        sc.close();
    }
}