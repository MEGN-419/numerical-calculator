package methods;

public abstract class PolynomialRegression {

    // Internal Gauss Elimination to solve the 3x3 Normal Equations matrix
    private static double[] solveSystem(double[][] A_in, double[] B_in) {
        int n = 3;
        double[][] A = new double[n][n];
        double[] B = new double[n];
        for (int i = 0; i < n; i++) {
            B[i] = B_in[i];
            System.arraycopy(A_in[i], 0, A[i], 0, n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double ratio = A[j][i] / A[i][i];
                for (int k = 0; k < n; k++) A[j][k] -= ratio * A[i][k];
                B[j] -= ratio * B[i];
            }
        }
        double[] X = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) sum += A[i][j] * X[j];
            X[i] = (B[i] - sum) / A[i][i];
        }
        return X; // Returns [c, b, a]
    }
    public static String solve(double[] x, double[] y) {
        int n = x.length;
        double sumX = 0, sumY = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumXY = 0, sumX2Y = 0;
        StringBuilder result = new StringBuilder();
        result.append("=== Quadratic Regression (y = ax^2 + bx + c) ===\n\n");
        result.append(String.format("%-4s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s\n", "i", "X", "Y", "X^2", "X^3", "X^4", "XY", "X^2*Y"));
        result.append("----------------------------------------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            double x2 = x[i] * x[i];
            double x3 = x2 * x[i];
            double x4 = x3 * x[i];
            double xy = x[i] * y[i];
            double x2y = x2 * y[i];
            sumX += x[i]; sumY += y[i];
            sumX2 += x2;  sumX3 += x3; sumX4 += x4;
            sumXY += xy;  sumX2Y += x2y;

            result.append(String.format("%-4d | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f\n",
                         (i + 1), x[i], y[i], x2, x3, x4, xy, x2y));
        }
        result.append("----------------------------------------------------------------------------------\n");
        result.append(String.format("SUMS | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f | %-8.2f\n\n",
                     sumX, sumY, sumX2, sumX3, sumX4, sumXY, sumX2Y));
        // Build the 3x3 Matrix for Normal Equations
        // Order of variables: [c, b, a]
        double[][] A = {
            {n, sumX, sumX2},
            {sumX, sumX2, sumX3},
            {sumX2, sumX3, sumX4}
        };
        double[] B = {sumY, sumXY, sumX2Y};
        double[] coeffs = solveSystem(A, B);
        double c = coeffs[0];
        double b = coeffs[1];
        double a = coeffs[2];
        result.append("Normal Equations:\n");
        result.append(String.format("1) %.2f = %d*c + %.2f*b + %.2f*a\n", sumY, n, sumX, sumX2));
        result.append(String.format("2) %.2f = %.2f*c + %.2f*b + %.2f*a\n", sumXY, sumX, sumX2, sumX3));
        result.append(String.format("3) %.2f = %.2f*c + %.2f*b + %.2f*a\n\n", sumX2Y, sumX2, sumX3, sumX4));
        result.append("Calculated Coefficients:\n");
        result.append(String.format("a = %.6f\nb = %.6f\nc = %.6f\n\n", a, b, c));
        String signB = (b >= 0) ? "+" : "-";
        String signC = (c >= 0) ? "+" : "-";
        result.append(String.format("Parabola of Best Fit:\n y = %.6fx^2 %s %.6fx %s %.6f\n",
                      a, signB, Math.abs(b), signC, Math.abs(c)));
        return result.toString();
    }
}