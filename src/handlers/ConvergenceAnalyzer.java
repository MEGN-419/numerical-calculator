package handlers;

public abstract class ConvergenceAnalyzer {

    public static String analyzeSequence(double[] values, double tolerance) {
        if (values == null || values.length < 3) {
            if (values != null && values.length == 2) {
                double diff = Math.abs(values[1] - values[0]);
                if (diff < tolerance) {
                    return "Result: CONVERGES. Final limit is approximately " + values[1];
                }
                return "Result: INCONCLUSIVE (Too few iterations).";
            }
            return "Result: INSUFFICIENT DATA.";
        }

        int n = values.length - 1;
        double lastValue = values[n];

        // Check for overflow/infinity
        if (Double.isInfinite(lastValue) || Double.isNaN(lastValue) || Math.abs(lastValue) > 1e15) {
            return "Result: DIVERGES (Value exceeds bounds or is undefined).";
        }

        double diffLast = Math.abs(values[n] - values[n - 1]);
        double diffPrev = Math.abs(values[n - 1] - values[n - 2]);

        // If it already reached tolerance
        if (diffLast < tolerance) {
            return "Result: CONVERGES. Final limit is approximately " + lastValue;
        }

        // If differences are decreasing, it is likely converging but slowly
        if (diffLast < diffPrev) {
            return "Result: CONVERGING. Current limit is approximately " + lastValue;
        }

        // If differences are increasing, it's diverging
        if (diffLast > diffPrev && diffLast > tolerance) {
            return "Result: DIVERGES (Values are moving apart).";
        }

        // Oscillating or constant but above tolerance
        return "Result: NOT CONVERGING (Oscillating or stagnant).";
    }
}