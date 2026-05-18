package methods.phase3;

import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class NumericalIntegration {

    public static String solve(Expression equation, double a, double b, int n) {
        if (n <= 0) return "Error: Number of subintervals (n) must be greater than 0.";
        double h = (b - a) / n;
        double[] x = new double[n + 1];
        double[] y = new double[n + 1];
        StringBuilder result = new StringBuilder();
        result.append("=== Numerical Integration ===\n");
        result.append("Function: ").append(equation.getExpressionString()).append("\n");
        result.append(String.format("Bounds: [%.4f, %.4f]\n", a, b));
        result.append("Subintervals (n): ").append(n).append("\n");
        result.append(String.format("Step size (h): %.6f\n\n", h));
        result.append(String.format("%-5s | %-12s | %-12s\n", "i", "x_i", "f(x_i)"));
        result.append("----------------------------------\n");
        for (int i = 0; i <= n; i++) {
            x[i] = a + i * h;
            DynamicEquationParser.updateX(equation, x[i]);
            y[i] = equation.calculate();
            result.append(String.format("%-5d | %-12.6f | %-12.6f\n", i, x[i], y[i]));
        }
        result.append("\n");
        double trapSum = y[0] + y[n];
        for (int i = 1; i < n; i++) {
            trapSum += 2 * y[i];
        }
        double trapResult = (h / 2.0) * trapSum;
        result.append("--- Trapezoidal Rule ---\n");
        result.append(String.format("Formula: (h/2) * [y0 + yn + 2*(y1 + ... + yn-1)]\n"));
        result.append(String.format("Area ≈ %.9f\n\n", trapResult));
        result.append("--- Simpson's 1/3 Rule ---\n");
        if (n % 2 != 0) {
            result.append("Error: Simpson's Rule requires an EVEN number of subintervals (n).\n");
            result.append("Cannot calculate with n = ").append(n).append(".\n");
        } else {
            double simpSum = y[0] + y[n];
            for (int i = 1; i < n; i++) {
                if (i % 2 == 1) {
                    simpSum += 4 * y[i]; // Odd indices
                } else {
                    simpSum += 2 * y[i]; // Even indices
                }
            }
            double simpResult = (h / 3.0) * simpSum;
            result.append(String.format("Formula: (h/3) * [y0 + yn + 4*(odds) + 2*(evens)]\n"));
            result.append(String.format("Area ≈ %.9f\n", simpResult));
        }
        return result.toString();
    }
}