/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author ggiliute
 */
public class tab4 extends JFrame implements ActionListener {

    Font fnt = new Font("Monospaced", Font.ITALIC, 14);
    Font bigfnt = new Font("Monospaced", Font.PLAIN, 18);
    Border blackline;

    String[] columnNamesForBisection = {"Iteration", "xlower", "xupper", "xmid", "f(xlower)", "f(xupper)", "approx root"};
    String[] columnNamesForSecant = {"Iteration", "guess 0 ", "guess1", "f(guess0)", "f(guess1)", "approx root"};
    String[] columnNamesForNewton = {"Iteration", "xn", "f(xn)", "f'xn", "approx root"};

    String[] methods = {"Secant", "Newton-Raphson", "Bisection"};
    JComboBox methodList = new JComboBox(methods);
    String[] decimal = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    JComboBox decimalList = new JComboBox(decimal);
    String[] iteration = {"50", "100", "150"};
    JComboBox iterationList = new JComboBox(iteration);

    JPanel mainPanel = new JPanel();
    JPanel leftPanel = new JPanel(new BorderLayout());
    JPanel rightPanel = new JPanel();
    JPanel panelTop = new JPanel();
    JPanel panelBottom = new JPanel();

    JPanel methodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel startPPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel decimalPPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel iterationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel rootPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel tablePanel = new JPanel();
    JPanel graphPanel = new JPanel();
    JTable table;

    JLabel method = new JLabel("select a method: ");
    JLabel startP = new JLabel("input starting point/s: ");
    JLabel decpl = new JLabel("choose decimal place: ");
    JLabel iter = new JLabel("choose max iteration: ");
    JLabel rootL = new JLabel("root for this function is: ");

    JTextField inputA = new JTextField();
    JTextField startP1Field = new JTextField();
    JTextField startP2Field = new JTextField();

    DefaultTableModel dtm = new DefaultTableModel(0, 0);

    double x1, startP1, startP2, root;
    int decimalPl, maxIt;
    String methodName;

    JButton b_find = new JButton("solve!");
    ArrayList<JTextField> listOfFields = new ArrayList<>();

