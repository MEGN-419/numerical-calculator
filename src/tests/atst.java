package tests;

import handlers.DynamicEquationParser;
import methods.Aitken;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class atst {
    static void main() {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the fixed-point equation g(x): ");
        String eqStr = sc.nextLine();
        Expression equation = DynamicEquationParser.EBX(eqStr);
        System.out.println("Enter max iterations: ");
        int itt = sc.nextInt();
        System.out.println("Enter initial x0 value: ");
        double x0 = DynamicEquationParser.EBX(sc.next()).calculate();
        String answer = Aitken.solve(equation, x0, itt);
        System.out.println(answer);
    }
}