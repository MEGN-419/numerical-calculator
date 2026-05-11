package methods;

public abstract class ExponentialRegression {

    public static String solve(double[] x, double[] y_raw) {
        int n = x.length;
        double sumX = 0, sumY = 0, sumX2 = 0, sumXY = 0;
        StringBuilder result = new StringBuilder();
        result.append("=== Exponential Curve Fitting (y = a * e^(bx)) ===\n\n");
        result.append("Transformation: ln(y) = ln(a) + bx\n");
        result.append("Let Y = ln(y), X = x, A = ln(a), B = b\n\n");
        result.append(String.format("%-4s | %-8s | %-8s | %-9s | %-9s | %-9s | %-9s\n", "i", "x", "y", "X=x", "Y=ln(y)", "X^2", "X*Y"));
        result.append("-----------------------------------------------------------------------\n");
        for (int i = 0; i < n; i++) {
            double X = x[i];
            double Y = Math.log(y_raw[i]);

            double X2 = X * X;
            double XY = X * Y;

            sumX += X;
            sumY += Y;
            sumX2 += X2;
            sumXY += XY;

            result.append(String.format("%-4d | %-8.2f | %-8.3f | %-9.2f | %-9.5f | %-9.2f | %-9.5f\n",
                         (i + 1), x[i], y_raw[i], X, Y, X2, XY));
        }
        result.append("-----------------------------------------------------------------------\n");
        result.append(String.format("SUMS | %-8s | %-8s | %-9.2f | %-9.5f | %-9.2f | %-9.5f\n\n",
                     "-", "-", sumX, sumY, sumX2, sumXY));
        double B = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double A = (sumY - B * sumX) / n;
        double a = Math.exp(A);
        result.append("Calculated Linear Coefficients:\n");
        result.append(String.format("A = %.5f\n", A));
        result.append(String.format("B = %.5f\n\n", B));
        result.append("Reverse Transformation:\n");
        result.append(String.format("a = e^A = e^(%.5f) = %.8f\n", A, a));
        result.append(String.format("b = B = %.5f\n\n", B));
        result.append(String.format("Final Exponential Equation:\n y = %.8f * e^(%.5fx)\n", a, B));
        return result.toString();
    }
}