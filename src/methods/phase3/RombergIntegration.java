package methods.phase3;

import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class RombergIntegration {

    public static String solve(Expression equation, double a, double b, int maxRows) {
        if (maxRows < 2) return "Error: Romberg Integration requires at least 2 rows.";

        double[][] R = new double[maxRows][maxRows];
        StringBuilder result = new StringBuilder();

        result.append("=== Romberg Integration ===\n");
        result.append("Function: ").append(equation.getExpressionString()).append("\n");
        result.append(String.format("Bounds: [%.4f, %.4f] | Max Rows: %d\n\n", a, b, maxRows));

        // R[0][0] is the standard Trapezoidal rule with 1 interval
        double h = b - a;
        DynamicEquationParser.updateX(equation, a);
        double fa = equation.calculate();
        DynamicEquationParser.updateX(equation, b);
        double fb = equation.calculate();

        R[0][0] = (h / 2.0) * (fa + fb);

        // Generate the rest of the Romberg Table
        for (int i = 1; i < maxRows; i++) {
            h = h / 2.0; // Halve the step size

            // Calculate the new Trapezoidal estimate for column 0
            double sum = 0.0;
            int powerOfTwo = 1 << (i - 1); // 2^(i-1)
            for (int k = 1; k <= powerOfTwo; k++) {
                double x_k = a + (2 * k - 1) * h;
                DynamicEquationParser.updateX(equation, x_k);
                sum += equation.calculate();
            }
            R[i][0] = 0.5 * R[i - 1][0] + sum * h;

            // Apply Richardson Extrapolation for the rest of the row
            for (int j = 1; j <= i; j++) {
                double factor = Math.pow(4, j);
                R[i][j] = R[i][j - 1] + (R[i][j - 1] - R[i - 1][j - 1]) / (factor - 1.0);
            }
        }

        // Print the Romberg Triangle beautifully
        result.append("--- Romberg Table ---\n");
        for (int i = 0; i < maxRows; i++) {
            result.append(String.format("R[%d] | ", i));
            for (int j = 0; j <= i; j++) {
                result.append(String.format("%-12.8f ", R[i][j]));
            }
            result.append("\n");
        }

        result.append("\n--------------------------------------------------\n");
        result.append(String.format("Final Estimated Area ≈ %.10f\n", R[maxRows - 1][maxRows - 1]));

        return result.toString();
    }
}