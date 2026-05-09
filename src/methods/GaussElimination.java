package methods;

public abstract class GaussElimination {

    public static String solve(double[][] A_in, double[] B_in) {
        int n = B_in.length;
        double[][] A = new double[n][n];
        double[] B = new double[n];
        for (int i = 0; i < n; i++) {
            B[i] = B_in[i];
            System.arraycopy(A_in[i], 0, A[i], 0, n);
        }
        StringBuilder result = new StringBuilder();
        result.append("=== GAUSS ELIMINATION STEPS ===\n\n");
        for (int i = 0; i < n; i++) {
            // Safety check for division by zero
            if (Math.abs(A[i][i]) < 1e-12) {
                return "Error: Zero pivot encountered. Matrix requires row swapping (partial pivoting) to solve.";
            }
            for (int j = i + 1; j < n; j++) {
                double ratio = A[j][i] / A[i][i];

                for (int k = 0; k < n; k++) {
                    A[j][k] -= ratio * A[i][k];
                }
                B[j] -= ratio * B[i];
            }
            result.append("After eliminating column ").append(i + 1).append(":\n");
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    result.append(String.format("%10.4f ", A[r][c]));
                }
                result.append(String.format(" | %10.4f\n", B[r]));
            }
            result.append("----------------------------------------------\n");
        }
        double[] X = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * X[j];
            }
            X[i] = (B[i] - sum) / A[i][i];
        }
        result.append("\n=== Final Exact Result ===\n");
        for (int i = 0; i < n; i++) {
            result.append("x").append(i + 1).append(" = ").append(String.format("%.6f", X[i])).append("\n");
        }
        return result.toString();
    }
}