package methods;

import handlers.ConvergenceAnalyzer;
import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class FalsePosition {

    public static String solve(Expression equation, double a, double b, int itt) {
        double[] c_vals = new double[itt];
        DynamicEquationParser.updateX(equation, a);
        double fa = equation.calculate();
        DynamicEquationParser.updateX(equation, b);
        double fb = equation.calculate();
        if (fa * fb >= 0) {
            return "Error: Initial bounds (" + a + ", " + b + ") do not bracket the root.";
        }
        StringBuilder result = new StringBuilder();
        result.append("Entered equation: ").append(equation.getExpressionString())
              .append("\nIterations = ").append(itt)
              .append("\nInitial a = ").append(a).append(", b = ").append(b).append("\n\n");
        for (int i = 0; i < itt; i++) {
            DynamicEquationParser.updateX(equation, a);
            fa = equation.calculate();
            DynamicEquationParser.updateX(equation, b);
            fb = equation.calculate();
            double c = b - ((b - a) * fb) / (fb - fa);
            c_vals[i] = c;
            DynamicEquationParser.updateX(equation, c);
            double fc = equation.calculate();
            result.append("Iteration (").append(i + 1).append(") : a = ").append(String.format("%.6f", a))
                  .append(" | b = ").append(String.format("%.6f", b))
                  .append(" | c = ").append(String.format("%.6f", c))
                  .append(" | f(c) = ").append(String.format("%.6f", fc)).append("\n");
            if (fa * fc < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        result.append("\n").append(ConvergenceAnalyzer.analyzeSequence(c_vals, 0.0001));
        return result.toString();
    }
}