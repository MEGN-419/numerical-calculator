package methods;

public abstract class LinearRegression {

    public static String solve(double[] x, double[] y) {
        if (x.length != y.length || x.length == 0) {
            return "Error: X and Y arrays must be the same size and contain data.";
        }
        int n = x.length;
        double sumX = 0, sumY = 0, sumX2 = 0, sumXY = 0;
        StringBuilder result = new StringBuilder();
        result.append("=== Linear Regression (Least Squares Method) ===\n\n");
        result.append(String.format("%-5s | %-10s | %-10s | %-10s | %-10s\n", "i", "X", "Y", "X^2", "XY"));
        result.append("-----------------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            double x2 = x[i] * x[i];
            double xy = x[i] * y[i];
            sumX += x[i];
            sumY += y[i];
            sumX2 += x2;
            sumXY += xy;
            result.append(String.format("%-5d | %-10.4f | %-10.4f | %-10.4f | %-10.4f\n", (i + 1), x[i], y[i], x2, xy));
        }
        result.append("-----------------------------------------------------------\n");
        result.append(String.format("%-5s | %-10.4f | %-10.4f | %-10.4f | %-10.4f\n\n", "SUMS", sumX, sumY, sumX2, sumXY));
        double b = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double a = (sumY - b * sumX) / n;
        result.append("Normal Equations:\n");
        result.append(String.format("1) %.4f = %d*a + %.4f*b\n", sumY, n, sumX));
        result.append(String.format("2) %.4f = %.4f*a + %.4f*b\n\n", sumXY, sumX, sumX2));
        result.append("Calculated Coefficients:\n");
        result.append(String.format("Intercept (a) = %.6f\n", a));
        result.append(String.format("Slope (b) = %.6f\n", b));
        String sign = (b >= 0) ? "+" : "-";
        result.append(String.format("\nLine of Best Fit: y = %.4f %s %.4fx\n", a, sign, Math.abs(b)));
        return result.toString();
    }
}