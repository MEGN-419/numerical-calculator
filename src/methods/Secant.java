package methods;

import handlers.ConvergenceAnalyzer;
import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class Secant {
    public static String solve(Expression equation , int itt , double x0 , double x1 ){
        if (itt < 2) {
            return "Error: Number of iterations must be at least 2.";
        }
        double[] xval = new double[itt];
        xval[0] = x0;
        xval[1] = x1;
        double fx0 = 0;
        double fx1;
        for (int i = 1; i < itt - 1; i++) {
            if (i == 1) {
                DynamicEquationParser.updateX(equation, xval[i - 1]);
                fx0 = equation.calculate();
            }
            DynamicEquationParser.updateX(equation, xval[i]);
            fx1 = equation.calculate();
            
            double denominator = fx1 - fx0;
            if (denominator == 0) {
                for (int j = i + 1; j < itt; j++) {
                    xval[j] = Double.NaN;
                }
                break;
            }
            xval[i+1] = xval[i] - (fx1 * (xval[i] - xval[i-1])) / denominator;
            fx0 = fx1;
            if (Double.isNaN(xval[i+1]) || Double.isInfinite(xval[i+1])) {
                for (int j = i + 2; j < itt; j++) {
                    xval[j] = Double.NaN;
                }
                break;
            }
        }
        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Entered equation: ").append(equation.getExpressionString())
                   .append("\nIterations = ").append(itt)
                   .append("\nx0 = ").append(x0);
         for (int i = 1; i < xval.length; i++) {
            finalResult.append("\nx").append(i).append(" = ").append(xval[i]);
        }
        finalResult.append("\n").append(ConvergenceAnalyzer.analyzeSequence(xval, 1e-6));
        return finalResult.toString();
    }
}
