package methods;

import handlers.ConvergenceAnalyzer;
import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class Aitken {

    public static String solve(Expression equation, double x0, int itt) {
        double[] x_vals = new double[itt + 1];
        x_vals[0] = x0;
        StringBuilder result = new StringBuilder();
        result.append("Entered fixed-point equation g(x) = ").append(equation.getExpressionString())
              .append("\nIterations = ").append(itt)
              .append("\nInitial x0 = ").append(x0).append("\n\n");
        for (int i = 0; i < itt; i++) {
            DynamicEquationParser.updateX(equation, x0);
            double x1 = equation.calculate();
            DynamicEquationParser.updateX(equation, x1);
            double x2 = equation.calculate();
            double denominator = x2 - 2 * x1 + x0;
            if (denominator == 0) {
                result.append("Error: Denominator became zero at iteration ").append(i + 1).append(".\n");
                break;
            }
            double x_aitken = x0 - (Math.pow(x1 - x0, 2) / denominator);
            x_vals[i + 1] = x_aitken;
            result.append("Iteration (").append(i + 1).append(") : x").append(i + 1).append(" = ")
                  .append(String.format("%.9f", x_aitken)).append("\n");
            x0 = x_aitken;
        }
        result.append("\n").append(ConvergenceAnalyzer.analyzeSequence(x_vals, 0.00000001));
        return result.toString();
    }
}