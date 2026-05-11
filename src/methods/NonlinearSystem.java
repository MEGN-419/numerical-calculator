package methods;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class NonlinearSystem {

    // Internal silent Gauss Elimination for solving [Jacobian] * [Delta] = [-F]
    private static double[] solveJacobian(double[][] J, double[] minusF) {
        int n = minusF.length;
        double[][] A = new double[n][n];
        double[] B = new double[n];
        for (int i = 0; i < n; i++) {
            B[i] = minusF[i];
            System.arraycopy(J[i], 0, A[i], 0, n);
        }
        // Forward elimination
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double ratio = A[j][i] / A[i][i];
                for (int k = 0; k < n; k++) {
                    A[j][k] -= ratio * A[i][k];
                }
                B[j] -= ratio * B[i];
            }
        }
        // Back substitution
        double[] delta = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * delta[j];
            }
            delta[i] = (B[i] - sum) / A[i][i];
        }
        return delta;
    }
    public static String solve(Expression[] equations, String[] varNames, double[] X0, int itt) {
        int n = equations.length;
        double[] X = X0.clone();
        double h = 1e-6; // Tiny step for numerical derivatives
        StringBuilder result = new StringBuilder();
        result.append("--- Dynamic Nonlinear System (Newton-Raphson) ---\n\n");
        // Dynamically build the header based on n variables
        StringBuilder header = new StringBuilder(String.format("%-4s | ", "Iter"));
        for (int i = 0; i < n; i++) header.append(String.format("%-10s | ", varNames[i]));
        for (int i = 0; i < n; i++) header.append(String.format("f%-9d | ", i + 1));
        result.append(header.toString()).append("\n");
        for (int i = 0; i < header.length(); i++) result.append("-");
        result.append("\n");
        // Set up Arguments for mXparser dynamically
        Argument[] args = new Argument[n];
        for (int i = 0; i < n; i++) {
            args[i] = new Argument(varNames[i], X[i]);
            for (Expression eq : equations) {
                eq.addArguments(args[i]);
            }
        }
        for (int iter = 0; iter <= itt; iter++) {
            // Update argument values for this loop
            for (int i = 0; i < n; i++) args[i].setArgumentValue(X[i]);
            // Calculate F(X) array
            double[] F = new double[n];
            double[] minusF = new double[n];
            boolean converged = true;
            for (int i = 0; i < n; i++) {
                F[i] = equations[i].calculate();
                minusF[i] = -F[i];
                if (Math.abs(F[i]) > 1e-8) converged = false;
            }
            // Dynamically print row values
            StringBuilder row = new StringBuilder(String.format("%-4d | ", iter));
            for (int i = 0; i < n; i++) row.append(String.format("%-10.6f | ", X[i]));
            for (int i = 0; i < n; i++) row.append(String.format("%-10.6f | ", F[i]));
            result.append(row.toString()).append("\n");
            if (converged && iter > 0) {
                result.append("\nResult: CONVERGED early at iteration ").append(iter).append("!\n");
                break;
            }
            if (iter == itt) break;
            // Calculate the n x n Jacobian Matrix dynamically
            double[][] J = new double[n][n];
            for (int col = 0; col < n; col++) {
                // Shift one specific variable by h
                args[col].setArgumentValue(X[col] + h);
                for (int row_eq = 0; row_eq < n; row_eq++) {
                    double f_shifted = equations[row_eq].calculate();
                    J[row_eq][col] = (f_shifted - F[row_eq]) / h;
                }
                // Reset that variable back to normal before shifting the next one
                args[col].setArgumentValue(X[col]);
            }
            // Solve for Delta using the internal Gauss Elimination
            double[] delta = solveJacobian(J, minusF);
            // Update variables for the next iteration (X_new = X_old + Delta)
            for (int i = 0; i < n; i++) {
                X[i] += delta[i];
            }
        }
        return result.toString();
    }
}