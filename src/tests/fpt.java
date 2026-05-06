package tests;
import handlers.DynamicEquationParser;
import methods.FixedPoint;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import org.mariuszgromada.math.mxparser.mXparser.*;
import java.util.Scanner;
public class fpt {
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
        double x0;
        Expression x0Expr = new Expression(x0Input);
        x0 = x0Expr.calculate();
        if (Double.isNaN(x0)) {
            try {
                x0 = Double.parseDouble(x0Input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid x0 value. Defaulting to 0.");
                x0 = 0;
            }
        }
        String answer = FixedPoint.solve(itt,x0,equation);
        System.out.println(answer);
    }
}
