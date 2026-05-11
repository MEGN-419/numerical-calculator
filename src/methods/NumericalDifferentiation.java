package methods;

public abstract class NumericalDifferentiation {

    public static String solve(double[] x, double[] y) {
        int n = x.length;
        if (n < 3) {
            return "Error: At least 3 points are required for these formulas.";
        }

        double h = x[1] - x[0]; // Assuming uniform step size

        StringBuilder result = new StringBuilder();
        result.append("=== Numerical Differentiation ===\n");
        result.append(String.format("Uniform Step Size (h) = %.4f\n\n", h));

        result.append(String.format("%-5s | %-8s | %-8s | %-12s | %-12s | %-15s\n",
                                    "i", "x", "f(x)", "f'(x)", "f''(x)", "Formula Used"));
        result.append("--------------------------------------------------------------------------------\n");

        for (int i = 0; i < n; i++) {
            double firstDeriv = 0;
            double secondDeriv = Double.NaN;
            String formulaUsed = "";

            if (i == 0) {
                // Three-Point Forward Difference for the first point
                firstDeriv = (-3 * y[i] + 4 * y[i + 1] - y[i + 2]) / (2 * h);
                formulaUsed = "3-Pt Forward";
            }
            else if (i == n - 1) {
                // Three-Point Backward Difference for the last point
                firstDeriv = (3 * y[i] - 4 * y[i - 1] + y[i - 2]) / (2 * h);
                formulaUsed = "3-Pt Backward";
            }
            else {
                // Three-Point Central Difference for all middle points
                firstDeriv = (y[i + 1] - y[i - 1]) / (2 * h);
                formulaUsed = "Central";

                // 2nd Derivative Central Formula from Page 6
                secondDeriv = (y[i + 1] - 2 * y[i] + y[i - 1]) / (h * h);
            }

            String secDerivStr = Double.isNaN(secondDeriv) ? "-" : String.format("%.4f", secondDeriv);

            result.append(String.format("%-5d | %-8.4f | %-8.4f | %-12.4f | %-12s | %-15s\n",
                         i, x[i], y[i], firstDeriv, secDerivStr, formulaUsed));
        }

        return result.toString();
    }
}