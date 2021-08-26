public class Main {

  public static void showTable(double [] x, double [] y) {
    System.out.printf("%-10s|", "x");
    for(double xi: x) {
      System.out.printf("%-10.3f|", xi);
    }
    System.out.println();
    for(int i=0; i<x.length*9; i++) {
      System.out.print("―");
    }
    System.out.println();
    System.out.printf("%-10s|", "f(x)");
    for(double yi: y) {
      System.out.printf("%-10.3f|", yi);
    }
    System.out.print("\n\n");
  }

  public static double [] getMas(int size) {
    double [] xs = new double[size];
    int start = -20;
    for(int i=0; i<size; i++) {
      xs[i] = start;
      start += 0.001;
    }
    return xs;
  }

    public static void main(String[] args) {

      double [] xValues1 = { -19.0, -17.0, -16.0, -14.0 };
      double [] xValues2 = { -18.0, -17.0, -16.0, -15.0 };

      double [] newXValues1 = { -18.8, -16.5, -13.3 };
      double [] newXValues2 = { -17.7, -16.5, -14.2 };
      double [] newXValues3 = { -19,000, -16.5, -15.1 };

      StationarySystemsModeling ob1 = new StationarySystemsModeling(xValues1);
      StationarySystemsModeling ob2 = new StationarySystemsModeling(xValues2);

      double [] xs = getMas(1000);

      //ex1
      showTable(xValues1, StationarySystemsModeling.countFunction(xValues1));
      System.out.println("Lagrange interpolation");
      ob1.showDifference(newXValues1, ob1.lagrangeInterpolation(newXValues1));
      Graph.drawGraph("Lagrangue", "y = sin(x) + x", xs, StationarySystemsModeling.countFunction(xs), newXValues1, ob1.lagrangeInterpolation(newXValues1));

      //ex1.1
      System.out.println("Polynom of Newton for uneven intervals");
      ob1.showDifference(newXValues1, ob1.polynomNewtonUnevenIntervals(newXValues1));
      Graph.drawGraph("polynomNewtonUnevenIntervals", "y = sin(x) + x", xs, StationarySystemsModeling.countFunction(xs), newXValues1, ob1.polynomNewtonUnevenIntervals(newXValues1));

      //ex2
      System.out.println("Chebyshev polynomials");
      ob1.showDifference(newXValues1, ob1.PolinomChebisheva(-19, -14, newXValues1));
      Graph.drawGraph("PolinomChebisheva", "y = sin(x) + x", xs, StationarySystemsModeling.countFunction(xs), newXValues1, ob1.PolinomChebisheva(-19, -14, newXValues1));

      //ex3
      System.out.println("Polynom of Newton for even intervals");
      showTable(xValues2, StationarySystemsModeling.countFunction(xValues2));
      ob1.showDifference(newXValues2, ob1.polynomNewtonEvenIntervals(newXValues2));
      Graph.drawGraph("polynomNewtonEvenIntervals", "y = sin(x) + x", xs, StationarySystemsModeling.countFunction(xs), newXValues2, ob1.polynomNewtonEvenIntervals(newXValues2));

      //ex4
      System.out.println("Spline");
      showTable(xValues1, StationarySystemsModeling.countFunction(xValues1));
      ob1.showDifference(newXValues3, ob1.spline(newXValues3));
      Graph.drawGraph("spline", "y = sin(x) + x", xs, StationarySystemsModeling.countFunction(xs), newXValues3, ob1.spline(newXValues3));

      //ex5
      System.out.println("Least Squares Method");
      showTable(xValues2, StationarySystemsModeling.countFunction(xValues2));
      double [] newYValues = ob2.leastSquaresMethod(newXValues1);
      ob2.showDifference(newXValues1, newYValues);
      System.out.println("Linear function: " + ob2.getFuncName());
      Graph.drawGraph("y = sin(x) + x", ob2.getFuncName(), newXValues1, newYValues, xValues1, StationarySystemsModeling.countFunction(xValues1));


      //ex6
      double [] xValues4 = { 4.99, 4.99, 2.33, 8.32 };
      double res = StationarySystemsModeling.leastSquaresMethod(DataHandler.getData("data.txt") , xValues4);
      System.out.println("Least Squares Method");
      System.out.printf("%-20s %-20s %-20s\n", "Calculated value", "Function value", "Difference");
      System.out.printf("%-20.3f %-20.3f %-20.3f\n", res, 54.17, Math.abs(54.17 - res));
  }
}
