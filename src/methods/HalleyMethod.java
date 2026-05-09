package methods;

import handlers.ConvergenceAnalyzer;
import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class HalleyMethod {

    public static String solve(Expression equation, Expression derivative1, Expression derivative2, double x0, int itt) {
        double[] x_vals = new double[itt + 1];
        x_vals[0] = x0;
        StringBuilder result = new StringBuilder();
        result.append("Entered equation f(x) = ").append(equation.getExpressionString())
              .append("\nDerivatives f'(x) and f''(x) = Calculated dynamically")
              .append("\nIterations = ").append(itt)
              .append("\nInitial x0 = ").append(x0).append("\n\n");

        for (int i = 0; i < itt; i++) {
            DynamicEquationParser.updateX(equation, x0);
            double fx = equation.calculate();
            DynamicEquationParser.updateX(derivative1, x0);
            double dfx = derivative1.calculate();
            DynamicEquationParser.updateX(derivative2, x0);
            double ddfx = derivative2.calculate();
            double numerator = fx * dfx;
            double denominator = (dfx * dfx) - (fx * ddfx);
            if (denominator == 0) {
                result.append("Error: Denominator became zero at iteration ").append(i).append(".\n");
                break;
            }
            double x1 = x0 - (numerator / denominator);
            x_vals[i + 1] = x1;
            result.append("Iteration (").append(i + 1).append(") : x").append(i + 1).append(" = ")
                  .append(String.format("%.9f", x1)).append("\n");
            x0 = x1;
        }

        result.append("\n").append(ConvergenceAnalyzer.analyzeSequence(x_vals, 0.00000001)); // Halley converges extremely fast, so tight tolerance
        return result.toString();
    }
}