    public tab4() {
        mainPanel = createAPanel();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void specifyPanel() {

        JLabel l_function1 = new JLabel("f(x)=e^x +");
        JLabel l_function2 = new JLabel("x");

        l_function1.setFont(bigfnt);
        l_function1.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_function2.setFont(bigfnt);
        l_function2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel(new FlowLayout());

        inputA.setPreferredSize(new Dimension(40, 30));

        inputPanel.add(l_function1);
        inputPanel.add(inputA);
        inputPanel.add(l_function2);

        inputPanel.setMaximumSize(inputPanel.getPreferredSize());
        leftPanel.add(inputPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("find".equals(e.getActionCommand())) {
            try // tries to parse the data user has input         
            {
                methodName = (String) methodList.getSelectedItem();
                x1 = Double.parseDouble(inputA.getText());
                startP1 = Double.parseDouble(startP1Field.getText());
                decimalPl = Integer.valueOf((String) decimalList.getSelectedItem());
                maxIt = Integer.valueOf((String) iterationList.getSelectedItem());

                if (methodName.equals("Newton-Raphson")) {
                    solve(methodName, x1, startP1, decimalPl, maxIt, -1);
                } else {
                    startP2 = Double.parseDouble(startP2Field.getText());
                    solve(methodName, x1, startP1, decimalPl, maxIt, startP2);
                }
            } catch (NumberFormatException ex) //if input cannot be parsed displays error message
            {
                JOptionPane.showMessageDialog(null, "Check your inputs and try again.");
                inputA.setText("");
                startP1Field.setText("");
                startP2Field.setText("");
            }
        }
        if ("methodName".equals(e.getActionCommand())) {
            //disables text field for second point as Newton-Raphson only needs one 
            methodName = (String) methodList.getSelectedItem();
            if (methodName.equals("Newton-Raphson")) {
                startP2Field.setText("");
                startP2Field.setEnabled(false);
            } else {
                startP2Field.setEnabled(true);
            }
        }
    }

    private void solve(String name, double a, double sP1, int dp, int it, double sP2) {
        if (methodName.equals("Bisection")) {
            BisectionMethod BM = new BisectionMethod(a, sP1, sP2, dp, it);
            double[][] result = BM.getResult();
            root = BM.getRoot();
            displayBisectionData(result, root);
        }
        if (methodName.equals("Newton-Raphson")) {
            NewtonRhapsonMethod NM = new NewtonRhapsonMethod(a, sP1, dp, it);
            double[][] result = NM.getResult();
            root = NM.getRoot();
            displayNewtonData(result, root);
        }
        if (methodName.equals("Secant")) {
            SecantMethod SM = new SecantMethod(a, sP1, sP2, dp, it);
            double[][] result = SM.getResult();
            root = SM.getRoot();
            displaySecantData(result, root);
        }

    }

    private void displayBisectionData(double[][] result, double root) {
        tablePanel.removeAll();  // removes components from panel each time "solve" button is pressed so new table could be displayed
        graphPanel.removeAll();  // removes components from panel each time "solve" button is pressed so new graph could be displayed
        rootPanel.removeAll();   // removes components from panel each time "solve" button is pressed so new root could be displayed
        JLabel rootStr = new JLabel(Double.toString(root)); //converts root's value to string so it can be added to Jlabel;

        //adds components to root panel
        rootPanel.add(rootL);
        rootPanel.add(rootStr);
        rootPanel.revalidate();
        rootPanel.repaint();
        rootPanel.updateUI();

        leftPanel.add(rootPanel);

        //setting preferences for the table
        String[][] data = new String[100][7];
        table = new JTable(data, columnNamesForBisection);
        dtm.setColumnIdentifiers(columnNamesForBisection);
        table.setModel(dtm);
        table.setDefaultEditor(Object.class, null); // makes table not-editable
        table.setFillsViewportHeight(true);
        table.setGridColor(Color.gray);

        if (dtm.getRowCount() > 0) { // removes any previous data in the table
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }
        for (int i = 0; i < result.length; i++) {
            String[] row = new String[7];
            row[0] = String.valueOf((i + 1));
            for (int j = 0; j < 6; j++) {
                row[j + 1] = String.valueOf(result[i][j]);
            }
            dtm.addRow(row);
        }
        // sets new preferences for tablePanel as all preferences have been removed before
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.setPreferredSize(new Dimension(790, 200));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //revokes panel
        tablePanel.revalidate();
        tablePanel.repaint();
        tablePanel.updateUI();

        panelBottom.add(tablePanel);
        pack();
        setVisible(true);

        //graph plotter. Library downloaded from JFreeChart
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries funcPlot = new XYSeries("f(x)");
        XYSeries rootPlot = new XYSeries("root");

        //points that needs to be plot are added to series
        for (int i = 0; i < result.length; i++) {
            funcPlot.add(result[i][0], result[i][3]);
            funcPlot.add(result[i][1], result[i][4]);
            funcPlot.add(result[i][2], result[i][5]);
            rootPlot.add(result[i][2], result[i][5]);

        }

        dataset.addSeries(funcPlot);
        dataset.addSeries(rootPlot);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph for the Exponential function, using Bisection Method",
                "x",
                "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        renderGraph(chart);

    }

    private void displayNewtonData(double[][] result, double root) {
        tablePanel.removeAll();  // removes components from panel each time "solve" button is pressed so new table could be displayed
        graphPanel.removeAll();  // removes components from panel each time "solve" button is pressed so new graph could be displayed
        rootPanel.removeAll();   // removes components from panel each time "solve" button is pressed so new root could be displayed
        JLabel rootStr = new JLabel(Double.toString(root)); //converts root's value to string so it can be added to Jlabel;

        //adds components to root panel
        rootPanel.add(rootL);
        rootPanel.add(rootStr);
        rootPanel.revalidate();
        rootPanel.repaint();
        rootPanel.updateUI();

        leftPanel.add(rootPanel);

        //setting preferences for the table
        String[][] data = new String[100][7];
        table = new JTable(data, columnNamesForNewton);
        dtm.setColumnIdentifiers(columnNamesForNewton);
        table.setModel(dtm);
        table.setDefaultEditor(Object.class, null); // makes table not-editable
        table.setFillsViewportHeight(true);
        table.setGridColor(Color.gray);

        if (dtm.getRowCount() > 0) { // removes any previous data in the table
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }
        for (int i = 0; i < result.length; i++) //adds new entries to the table
        {
            String[] row = new String[5];
            row[0] = String.valueOf((i + 1));
            for (int j = 0; j < 4; j++) {
                row[j + 1] = String.valueOf(result[i][j]);
            }
            dtm.addRow(row);
        }
        // sets new preferences for tablePanel as all components have been removed before
        table.setDefaultEditor(Object.class, null); // makes table not-editable
        table.setFillsViewportHeight(true);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.setPreferredSize(new Dimension(790, 200));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        tablePanel.revalidate();
        tablePanel.repaint();
        tablePanel.updateUI();
        panelBottom.add(tablePanel);
        pack();
        setVisible(true);

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries funcPlot = new XYSeries("f(x)");
        XYSeries rootPlot = new XYSeries("root");
        for (int i = 0; i < result.length; i++) {
            funcPlot.add(result[i][0], result[i][2]);
            rootPlot.add(result[i][0], result[i][2]);

        }
        //series are added to data set;
        dataset.addSeries(funcPlot);
        dataset.addSeries(rootPlot);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph for the Exponential function, using Newton-Raphson Method",
                "x",
                "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        renderGraph(chart);

    }

    private void displaySecantData(double[][] result, double root) {

        tablePanel.removeAll();  // removes components from panel each time "solve" button is pressed so new table could be displayed
        graphPanel.removeAll();  // removes components from panel each time "solve" button is pressed so new graph could be displayed
        rootPanel.removeAll();   // removes components from panel each time "solve" button is pressed so new root could be displayed
        JLabel rootStr = new JLabel(Double.toString(root)); //converts root's value to string so it can be added to Jlabel;

        //adds components to root panel
        rootPanel.add(rootL);
        rootPanel.add(rootStr);
        rootPanel.revalidate();
        rootPanel.repaint();
        rootPanel.updateUI();

        leftPanel.add(rootPanel);

        //setting preferences for the table
        String[][] data = new String[100][7];
        table = new JTable(data, columnNamesForSecant);
        dtm.setColumnIdentifiers(columnNamesForSecant);
        table.setModel(dtm);
        table.setDefaultEditor(Object.class, null); // makes table not-editable
        table.setFillsViewportHeight(true);
        table.setGridColor(Color.gray);

        if (dtm.getRowCount() > 0) //removes any previous entries in the table
        {
            for (int i = dtm.getRowCount() - 1; i > -1; i--) {
                dtm.removeRow(i);
            }
        }
        for (int i = 0; i < result.length; i++) // adds new entries to the table
        {
            String[] row = new String[6];
            row[0] = String.valueOf((i + 1));
            for (int j = 0; j < 5; j++) {
                row[j + 1] = String.valueOf(result[i][j]);
            }
            dtm.addRow(row);
        }
        // sets new preferences for tablePanel as all preferences have been removed before
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.setPreferredSize(new Dimension(790, 200));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //revokes panel
        tablePanel.revalidate();
        tablePanel.repaint();
        tablePanel.updateUI();

        panelBottom.add(tablePanel);
        pack();
        setVisible(true);

        //graph plotter. Library downloaded from JFreeChart
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries funcPlot = new XYSeries("f(x)");
        XYSeries rootPlot = new XYSeries("root");

        //points that needs to be plot are added to series
        for (int i = 0; i < result.length; i++) {
            funcPlot.add(result[i][0], result[i][2]);
            funcPlot.add(result[i][1], result[i][3]);
            rootPlot.add(result[i][0], result[i][2]);
            rootPlot.add(result[i][1], result[i][3]);
        }
        //series are added to data set;
        dataset.addSeries(funcPlot);
        dataset.addSeries(rootPlot);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph for the Exponential function, using Secant Method",
                "x",
                "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        renderGraph(chart);
    }

    public void renderGraph(JFreeChart chart) // sets graphs preferences. Library downloaded from JFreeChart
    {
        //creating new chart panel, setting its preferences
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(700, 400));
        final XYPlot plot = chart.getXYPlot();

        //creating renderer, setting its preferences
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);

        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesLinesVisible(1, false);

        //adding components to graph, setting its preferences
        plot.setRenderer(renderer);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.white);

