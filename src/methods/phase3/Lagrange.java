package methods.phase3;

public abstract class Lagrange {

    public static String solve(double[] x, double[] y, double xp) {
        int n = x.length;
        double yp = 0;
        StringBuilder result = new StringBuilder();
        result.append("=== Lagrange Interpolation ===\n\n");
        result.append("Estimating value at x = ").append(xp).append("\n\n");
        for (int i = 0; i < n; i++) {
            double p = 1;
            result.append(String.format("L_%d(x) terms for point (%.2f, %.2f):\n", i, x[i], y[i]));
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double fraction = (xp - x[j]) / (x[i] - x[j]);
                    p *= fraction;
                    result.append(String.format("   * (%.2f - %.2f) / (%.2f - %.2f) = %.4f\n",
                                  xp, x[j], x[i], x[j], fraction));
                }
            }
            double termValue = p * y[i];
            yp += termValue;
            result.append(String.format("-> L_%d(%.2f) = %.5f\n", i, xp, p));
            result.append(String.format("-> Term %d contribution = %.5f * %.2f = %.5f\n\n", i, p, y[i], termValue));
        }
        result.append("--------------------------------------------------\n");
        result.append(String.format("Final Interpolated Value:\n f(%.4f) = %.6f\n", xp, yp));
        return result.toString();
    }
}