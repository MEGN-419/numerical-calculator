package tests.ph3tsts;

import handlers.DynamicEquationParser;
import methods.phase3.DoubleIntegration;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class dintst {
    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the 3D function to integrate f(x,y): ");
        String eqStr = sc.nextLine();
        Expression equation = DynamicEquationParser.EBXY(eqStr);
        System.out.println("--- X-Axis ---");
        System.out.println("Enter lower bound (ax): ");
        double ax = sc.nextDouble();
        System.out.println("Enter upper bound (bx): ");
        double bx = sc.nextDouble();
        System.out.println("Enter subintervals for X (nx - must be even): ");
        int nx = sc.nextInt();
        System.out.println("--- Y-Axis ---");
        System.out.println("Enter lower bound (cy): ");
        double cy = sc.nextDouble();
        System.out.println("Enter upper bound (dy): ");
        double dy = sc.nextDouble();
        System.out.println("Enter subintervals for Y (ny - must be even): ");
        int ny = sc.nextInt();
        String answer = DoubleIntegration.solve(equation, ax, bx, nx, cy, dy, ny);
        System.out.println("\n" + answer);

        sc.close();
    }
}