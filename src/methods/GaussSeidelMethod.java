package methods;

public abstract class GaussSeidelMethod {

    public static String solve(double[][] A, double[] B, double[] X0, int itt) {
        int n = B.length;
        double[] X = X0.clone();
        StringBuilder result = new StringBuilder();
        result.append("--- Gauss-Seidel Method Iterations ---\n");
        result.append(String.format("%-5s | %-10s | %-10s | %-10s\n", "Iter", "x", "y", "z"));
        result.append("---------------------------------------------\n");
        for (int k = 0; k <= itt; k++) {
            result.append(String.format("%-5d | %-10.6f | %-10.6f | %-10.6f\n", k, X[0], X[1], X[2]));
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum += A[i][j] * X[j];
                    }
                }
                X[i] = (B[i] - sum) / A[i][i];
            }
        }
        return result.toString();
    }
}