        //setting preferences for the panel as they have been removed previously
        graphPanel.add(panel);
        graphPanel.setPreferredSize(new Dimension(790, 420));
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //revoking the panel, so new graph can be visible
        graphPanel.revalidate();
        graphPanel.repaint();
        panelTop.add(graphPanel);
        pack();
        setVisible(true);
    }

    private JPanel createAPanel() { // method that creates the generic panel, sets its components specs

        mainPanel.setPreferredSize(new Dimension(1100, 630));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(790, 630));
        rightPanel.add(panelTop);
        rightPanel.add(panelBottom);

        leftPanel.setPreferredSize(new Dimension(300, 500));
        leftPanel.setBorder(new TitledBorder(blackline, "Select your preferences below: "));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        panelBottom.setPreferredSize(new Dimension(790, 200));
        panelTop.setPreferredSize(new Dimension(790, 420));

        b_find.setActionCommand("find");
        b_find.addActionListener(this);
        b_find.setEnabled(false);
        b_find.setFont(fnt);

        buttonvalidation();

        startP1Field.setPreferredSize(new Dimension(40, 30));
        startP2Field.setPreferredSize(new Dimension(40, 30));

        methodPanel.add(method);
        methodPanel.add(methodList);

        startPPanel.add(startP);
        startPPanel.add(startP1Field);
        startPPanel.add(startP2Field);

        decimalPPanel.add(decpl);
        decimalPPanel.add(decimalList);

        iterationPanel.add(iter);
        iterationPanel.add(iterationList);

        methodPanel.setMaximumSize(methodPanel.getPreferredSize());
        startPPanel.setMaximumSize(startPPanel.getPreferredSize());
        decimalPPanel.setMaximumSize(decimalPPanel.getPreferredSize());
        iterationPanel.setMaximumSize(iterationPanel.getPreferredSize());

        methodList.setActionCommand("methodName");
        methodList.addActionListener(this);

        specifyPanel();

        leftPanel.add(methodPanel);
        leftPanel.add(startPPanel);
        leftPanel.add(decimalPPanel);
        leftPanel.add(iterationPanel);
        leftPanel.add(b_find);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        pack();
        return mainPanel;
    }

    private void buttonvalidation() // method that adds DocumentListener to all text fields for later checking 
    {
        listOfFields.add(inputA);
        listOfFields.add(startP1Field);

        for (JTextField tf : listOfFields) {
            tf.getDocument().addDocumentListener(listener);
        }
    }

    DocumentListener listener = new DocumentListener() {
        @Override
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            boolean enabled = true;
            for (JTextField tf : listOfFields) {
                if (tf.getText().isEmpty()) {
                    enabled = false;
                }
            }
            b_find.setEnabled(enabled); // button "solve" is only enabled when all input fields have been filled
        }
    };
}
