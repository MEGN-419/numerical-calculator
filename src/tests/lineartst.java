package tests;

import methods.GaussSeidelMethod;
import methods.JacobiMethod;
import java.util.Scanner;
public class lineartst {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of variables (e.g., 3): ");
        int n = sc.nextInt();
        double[][] A = new double[n][n];
        double[] B = new double[n];
        double[] X0 = new double[n]; // Defaults to 0.0
        System.out.println("\nEnter the coefficients for matrix A and constants for vector B:");
        for (int i = 0; i < n; i++) {
            System.out.println("--- Equation " + (i + 1) + " ---");
            for (int j = 0; j < n; j++) {
                System.out.print("Coefficient for variable " + (j + 1) + ": ");
                A[i][j] = sc.nextDouble();
            }
            System.out.print("Constant (Result) for Equation " + (i + 1) + ": ");
            B[i] = sc.nextDouble();
        }
        System.out.print("\nEnter max iterations: ");
        int itt = sc.nextInt();
        System.out.println("\n" + JacobiMethod.solve(A, B, X0, itt));
        System.out.println("\n" + GaussSeidelMethod.solve(A, B, X0, itt));
        System.out.println("\n" + methods.GaussElimination.solve(A, B));
        sc.close();
    }
}