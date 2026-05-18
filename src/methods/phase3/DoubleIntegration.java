package methods.phase3;

import org.mariuszgromada.math.mxparser.Expression;

public abstract class DoubleIntegration {

    public static String solve(Expression equation, double ax, double bx, int nx, double cy, double dy, int ny) {
        if (nx <= 0 || ny <= 0) return "Error: Number of subintervals must be > 0.";
        if (nx % 2 != 0 || ny % 2 != 0) return "Error: Simpson's Rule requires EVEN subintervals for both nx and ny.";

        double hx = (bx - ax) / nx;
        double hy = (dy - cy) / ny;

        StringBuilder result = new StringBuilder();
        result.append("=== Double Numerical Integration (Simpson's 1/3 Rule) ===\n");
        result.append("Function: f(x,y) = ").append(equation.getExpressionString()).append("\n");
        result.append(String.format("X-Bounds: [%.4f, %.4f] | nx = %d | hx = %.5f\n", ax, bx, nx, hx));
        result.append(String.format("Y-Bounds: [%.4f, %.4f] | ny = %d | hy = %.5f\n\n", cy, dy, ny, hy));

        double totalSum = 0;

        // Nested loops to traverse the 2D grid
        for (int i = 0; i <= nx; i++) {
            double x = ax + i * hx;

            // Determine Simpson's weight for X
            int weightX = (i == 0 || i == nx) ? 1 : (i % 2 == 1 ? 4 : 2);

            for (int j = 0; j <= ny; j++) {
                double y = cy + j * hy;

                // Determine Simpson's weight for Y
                int weightY = (j == 0 || j == ny) ? 1 : (j % 2 == 1 ? 4 : 2);

                // Combined 2D Weight
                int combinedWeight = weightX * weightY;

                // Evaluate the function f(x,y)
                equation.setArgumentValue("x", x);
                equation.setArgumentValue("y", y);
                double fxy = equation.calculate();

                totalSum += combinedWeight * fxy;
            }
        }

        // The 2D Simpson's multiplier is (hx * hy) / 9
        double finalVolume = (hx * hy / 9.0) * totalSum;

        result.append(String.format("Total Weighted Sum = %.6f\n", totalSum));
        result.append(String.format("Formula: (hx * hy / 9) * Sum\n"));
        result.append(String.format("Final Estimated Volume ≈ %.9f\n", finalVolume));

        return result.toString();
    }
}