package tests;

import handlers.DynamicEquationParser;
import methods.RombergIntegration;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class rombergtst {
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

        System.out.println("Enter the number of Romberg rows to generate (e.g., 4 or 5): ");
        int maxRows = sc.nextInt();

        String answer = RombergIntegration.solve(equation, a, b, maxRows);
        System.out.println("\n" + answer);

        sc.close();
    }
}