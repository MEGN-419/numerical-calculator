package tests;

import handlers.DynamicEquationParser;
import methods.Secant;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.util.Scanner;


public class st {
    static void main() {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the equation: ");
        String steq = sc.nextLine();
        Expression expr = new Expression(steq);
        Expression equation = DynamicEquationParser.EBX(steq);
        System.out.println("enter itt number: ");
        int itt = sc.nextInt();
        System.out.println("enter x0 value: ");
        String x0Input = sc.next();
        double x0 = DynamicEquationParser.EBX(x0Input).calculate();
        System.out.println("enter x1 value: ");
        String x1Input = sc.next();
        double x1 = DynamicEquationParser.EBX(x1Input).calculate();
        String answer = Secant.solve(equation,itt, x0, x1);
        System.out.println(answer);
    }
}
