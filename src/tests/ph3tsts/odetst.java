package tests.ph3tsts;

import methods.phase3.ODE;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;
import java.util.Scanner;

public class odetst {
    public static void main(String[] args) {
        License.iConfirmNonCommercialUse("John Doe");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the differential equation dy/dt = f(t,y): ");
        String eqStr = sc.nextLine();

        // Setup mXparser for 't' and 'y'
        Argument tArg = new Argument("t");
        Argument yArg = new Argument("y");
        Expression equation = new Expression(eqStr, tArg, yArg);

        System.out.println("Enter initial time (t0): ");
        double t0 = sc.nextDouble();

        System.out.println("Enter initial condition (y0): ");
        double y0 = sc.nextDouble();

        System.out.println("Enter step size (h): ");
        double h = sc.nextDouble();

        System.out.println("Enter target time to calculate (t_target): ");
        double tTarget = sc.nextDouble();

        String answer = ODE.solve(equation, t0, y0, h, tTarget);
        System.out.println("\n" + answer);

        sc.close();
    }
}