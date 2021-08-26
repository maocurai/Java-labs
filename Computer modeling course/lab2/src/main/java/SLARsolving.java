import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class SLARsolving {

    public double g;
    public double k;

    public SLARsolving(double g, double k) {
        this.g = g;
        this.k = k;
    }

    public double halfDivisionMethod(double a, double b, double epsilon) {
        double c = (b+a)/2;
        while(Math.abs(b-a) > epsilon) {
            if(countFunction(0, c) == 0) { break; }
            b = ((countFunction(0, a)*countFunction(0, c)) < 0) ? c : b;
            a = ((countFunction(0, b)*countFunction(0, c)) < 0) ? c : a;
            c = (b+a)/2;
        }
        return c;
    }

    public double chordMethod(double a, double b, double epsilon) {
        double xt =0, xp=0;
        do {
            for(int i=0; i<2; i++) {
                xt = a - ((countFunction(0, a) * (b - a)) / ((countFunction(0, b) - countFunction(0, a))));
                if (countFunction(0, xt) == 0) { return xt; }
                b = ((countFunction(0, a) * countFunction(0, xt)) < 0) ? xt : b;
                a = ((countFunction(0, b) * countFunction(0, xt)) < 0) ? xt : a;
                xp = (i == 0) ? xt : xp;
            }
        } while (Math.abs(xt-xp) > epsilon);
        return xt;
    }

    public double tangentMethod(double a, double b, double epsilon) {
        double xt = 0;
        xt = (countFunction(0, a)*countFunction(2, a) > 0) ? a : xt;
        xt = (countFunction(0, b)*countFunction(2, b) > 0) ? b : xt;
        double xp;
        do {
            xp = xt;
            xt = xp - (countFunction(0, xp) / countFunction(1, xp));
        } while (Math.abs(xt - xp) > epsilon);
        return xt;
    }

    public double iterationMethod(double a, double b, double epsilon) {
        double xt = (a+b)/2;
        double xp;
        do {
            xp = xt;
            xt = (-10*g/(-k)) - ((1/(-k))*Math.sin(xp-((10*g)/k)));
        } while (Math.abs(xt - xp) > epsilon);
        return xt;
    }

    public double[] findMax(double [][] matrx, int starti, int startj) {
        double [] res = new double[3];
        res[0] = matrx[starti][startj];
        for(int i = starti; i<matrx[0].length-1; i++) {
            for (int j = startj; j < matrx[0].length - 1; j++) {
                if (Math.abs(matrx[i][j]) >= Math.abs(res[0])) {
                    res[0] = matrx[i][j];
                    res[1] = i;
                    res[2] = j;
                }
            }
        }
        return res;
    }

    public void moveRows(double [][] matrx, int ind1, int ind2) {
        if(ind1 == ind2) { return; }
        double temp;
        for(int i=0; i<matrx[0].length; i++) {
            temp = matrx[ind1][i];
            matrx[ind1][i] = matrx[ind2][i];
            matrx[ind2][i] = temp;
        }
    }

    public void moveCols(double [][] matrx, int ind1, int ind2) {
        if(ind1 == ind2) { return; }
        double temp;
        for(int i=0; i<matrx[0].length-1; i++) {
            temp = matrx[i][ind1];
            matrx[i][ind1] = matrx[i][ind2];
            matrx[i][ind2] = temp;
        }
    }

    public void moveCols(double [] matrx, int ind1, int ind2) {
        if(ind1 == ind2) { return; }
        double temp = matrx[ind1];
        matrx[ind1] = matrx[ind2];
        matrx[ind2] = temp;
    }

    public boolean isZeroColumn(double[][] matrx, int clmnNum) {
        int size = matrx[0].length;
        boolean res = true;
        for(int i=0; i<size-1; i++) {
            if(matrx[i][clmnNum-1] != 0) {
                res = false;
            }
        }
        return res;
    }

    public double[] getOrder(int length) {
        double [] res = new double[length];
        for(int i=0; i<length; i++) {
            res[i] = i;
        }
        return res;
    }

    public double[] GaussMethod(double [][] matrx) {
        int size = matrx[0].length;
        double [] xOrder = getOrder(matrx[0].length-2);
        double [] res = new double[size-1];
        for(int n=0; n<size-1; n++) {
            double [] max = findMax(matrx, n,n);
            if(max[0] == 0) {
                String message = (isZeroColumn(matrx, size)) ? "infinite number of solutions" : "no solutions";
                System.out.println("System has " + message);
                return res;
            }
            moveRows(matrx, (int)max[1], n);
            moveCols(matrx, (int)max[2], n);
            moveCols(xOrder, (int)max[2], n);
            for(int k=n; k<size; k++) {
                matrx[n][k] /= max[0];
            }
            for(int i=n+1; i<size-1; i++) {
                double first = matrx[i][n];
                for(int j=n; j<size; j++) {
                    matrx[i][j] = matrx[i][j] - first*matrx[n][j];
                }
            }
        }
        for(int i = size-2;  i>=0; i--) {
            res[i] = matrx[i][size-1];
            for (int j = res.length - 1; j > i; j--) {
                res[i] -= matrx[i][j] * res[j];
            }
        }
        for(int i=0; i<xOrder.length; i++) {
            moveCols(res, i, (int)xOrder[i]);
        }
        return res;
    }

        public double[] seidelMethod(double x, double y, double epsilon) {
        double [] res = new double[2];
        double prevx, prevy;
        do {
            prevx = x;
            prevy = y;
            x = (4*g - (Math.sin((k+1)*prevx + prevy -4*g)/10))/(k+1);
            y = Math.sin((k+1)*x + prevy - 4*g)/(10*(g+1));
        } while (Math.max(Math.abs(x - prevx), Math.abs(y - prevy)) > epsilon);
        res[0] = x;
        res[1] = y;
        return res;
    }

    public double getDerivative(double xRealValue, int order, int i, int j) {
        DerivativeStructure x = new DerivativeStructure(1, order, 0, xRealValue);
        DerivativeStructure funk1x = x.multiply(k+1).subtract(4*g).add(((x.multiply(k+1).add(xRealValue).subtract(4*g)).sin()).divide(10));
        DerivativeStructure funk1y =  ((x.subtract(4*g).add((k+1)*xRealValue)).divide(10)).subtract(4*g).add((k+1)*xRealValue);
        DerivativeStructure funk2x = (((x.multiply(k+1).add(xRealValue).subtract(4*g)).sin()).divide(10*(k+1))).negate().add(xRealValue);
        DerivativeStructure funk2y = x.subtract(((x.subtract(4*g).add((k+1)*xRealValue)).sin()).divide(10*(k+1)));
        DerivativeStructure [][] func = new DerivativeStructure[][] { { funk1x, funk1y}, { funk2x, funk2y }};
        return func[i][j].getPartialDerivative(order);
    }

    public double[][] getMatrix(double []x, int order) {
        double[][] res = new double[x.length][x.length];
        for(int i=0; i< x.length; i++) {
            for(int j=0; j<x.length; j++) {
                res[i][j] = getDerivative(x[i], order, i, j);
            }
        }
        return res;
    }

    public double[] getFunc(double []x) {
        double [] res = new double[x.length];
        res[0] = (k+1)*x[0] - 4*g + (Math.sin((k+1)*x[0] + x[1] - 4*g))/10;
        res[1] = x[1] - (Math.sin((k+1)*x[0] + x[1] - 4*g))/(10*(k+1));
        return res;
    }

    public double[] newtonMethod(double []x, double epsilon) {
        double [] prevx;
        do {
            prevx = x;
            RealMatrix xMatrix = MatrixUtils.createColumnRealMatrix(x);
            RealMatrix wMatrix = MatrixUtils.createRealMatrix(getMatrix(x, 1));
            RealMatrix fMatrix = MatrixUtils.createColumnRealMatrix(getFunc(x));
            x = xMatrix.subtract(new LUDecomposition(wMatrix).getSolver().getInverse().multiply(fMatrix)).getColumn(0);
        } while (Math.max(Math.abs(x[0] - prevx[0]), Math.abs(x[1] - prevx[1])) > epsilon);
        return x;
    }

    public double[] countFunction(int order, double [] xRealValue) {
        double [] res = new double[xRealValue.length];
        for(int i=0; i<xRealValue.length; i++) {
            res[i] = countFunction(order, xRealValue[i]);
        }
        return res;
    }

    public double [] countFunctionSeidel(double [] xRealValue) {
        double [] res = new double[xRealValue.length];
        for(int i=0; i<res.length; i++) {
            res[i] = (k*xRealValue[i] - 10*g) - Math.sin(xRealValue[i] - 10*g/k);
        }
        return res;
    }

    public double [] countFunctionIterationMethod(double [] xRealValue) {
        double [] res = new double[xRealValue.length];
        for(int i=0; i<res.length; i++) {
            res[i] = (k*xRealValue[i] - 10*g) - Math.sin(xRealValue[i] - 10*g/k);
        }
        return res;
    }

    public double countFunction(int order, double xRealValue) {
        DerivativeStructure x = new DerivativeStructure(1, order, 0, xRealValue);
        DerivativeStructure y = (x.subtract(g*k)).pow(2).add((x.subtract(g*k)).sin());
        return y.getPartialDerivative(order);
    }
}
