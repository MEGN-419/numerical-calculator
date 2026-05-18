package tests;

import handlers.DynamicEquationParser;
import methods.phase3.NumericalIntegration;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class nintst {
    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the function to integrate f(x): ");
        String eqStr = sc.nextLine();
        Expression equation = DynamicEquationParser.EBX(eqStr);
        System.out.println("Enter lower bound (a): ");
        double a = DynamicEquationParser.EBX(sc.next()).calculate();
        System.out.println("Enter upper bound (b): ");
        double b = DynamicEquationParser.EBX(sc.next()).calculate();
        System.out.println("Enter number of subintervals (n - must be even for Simpson's): ");
        int n = sc.nextInt();
        String answer = NumericalIntegration.solve(equation, a, b, n);
        System.out.println("\n" + answer);
        sc.close();
    }
}