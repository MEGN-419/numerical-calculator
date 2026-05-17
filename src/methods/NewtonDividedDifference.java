package methods;

public abstract class NewtonDividedDifference {

    public static String solve(double[] x, double[] y, double xp) {
        int n = x.length;
        double[][] f = new double[n][n];

        // Step 1: Initialize the first column with y values
        for (int i = 0; i < n; i++) {
            f[i][0] = y[i];
        }

        // Step 2: Calculate the divided differences table
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                f[i][j] = (f[i + 1][j - 1] - f[i][j - 1]) / (x[i + j] - x[i]);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("=== Newton's Divided Difference Interpolation ===\n\n");

        // Print the table beautifully
        result.append("--- Divided Difference Table ---\n");
        result.append(String.format("%-8s | %-10s | ", "x", "f(x)"));
        for (int j = 1; j < n; j++) result.append(String.format("%d-DD       | ", j));
        result.append("\n");
        for (int i = 0; i < n * 15; i++) result.append("-");
        result.append("\n");

        for (int i = 0; i < n; i++) {
            result.append(String.format("%-8.2f | ", x[i]));
            for (int j = 0; j < n - i; j++) {
                result.append(String.format("%-10.5f | ", f[i][j]));
            }
            result.append("\n");
        }

        // Step 3: Extract the leading coefficients (b0, b1, b2...)
        result.append("\n--- Interpolating Polynomial ---\n");
        result.append("Leading Coefficients (b_n): \n");
        for (int i = 0; i < n; i++) {
            result.append(String.format("b_%d = %.5f\n", i, f[0][i]));
        }

        // Step 4: Evaluate the polynomial at xp
        double yp = f[0][0];
        double xTerms = 1.0;

        result.append(String.format("\nEvaluating at x = %.4f:\n", xp));
        result.append(String.format("P(%.4f) = %.5f", xp, f[0][0]));

        for (int i = 1; i < n; i++) {
            xTerms *= (xp - x[i - 1]);
            double termValue = f[0][i] * xTerms;
            yp += termValue;

            // Show the math steps
            result.append(String.format(" + (%.5f * %.5f)", f[0][i], xTerms));
        }

        result.append(String.format("\n\nFinal Estimated Value:\n f(%.4f) = %.6f\n", xp, yp));

        return result.toString();
    }
}