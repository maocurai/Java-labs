public class Main {

    public static void main(String[] args) throws Exception {

        SLARsolving ob = new SLARsolving(2, 9);

        double [] xValues;
        xValues = Main.getXvalues(15, 0.06, 80);
        System.out.println("Метод ділення навпіл");
        showDifference(MathLibraries.bisectionMethod(16.9, 17.5), ob.halfDivisionMethod(16.9, 17.5, 0.01));
        showDifference(MathLibraries.bisectionMethod(17.5, 18.1), ob.halfDivisionMethod(17.5, 18.1, 0.01));
        new DrawGraph("(x-18)^2 + sin(x-18) = 0", xValues, ob.countFunction(0, xValues));

        System.out.println("Метод хорд");
        showDifference(MathLibraries.secantMethod(16.9, 17.5), ob.chordMethod(16.9, 17.5, 0.01));
        showDifference(MathLibraries.secantMethod(17.5, 18.1), ob.chordMethod(17.5, 18.1, 0.01));

        System.out.println("Метод дотичних");
        showDifference(MathLibraries.newtonRaphson(16.9, 17.5), ob.tangentMethod(16.9, 17.5, 0.01));
        showDifference(MathLibraries.newtonRaphson(17.5, 18.1), ob.tangentMethod(17.5, 18.1, 0.01));


        xValues = Main.getXvalues(-5, 10, 5);
        System.out.println("Метод ітерацій");
        System.out.printf("%.4f\n\n", ob.iterationMethod(0, 5, 0.01));
        new DrawGraph("(9x - 20) - sin(x - 20/9) = 0", xValues, ob.countFunctionIterationMethod(xValues));

        System.out.println("Метод Гауса");
        double [][] matrx = new double[][] {{3, 4, 5, 9}, {6, 8, -3, 10}, {9, 2, -3, 11}};
        double [] res = ob.GaussMethod(matrx);
        for(int i=0; i<res.length; i++) {
            System.out.printf("x%d: %.4f\n", i, res[i]);
        }

        xValues = Main.getXvalues(-5, 0.06, 80);
        System.out.println("\nМетод Зейделя");
        for(double i: ob.seidelMethod(1, 0.3, 0.01)) {
            System.out.printf("%.4f\n", i);
        }

        System.out.println("\nМетод Ньютона");
        double [] x = new double[] { 0.5, 0.5 };
        res = ob.newtonMethod(x, 0.01);
        for(int i=0; i<res.length; i++) {
                System.out.printf("%.4f\n", res[i]);
        }

    }

    public static double[] getXvalues(double start, double delta, int points) {
        double [] xValues = new double[points];
        for(int i=0; i<points; i++) {
            xValues[i] = start;
            start += delta;
        }
        return xValues;
    }

    public static void showDifference(double [] funkVal, double [] myVal) {
        System.out.printf("%-10s|", "x");
        for(double xi: funkVal) {
            System.out.printf("%-10.3f|", xi);
        }
        System.out.println();
        for(int i=0; i<funkVal.length*9; i++) {
            System.out.print("―");
        }
        System.out.println();
        System.out.printf("%-10s|", "f(x)");
        for(double xi: myVal) {
            System.out.printf("%-10.3f|", xi);
        }
        System.out.println();
        for(int i=0; i<funkVal.length*9; i++) {
            System.out.print("―");
        }
        for(int i=0; i<funkVal.length; i++) {
            System.out.printf("%-10.3f|", funkVal[i]-myVal[i]);
        }
        System.out.print("\n\n");
    }

    public static void showDifference(double funkVal, double myVal) {
        System.out.printf("%s %-10.4f\n", "math libraries:", funkVal);
        System.out.printf("%s %-10.4f\n", "calculated value:", myVal);
        System.out.printf("%s %-10.4f\n\n", "error:", funkVal-myVal);
    }
}
