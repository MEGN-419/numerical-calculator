package methods;

import handlers.ConvergenceAnalyzer;
import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class Bisection {
    static int ittno = 0;
    static int ittne;
    static double[] A;
    static double[] B;
    static double[] C;
    public static String solve(Expression equation , double a , double b , int itt) {
        A = new double[itt];
        B = new double[itt];
        C = new double[itt];
        ittne = itt;
        A[0] = a;
        equation = DynamicEquationParser.updateX(equation,a);
        double fa = equation.calculate();
        B[0]=b;
        equation = DynamicEquationParser.updateX(equation,b);
        double fb = equation.calculate();
        double c = (a+b)/2;
        C[0]=c;
        equation = DynamicEquationParser.updateX(equation,c);
        double fc = equation.calculate();
        if (fc*fa < 0 ){
            ittno++;
            ittne--;
            rec(equation,a,c);
        }
        else  if (fc*fb < 0 ){
            ittno++;
            ittne--;
            rec(equation,c,b);
        }
        StringBuilder result = new StringBuilder();
        result.append("equation entered : ").append(equation.getExpressionString()).append("\n")
                .append("intial (a,b) : ").append(a).append(" , ").append(b).append(". c = ").append(c).append("\n");
        for (int i=1; i<C.length; i++){
           result.append("iteration (").append(i).append(") , (a,b) : ").append(A[i]).append(" , ").append(B[i]).append(". c = ").append(C[i]).append("\n");;
        }
        result.append("\n").append(ConvergenceAnalyzer.analyzeSequence(C, 0.0001));
        return result.toString();
    }
    private static String rec(Expression equation , double a , double b) {
        if (ittne > 0) {
            A[ittno] = a;
            equation = DynamicEquationParser.updateX(equation,a);
            double fa = equation.calculate();
            B[ittno]=b;
            equation = DynamicEquationParser.updateX(equation,b);
            double fb = equation.calculate();
            double c = (a+b)/2;
            C[ittno]=c;
            equation = DynamicEquationParser.updateX(equation,c);
            double fc = equation.calculate();
            if (fc*fa < 0 ){
                ittno++;
                ittne--;
                rec(equation,a,c);
        }
            else
                if (fc*fb < 0 ){
                    ittno++;
                    ittne--;
                    rec(equation,c,b);
                }
        }
        return null;
    }
}
