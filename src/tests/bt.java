package tests;

import handlers.DynamicEquationParser;
import methods.Bisection;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import org.mariuszgromada.math.mxparser.mXparser.*;
import java.util.Scanner;


public class bt {
    static void main() {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the equation: ");
        String steq = sc.nextLine();
        Expression expr = new Expression(steq);
        Expression equation = DynamicEquationParser.EBX(steq);
        System.out.println("enter itt number: ");
        int itt = sc.nextInt();
        System.out.println("enter a value: ");
        double aInput = sc.nextDouble();
        System.out.println("enter b value: ");
        double bInput = sc.nextDouble();
        String answer = Bisection.solve(equation,aInput,bInput,itt);
        System.out.println(answer);
    }
}
