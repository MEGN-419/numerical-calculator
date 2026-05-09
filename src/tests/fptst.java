package tests;

import handlers.DynamicEquationParser;
import methods.FalsePosition;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class fptst {
    static void main() {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the equation: ");
        String steq = sc.nextLine();
        Expression equation = DynamicEquationParser.EBX(steq);
        System.out.println("enter itt number: ");
        int itt = sc.nextInt();
        System.out.println("enter a value: ");
        double a = DynamicEquationParser.EBX(sc.next()).calculate();
        System.out.println("enter b value: ");
        double b = DynamicEquationParser.EBX(sc.next()).calculate();
        String answer = FalsePosition.solve(equation, a, b, itt);
        System.out.println(answer);
    }
}