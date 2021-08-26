import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;

public class DrawGraph {

    public DrawGraph(String name, double [] xVal, double [] yVal) {
        XYChart chart = QuickChart.getChart(name, "X", "Y", name, xVal, yVal);
        getSettings(chart);
        new SwingWrapper(chart).displayChart();
    }

    public DrawGraph(String name1, double [] xVal, double [] yVal, String name2, double [] xVal2, double [] yVal2) {
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", name1, xVal, yVal);
        chart.addSeries(name2, xVal2, yVal2);
        getSettings(chart);
        new SwingWrapper(chart).displayChart();
    }

    public void getSettings(XYChart chart) {
        chart.getStyler().setCursorEnabled(true);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);
        chart.getStyler().setZoomEnabled(true);
    }
}
