package tests;

import methods.NonlinearSystem;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class nlstst {
    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of variables/equations (e.g., 2 or 3): ");
        int n = sc.nextInt();
        sc.nextLine();
        String[] allVars = {"x1", "x2", "x3", "x4", "x5"};
        String[] activeVars = new String[n];
        System.arraycopy(allVars, 0, activeVars, 0, n);
        Expression[] equations = new Expression[n];
        double[] X0 = new double[n];
        System.out.println("\nEnsure your equations use variables like x1, x2, x3.");
        for (int i = 0; i < n; i++) {
            System.out.println("Enter equation f" + (i + 1) + "(" + String.join(",", activeVars) + "): ");
            equations[i] = new Expression(sc.nextLine());
        }
        System.out.println("\nEnter your initial guesses:");
        for (int i = 0; i < n; i++) {
            System.out.println("Initial guess for " + activeVars[i] + ": ");
            X0[i] = sc.nextDouble();
        }
        System.out.println("Enter max iterations: ");
        int itt = sc.nextInt();
        String answer = NonlinearSystem.solve(equations, activeVars, X0, itt);
        System.out.println("\n" + answer);

        sc.close();
    }
}