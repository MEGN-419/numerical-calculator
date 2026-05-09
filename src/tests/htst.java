package tests;

import handlers.DynamicEquationParser;
import methods.HalleyMethod;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class htst {
    static void main() {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the equation f(x): ");
        String eqStr = sc.nextLine();
        Expression equation = DynamicEquationParser.EBX(eqStr);
        Expression derivative1 = DynamicEquationParser.derive(eqStr);
        Expression derivative2 = DynamicEquationParser.deriveSecond(eqStr);
        System.out.println("Enter max iterations: ");
        int itt = sc.nextInt();
        System.out.println("Enter initial x0 value: ");
        double x0 = DynamicEquationParser.EBX(sc.next()).calculate();
        String answer = HalleyMethod.solve(equation, derivative1, derivative2, x0, itt);
        System.out.println(answer);
    }
}