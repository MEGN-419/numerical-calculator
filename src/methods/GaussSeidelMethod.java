package methods;

public abstract class GaussSeidelMethod {

    public static String solve(double[][] A, double[] B, double[] X0, int itt) {
        int n = B.length;
        double[] X = X0.clone();
        StringBuilder result = new StringBuilder();
        result.append("--- Gauss-Seidel Method Iterations ---\n");
        StringBuilder header = new StringBuilder(String.format("%-5s | ", "Iter"));
        for (int i = 0; i < n; i++) {
            header.append(String.format("x%-9d | ", i + 1));
        }
        result.append(header.toString()).append("\n");
        for(int i = 0; i < header.length(); i++) result.append("-");
        result.append("\n");
        for (int k = 0; k <= itt; k++) {
            StringBuilder row = new StringBuilder(String.format("%-5d | ", k));
            for (int i = 0; i < n; i++) {
                row.append(String.format("%-10.6f | ", X[i]));
            }
            result.append(row.toString()).append("\n");
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