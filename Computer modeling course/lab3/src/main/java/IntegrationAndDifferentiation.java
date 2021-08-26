public class IntegrationAndDifferentiation {

    public IntegrationAndDifferentiation() {}

    public static double countFunction(double x) {
        return Math.pow(x, 3) + x;
    }

    public static double [] countFunction(double [] x) {
        double [] res = new double[x.length];
        for(int i=0; i<x.length; i++) {
            res[i] = countFunction(x[i]);
        }
        return res;
    }

    public double incrementsSequence(double x, double epsilon) {
        double d=0, prevd, h, i=0;
        do {
            prevd = d;
            h = 0.5 / Math.pow(5, i);
            d = ((countFunction(x+h) - countFunction(x)) / h);
            i++;
        } while (Math.abs(d - prevd) > epsilon);
        System.out.printf("Iterations: %.0f\n", i);
        return d;
    }

    public double centeredDifference(double x, double epsilon) {
        double d=0, prevd, h, i=0;
        do {
            prevd = d;
            h = 0.5 / Math.pow(5, i);
            d = ((countFunction(x+h) - countFunction(x-h)) / (2*h));
            i++;
        } while (Math.abs(d - prevd) > epsilon);
        System.out.printf("Iterations: %.0f\n", i);
        return d;
    }

    public double quadraticInterpolation(double x, double [] xVal, double [] yVal) {
        StationarySystemsModeling ob = new StationarySystemsModeling(xVal, yVal);
        double h = xVal[1]-xVal[0];
        double q = (x - xVal[0])/h;
        double res = 1/h * (ob.countDelta(0, 1) + ((2*q - 1)/2)*ob.countDelta(0,2));
        return res;
    }

     public double leftRectanglesMethod(double a, double b, double epsilon, int n) {
         n = (n <= 0 ) ? 100 : n;
         double h, prevRes, res=0;
         do {
             h  = (b-a)/n;
             prevRes = res;
             res = 0;
             for(int i=0; i<n; i++) {
                 res += countFunction(a+i*h);
             }
             res *= h;
             n *= 2;
         } while(Math.abs(res - prevRes) > epsilon);
         return res;
     }

    public double trapezoidalMethod(double a, double b, double epsilon, int n) {
        n = (n <= 0 ) ? 100 : n;
        double h, prevRes, res=0;
        do {
            h  = (b-a)/n;
            prevRes = res;
            res = ((countFunction(a) + countFunction(b))/2);
            for(int i=1; i<n; i++) {
                res += countFunction(a+i*h);
            }
            res *= h;
            n *= 2;
        } while(Math.abs(res - prevRes) > epsilon);
        return res;
    }

    public double simpsonsMethod(double a, double b, double epsilon, int n) {
        n = (n <= 0 ) ? 100 : n;
        double h, prevRes, res=0;
        do {
            h  = (b-a)/n;
            prevRes = res;
            res = countFunction(a) + countFunction(b);
            for(int i=1; i<n; i++) {
                res += (i%2 == 0) ? countFunction(a+i*h)*2 : countFunction(a+i*h)*4;
            }
            res *= (h/3);
            n *= 2;
        } while(Math.abs(res - prevRes) > epsilon);
        return res;
    }
}
