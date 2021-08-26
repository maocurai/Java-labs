import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;

public class MathLib {

    private static UnivariateFunction func = (double x) -> Math.pow(x,3)+x;

    public static double Simpson(int i, double v, double v1 ) {
        UnivariateIntegrator integrator = new SimpsonIntegrator();
        return integrator.integrate(70, func, -19, -16);
    }
}
