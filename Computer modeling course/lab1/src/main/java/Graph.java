import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Graph extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;
    private String title1;
    private String title2;

    public Graph(String title1, double [] x, double [] y) {
        super(title1);
        this.title1 = title1;
    }

    public Graph(String title1, String title2, double [] x, double [] y, double [] xp, double [] yp) {
        super(title1);
        this.title1 = title1;
        this.title2 = title2;
        createSetAndPanel(x, y, xp, yp);
    }

    public void createSetAndPanel(double [] x, double [] y, double [] xp, double [] yp) {
        XYPlot plot = new XYPlot();

        XYDataset collection1 = createDataset(title1, xp, yp);
        XYItemRenderer renderer1 = new XYLineAndShapeRenderer(false, true);	// Shapes only
        ValueAxis domain1 = new NumberAxis("Domain1");
        ValueAxis range1 = new NumberAxis("Range1");

// Set the scatter data, renderer, and axis into plot
        plot.setDataset(0, collection1);
        plot.setRenderer(0, renderer1);
        plot.setDomainAxis(0, domain1);
        plot.setRangeAxis(0, range1);

// Map the scatter to the first Domain and first Range
        plot.mapDatasetToDomainAxis(0, 0);
        plot.mapDatasetToRangeAxis(0, 0);

        // Create the line data, renderer, and axis
        XYDataset collection2 = createDataset(title2, x, y);
        XYItemRenderer renderer2 = new XYLineAndShapeRenderer(true, false);	// Lines only
        ValueAxis domain2 = new NumberAxis("Domain2");
        ValueAxis range2 = new NumberAxis("Range2");

// Set the line data, renderer, and axis into plot
        plot.setDataset(1, collection2);
        plot.setRenderer(1, renderer2);
        plot.setDomainAxis(1, domain2);
        plot.setRangeAxis(1, range2);

// Map the line to the second Domain and second Range
        plot.mapDatasetToDomainAxis(1, 0);
        plot.mapDatasetToRangeAxis(1, 0);


// Create the chart with the plot and a legend
        JFreeChart chart = new JFreeChart("Multi Dataset Chart", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public XYDataset createDataset(String title, double [] x, double [] y) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries(title);
        for(int i=0; i<x.length; i++) {
            series.add(x[i], y[i]);
        }

        //Add series to dataset
        dataset.addSeries(series);

        return dataset;
    }

    public static void drawGraph(String name1, String name2, double [] x, double [] y, double [] xp, double [] yp) {
        Graph example = new Graph(name1, name2, x, y, xp, yp);
        example.setSize(800, 600);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
    }
}