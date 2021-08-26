import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.solvers.BisectionSolver;
import org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver;
import org.apache.commons.math3.analysis.solvers.SecantSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;

public class MathLibraries {
    public static double bisectionMethod(double a, double b){
        BisectionSolver solver = new BisectionSolver();
        UnivariateFunction func = (double x) -> Math.pow((x-18),2)+Math.sin(x-18);
        return solver.solve(1000, func, a, b);
    }

    public static double secantMethod(double a, double b){
        SecantSolver solver = new SecantSolver();
        UnivariateFunction func = (double x) -> Math.pow((x-18),2)+Math.sin(x-18);
        return solver.solve(1000, func, a, b);
    }

    public static double newtonRaphson(double a, double b){
        NewtonRaphsonSolver solver = new NewtonRaphsonSolver();
        UnivariateDifferentiableFunction f = new UnivariateDifferentiableFunction() {

            public double value(double x) {
                return Math.pow((x-18),2)+Math.sin(x-18);
            }

            @Override
            public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
                return ((t.subtract(18)).pow(2)).add((t.subtract(18)).sin());
            }
        };

        return solver.solve(1000, f, a, b);
    }
}
