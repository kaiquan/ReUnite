package View.RIM.Components;

/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2011, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ------------------
 * PieChartDemo1.java
 * ------------------
 * (C) Copyright 2003-2011, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1, copied from the demo collection that ships with
 *               the JFreeChart Developer Guide (DG);
 *
 */


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChart extends JPanel {

	
    private static final long serialVersionUID = 1L;

    {
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }
    int attending;
    int notAttending;
    int notSure;
    double total;
    
    public PieChart(int attending, int notAttending, int notSure, double total) {
        
    	this.attending = attending;
        this.notAttending = notAttending;
        this.notSure = notSure;
        this.total = total;
        
    	setLayout(new BorderLayout(0, 0));
        add(createDemoPanel());
        setVisible(true);
    }


    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Not Attendng", new Double(notAttending/total*100));
        dataset.setValue("Not Sure", new Double(notSure/total*100));
        dataset.setValue("Attending", new Double(attending/total*100));
        return dataset;
    }

 
    private JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            null,  // chart title
            dataset,             // data
            true,                // include legend
            true,
            false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");
        return chart;

    }

 
    public JPanel createDemoPanel() 
    {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setMaximumSize(new Dimension(20, 200));
        panel.setSize(150, 150);
        panel.setMouseWheelEnabled(true);
        return panel;
    }


    public static void main(String[] args) {


    }

}
