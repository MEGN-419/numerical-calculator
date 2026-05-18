package methods;

import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class ODE {

    public static String solve(Expression equation, double t0, double y0, double h, double tTarget) {
        if (h <= 0) return "Error: Step size (h) must be greater than 0.";
        if (tTarget <= t0) return "Error: Target time must be greater than initial time.";

        int steps = (int) Math.round((tTarget - t0) / h);

        StringBuilder result = new StringBuilder();
        result.append("=== Initial Value Problem (ODE) ===\n");
        result.append("Equation: dy/dt = f(t,y) = ").append(equation.getExpressionString()).append("\n");
        result.append(String.format("Initial Condition: y(%.2f) = %.4f\n", t0, y0));
        result.append(String.format("Step Size (h): %.4f | Target t: %.2f\n\n", h, tTarget));

        // Setup arrays for both methods
        double[] t = new double[steps + 1];
        double[] yEuler = new double[steps + 1];
        double[] yRK4 = new double[steps + 1];

        t[0] = t0;
        yEuler[0] = y0;
        yRK4[0] = y0;

        result.append(String.format("%-5s | %-8s | %-15s | %-15s\n", "Step", "t", "Euler y(t)", "RK4 y(t)"));
        result.append("----------------------------------------------------------\n");
        result.append(String.format("%-5d | %-8.4f | %-15.6f | %-15.6f\n", 0, t[0], yEuler[0], yRK4[0]));

        for (int i = 0; i < steps; i++) {
            t[i + 1] = t[i] + h;

            // --- Euler's Method ---
            // slope = f(t, y)
            equation.setArgumentValue("t", t[i]);
            equation.setArgumentValue("y", yEuler[i]);
            double slopeEuler = equation.calculate();

            // y_new = y_old + h * slope
            yEuler[i + 1] = yEuler[i] + h * slopeEuler;

            // --- Runge-Kutta 4 (RK4) Method ---
            // k1 = f(t, y)
            equation.setArgumentValue("t", t[i]);
            equation.setArgumentValue("y", yRK4[i]);
            double k1 = equation.calculate();

            // k2 = f(t + h/2, y + h*k1/2)
            equation.setArgumentValue("t", t[i] + h / 2.0);
            equation.setArgumentValue("y", yRK4[i] + h * k1 / 2.0);
            double k2 = equation.calculate();

            // k3 = f(t + h/2, y + h*k2/2)
            equation.setArgumentValue("t", t[i] + h / 2.0);
            equation.setArgumentValue("y", yRK4[i] + h * k2 / 2.0);
            double k3 = equation.calculate();

            // k4 = f(t + h, y + h*k3)
            equation.setArgumentValue("t", t[i] + h);
            equation.setArgumentValue("y", yRK4[i] + h * k3);
            double k4 = equation.calculate();

            // y_new = y_old + (h/6) * (k1 + 2k2 + 2k3 + k4)
            yRK4[i + 1] = yRK4[i] + (h / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);

            result.append(String.format("%-5d | %-8.4f | %-15.6f | %-15.6f\n",
                         (i + 1), t[i + 1], yEuler[i + 1], yRK4[i + 1]));
        }

        result.append("\n--- Final Results at t = ").append(tTarget).append(" ---\n");
        result.append(String.format("Euler's Method: y ≈ %.6f\n", yEuler[steps]));
        result.append(String.format("RK4 Method:     y ≈ %.6f\n", yRK4[steps]));

        return result.toString();
    }
}