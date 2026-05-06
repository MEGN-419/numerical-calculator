package handlers;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class DynamicEquationParser {
    public static Expression EBX(String equation) {
        Argument x = new Argument("x");
        return new Expression(equation, x);
    }
    public static Expression EBXY(String equation) {
        Argument x = new Argument("x");
        Argument y = new Argument("y");
        return new Expression(equation, x , y );
    }
    public static Expression updateX(Expression expr, double xValue) {
        Argument x = expr.getArgument("x");
        if (x != null) {
            x.setArgumentValue(xValue);
        } else {
            x = new Argument("x", xValue);
            expr.addArguments(x);
        }
        return expr;
    }
}