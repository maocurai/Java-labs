import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.SimpsonIntegrator;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;

public class Main {
    public static void main(String[] args) {
        IntegrationAndDifferentiation ob = new IntegrationAndDifferentiation();

        System.out.println("Метод послідовності приростів");
        showDiff(ob.incrementsSequence(-18, 0.001), 973);

        System.out.println("Метод центрованої різниці");
        showDiff(ob.centeredDifference(-18, 0.001), 973);

        System.out.println("Метод квадратичної інтерполяції");
        double [] xVal = new double[] { -19, -17.5, -16 };
        showDiff(ob.quadraticInterpolation(-18, xVal, IntegrationAndDifferentiation.countFunction(xVal)), 973);

        System.out.println("Метод лівих прямокутників");
        showDiff(ob.leftRectanglesMethod(-19, -16, 0.001, 70), -16248.7500);

        UnivariateFunction func = (double x) -> Math.pow(x,3)+x;

        System.out.println("Метод трапецій");
        showDiff(ob.trapezoidalMethod(-19, -16, 0.001, 70), -16248.7500);

        System.out.println("Метод Сімпсона");
        showDiff(ob.simpsonsMethod(-19, -16, 0.001, 70), new SimpsonIntegrator().integrate(1000, func, -19, -16));
    }

    public static void showDiff(double myVal, double funkVal) {
        System.out.printf("%s %-10.4f\n", "calculated value:", myVal);
        System.out.printf("%s %-10.4f\n", "math libraries:", funkVal);
        System.out.printf("%s %-10.4f\n\n", "error:", Math.abs(funkVal-myVal));
    }
}
