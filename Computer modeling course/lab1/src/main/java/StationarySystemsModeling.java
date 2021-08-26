import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.util.MathUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Float.NaN;

public class StationarySystemsModeling {

    double [] xValues;
    double [] yValues;
    String funcName = "y = ";
    boolean isName = false;

    public String getFuncName() {
        return funcName;
    }

    public StationarySystemsModeling(double [] values) {
        xValues = values;
        yValues = countFunction(values);
    }

    public StationarySystemsModeling(double [] values, double [] yvalues) {
        xValues = values;
        yValues = yvalues;
    }

    public void showDifference(double [] x, double [] y) {
        System.out.printf("%-20s %-20s %-20s %-20s\n", "x", "Calculated value", "Function value", "Difference");
        for(int i=0; i<x.length; i++) {
            System.out.printf("%-20.2f %-20.3f %-20.3f %-20.3f\n",
                    x[i], y[i], (countFunction(x[i])), Math.abs((countFunction(x[i])) - y[i]));
        }
        System.out.println();
    }

    public static double countFunction(double x) {
        return (Math.sin(x) + x);
    }

    public static double[] countFunction(double [] x) {
        double [] res = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            res[i] = countFunction(x[i]);
        }
        return res;
    }

    public double [] lagrangeInterpolation(double [] xVal) {
        double [] yVal = new double[xVal.length];
        for(int i=0; i<xVal.length; i++) {
            yVal[i] = lagrangeInterpolation(xVal[i]);
        }
        return yVal;
    }

    public double lagrangeInterpolation(double x) {
        double result = 0;
        for(int i=0; i<xValues.length; i++) {
            double Ljx = 1;
            double Ljxj = 1;
            for(int j=0; j<xValues.length; j++) {
                if (i != j) {
                    Ljx *= (x - xValues[j]);
                    Ljxj *= (xValues[i] - xValues[j]);
                }
            }
            result += (yValues[i] * (Ljx / Ljxj));
        }
        return result;
    }

    public double[] PolinomChebisheva(double a, double b, double[] values) {
        double[] result = new double[values.length];
        double [] xval = new double [values.length+1];
        double [] yval = new double [values.length+1];
        double n = values.length+1;
        for (int k = 0; k < n; k++) {
            double tk = Math.cos((((2 * k + 1) / (2 * n + 2)) * Math.PI));
            double xk = ((a + b) / 2 + (b - a) / 2 * tk);
            xval[k] = xk;
            yval[k] = countFunction(xk);
        }
        StationarySystemsModeling tempOb = new StationarySystemsModeling(xval, yval);
        for (int i = 0; i < values.length; i++) {
            result[i] = tempOb.lagrangeInterpolation(values[i]);
        }
        return result;
    }

    public double [] polynomNewtonEvenIntervals(double [] xVal) {
        double [] yVal = new double[xVal.length];
        for(int i=0; i<xVal.length; i++) {
            yVal[i] = polynomNewtonEvenIntervals(xVal[i]);
        }
        return yVal;
    }

    public double polynomNewtonEvenIntervals(double x) {
        double result = yValues[0];
        double h = xValues[1] - xValues[0];
        double q = (x - xValues[0]) / h;
        for (int i = 1; i < xValues.length; i++) {
            result += (countQ(i, q) * countDelta(0, i)) / MathUtils.factorial(i);
        }
        return result;
    }

    public double countQ(int i, double q) {
        double res = 1;
        for (int k = 0; k < i; k++) {
            res *= (q - k);
        }
        return res;
    }

    public double countDelta(int num, int order) {
        return (order != 1) ? (countDelta(num + 1, order - 1) - countDelta(num, order - 1))
                : (yValues[num + 1] - yValues[num]);
    }

    public double [] polynomNewtonUnevenIntervals(double [] xVal) {
        double [] yVal = new double[xVal.length];
        for(int i=0; i<xVal.length; i++) {
            yVal[i] = polynomNewtonUnevenIntervals(xVal[i]);
        }
        return yVal;
    }

    public double polynomNewtonUnevenIntervals(double x) {
        double result = yValues[0];
        for (int i = 1; i < xValues.length; i++) {
            result += countDividedDifference(i, 0) * countXdiff(x, i);
        }
        return result;
    }

    public double countXdiff(double x, int count) {
        double res = 1;
        for(int i=0; i<count; i++) {
            res *= (x - xValues[i]);
        }
        return res;
    }

    public double countDividedDifference(int order, int num) {
        if (order == 1) {
            return (yValues[num + 1] - yValues[num]) / (xValues[num + 1] - xValues[num]);
        } else {
            return (countDividedDifference(order - 1, num + 1) -
                    countDividedDifference(order - 1, num))
                    / (xValues[order+num] - xValues[num]);
        }
    }
    //num - індекс початку інтервала    order - порядок різниці

    public double [] spline(double [] xVal) {
        double [] yVal = new double[xVal.length];
        for(int i=0; i<xVal.length; i++) {
            yVal[i] = spline(xVal[i]);
        }
        return yVal;
    }

    public double spline(double x) {
        double y = NaN;
        for(int i=1; i < xValues.length; i++) {
            if (xValues[i-1] <= x  &&  x <= xValues[i]) {
                double b = (xValues[i - 1] * yValues[i] - xValues[i] * yValues[i - 1]) / (xValues[i - 1] - xValues[i]);
                double a = (yValues[i] - b) / xValues[i];
                y = a * x + b;
                break;
            }
        }
        return y;
    }

    public double [] leastSquaresMethod(double [] xVal) {
        double [] yVal = new double[xVal.length];
        for(int i=0; i<xVal.length; i++) {
            yVal[i] = leastSquaresMethod(xVal[i]);
        }
        return yVal;
    }

    public double leastSquaresMethod(double x) {
        double [][] uarr = new double [xValues.length][2];
        for(int i=0; i<xValues.length; i++) {
            uarr[i][0] = 1;
            uarr[i][1] = xValues[i];
        }
        RealMatrix uMatrix = MatrixUtils.createRealMatrix(uarr);
        RealMatrix yMatrix = MatrixUtils.createColumnRealMatrix(yValues);
        RealMatrix uMatrixTrans = uMatrix.transpose();
        RealMatrix resMatrix = (uMatrixTrans.multiply(uMatrix)).inverse().multiply(uMatrixTrans).multiply(yMatrix);
        if(!isName) {
            funcName += (round(resMatrix.getEntry(0,0), 3) + " + " + round(resMatrix.getEntry(1,0), 3) + "x");
            isName = true;
        }
        return resMatrix.getEntry(0,0) + resMatrix.getEntry(1,0)*x;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static double leastSquaresMethod(double [][] data, double [] x) {
        double [][] uarr = new double [data.length][data[0].length];
        for(int i=0; i<data.length; i++) {
            uarr[i][0] = 1;
            for(int j=1; j<data[0].length; j++) {
                uarr[i][j] = data[i][j-1];
            }
        }
        double [] yVal = new double[data.length];
        for(int i=0; i<data.length; i++) {
            yVal[i] = data[i][data[0].length-1];
        }
        RealMatrix uMatrix = MatrixUtils.createRealMatrix(uarr);
        RealMatrix yMatrix = MatrixUtils.createColumnRealMatrix(yVal);
        RealMatrix uMatrixTrans = uMatrix.transpose();
        RealMatrix resMatrix = (uMatrixTrans.multiply(uMatrix)).inverse().multiply(uMatrixTrans).multiply(yMatrix);
        double result = resMatrix.getEntry(0,0);
        for(int i=1; i<x.length+1; i++) {
            result += resMatrix.getEntry(i,0)*x[i-1];
        }
        return result;
    }
}