package methods;

import handlers.DynamicEquationParser;
import org.mariuszgromada.math.mxparser.Expression;

public abstract class GaussQuadrature {

    public static String solve(Expression eq, double a, double b) {
        StringBuilder result = new StringBuilder();
        result.append("=== Gauss Quadrature Method ===\n");
        result.append("Original Function: f(x) = ").append(eq.getExpressionString()).append("\n");
        result.append(String.format("Original Bounds: [%.2f, %.2f]\n\n", a, b));

        // Translation coefficients
        double m = (b - a) / 2.0;
        double c = (a + b) / 2.0;

        result.append("--- Domain Translation [-1, 1] ---\n");
        result.append(String.format("x = m*x_d + c -> x = (%.2f)*x_d + (%.2f)\n", m, c));
        result.append(String.format("dx = m*dx_d -> dx = (%.2f)*dx_d\n\n", m));

        // Helper function to evaluate f(m*x_d + c) * m
        java.util.function.Function<Double, Double> evalTranslated = (xd) -> {
            double translatedX = m * xd + c;
            DynamicEquationParser.updateX(eq, translatedX);
            return eq.calculate() * m;
        };

        // 1-Point Rule
        double val1pt = 2.0 * evalTranslated.apply(0.0);
        result.append("--- 1-Point Rule ---\n");
        result.append("Points: x = 0\n");
        result.append(String.format("Area ≈ %.6f\n\n", val1pt));

        // 2-Point Rule
        double pt2 = 1.0 / Math.sqrt(3.0);
        double val2pt = 1.0 * evalTranslated.apply(-pt2) + 1.0 * evalTranslated.apply(pt2);
        result.append("--- 2-Point Rule ---\n");
        result.append("Points: x = ±1/√(3)\n");
        result.append(String.format("Area ≈ %.6f\n\n", val2pt));

        // 3-Point Rule
        double pt3 = Math.sqrt(3.0 / 5.0);
        double val3pt = (5.0/9.0) * evalTranslated.apply(-pt3) +
                        (8.0/9.0) * evalTranslated.apply(0.0) +
                        (5.0/9.0) * evalTranslated.apply(pt3);
        result.append("--- 3-Point Rule ---\n");
        result.append("Points: x = 0, x = ±√(3/5)\n");
        result.append(String.format("Area ≈ %.6f\n", val3pt));

        return result.toString();
    }
}