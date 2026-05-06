package methods;

import handlers.ConvergenceAnalyzer;
import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class FixedPoint {
    int iterations;
    double x0;
    Expression equation;
    public static String solve(int iterations, double x0, Expression equation) {
        double[] values = new double[iterations + 1];
        values[0] = x0;
        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Entered equation: ").append(equation.getExpressionString())
                   .append("\nIterations = ").append(iterations)
                   .append("\nx0 = ").append(x0);
        for (int i = 1; i <= iterations; i++) {
            DynamicEquationParser.updateX(equation, values[i - 1]);
            values[i] = equation.calculate();
            finalResult.append("\nx").append(i).append(" = ").append(values[i]);
        }
        finalResult.append("\n").append(ConvergenceAnalyzer.analyzeSequence(values, 0.0001));
        return finalResult.toString();
    }
}
