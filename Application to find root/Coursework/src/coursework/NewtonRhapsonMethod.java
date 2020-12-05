/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author ggiliute
 */
public class NewtonRhapsonMethod {

    double a, b, c, startP, root;
    LinkedList<double[]> list;
    double[][] result;
    int decimalpl, it;

    public NewtonRhapsonMethod(double a, double b, double c, double stP, int dp, int it) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.decimalpl = dp;
        this.startP = stP;
        this.it = it;
        this.list = new LinkedList();
        solveQuadratic(a, b, c, decimalpl, startP, it, list);
        this.result = toArray(list);
    }

    public NewtonRhapsonMethod(double a, double b, double st1, int dp, int it) {
        this.a = a;
        this.b = b;
        this.decimalpl = dp;
        this.it = it;
        this.startP = st1;
        this.list = new LinkedList();
        solveLog(a, b, decimalpl, startP, it, list);
        this.result = toArray(list);
    }

    public NewtonRhapsonMethod(double a, double st1, int dp, int it) {
        this.a = a;
        this.decimalpl = dp;
        this.startP = st1;
        this.it = it;
        this.list = new LinkedList();
        solveExponential(a, decimalpl, startP, it, list);
        this.result = toArray(list);
    }

    public double getRoot() //getter, returns the root of the equation
    {
        return root;
    }

    public double[][] getResult() {
        return result;
    }

    private void solveQuadratic(double a, double b, double c, int decimalpl, double startP, int it, LinkedList list) {
        double xold, fxold, fdashxold, diff;
        double xnew = 0;
        int iteration = 0;
        list.clear();// clears the list if user wants to check other values straight after checking

        xold = startP; //assigning starting point to xold so we could proceed with calculations
        list.addFirst(xold); //adds first item to LinkedList
        try // tries to do all the calculations
        {
            do {
                
                
                if (xold == 0 && b == 0 && c == 0) {
                    JOptionPane.showMessageDialog(null, "whoops, starting point cannot be 0, for this function!");// if the function input is x^2, starting point cannot be 0,as the y - value goes to infinity
                    break;
                }

                fxold = a * xold * xold + b * xold + c; //calculates the functions value at point x  
                fdashxold = 2 * a * xold + b; //calculates the derivative of function at point x  

           
             if (Double.isNaN(fdashxold)||Double.isNaN(fxold)||Double.isInfinite(xold)||Double.isInfinite(fxold))  // checks if the point goes to infinity, if yes - breaks;
            {
                JOptionPane.showMessageDialog(null, "one of the points goes to infinity, calculations can't be done");
                break;
            }
                
                if ((fxold / fdashxold) == 0) // checks if divident is zero, if yes - error message pops up
                {
                    JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
                    break;
                }

                xnew = xold - (fxold / fdashxold); //the values obtained are put in Newton's method
                diff = (Math.abs(xnew - xold)) * 100;// gets the difference of old value and new, multiplies it by 10, iteration would be done more times, thus getting more accurate results
                iteration = iteration + 1;//increases iteration by one

                if (iteration == it) //check whether the maximum amount of iteration has been reached if yes - stops
                {
                    break;
                }

                //adds values to the list
                list.add(fxold);
                list.add(fdashxold);
                list.add(xnew);
                xold = xnew; // new value is assigned to xold 
                list.add(xold);

            } while (diff > decimalpl);
            {
                root = round(xnew, decimalpl);
            }

        } catch (Exception e) // if whilst doing calculations error occurs - a message pops up to inform the user
        {
            JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
        }

    }

    private void solveLog(double a, double b, int decimalpl, double startP, int it, LinkedList list) {
        double xold, fxold, fdashxold, diff;
        double xnew = 0;
        int iteration = 0;
        list.clear();// clears the list if user wants to check other values straight after checking

        xold = startP; //assigning starting point to xold so we could proceed with calculations
        list.addFirst(xold); //adds first item to LinkedList      
        do {
            fxold = (Math.log(xold + a) + b); //calculates the functions value at point x  
            fdashxold = 1 / (xold + a); //calculates the derivative of function at point x 

             if (Double.isNaN(fdashxold)||Double.isNaN(fxold)||Double.isInfinite(xold)||Double.isInfinite(fxold))  // checks if the point goes to infinity, if yes - breaks;
            {
                JOptionPane.showMessageDialog(null, "The point is undefined, calculations cannot be done");
                break;
            }

            if ((fxold / fdashxold) == 0) // checks if divident is zero, if yes - error message pops up
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
                break;
            }
            xnew = xold - (fxold / fdashxold); //the values obtained are used in Newton's method

            diff = (Math.abs(xnew - xold)) * 100;// gets the difference of old value and new, multiplies it by 10, iteration would be done more times, thus getting more accurate results
            iteration = iteration + 1;//increases iteration by one

            if (iteration == it) //check whether the maximum amount of iteration has been reached if yes - stops
            {
                break;
            }

            xold = xnew; // new value is assigned to xold 
            //adds values to the list
            list.add(xold);
            list.add(fxold);
            list.add(fdashxold);
        } while (diff > decimalpl);
        {
            root = round(xnew, decimalpl);
        }
    }

    private void solveExponential(double a, int decimalpl, double startP, int it, LinkedList list) {
        double xold, fxold, fdashxold, diff;
        double xnew = 0;
        int iteration = 0;
        list.clear();// clears the list if user wants to check other values straight after checking

        xold = startP; //assigning starting point to xold so we could proceed with calculations
        list.addFirst(xold); //adds first item to LinkedList  
        do {
            fxold = (Math.exp(xold) + a * xold); //calculates the functions value at point x  
            fdashxold = Math.exp(xold) + a;//calculates the derivative of function at point x  

            if (Double.isNaN(fdashxold)||Double.isNaN(fxold)||Double.isInfinite(xold)||Double.isInfinite(fxold))  // checks if the point goes to infinity, if yes - breaks;
            {
                JOptionPane.showMessageDialog(null, "The point goes to inifinity, calculations can't be done");
                break;
            }

            if ((fxold / fdashxold) == 0) // checks if divident is zero, if yes - error message pops up
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
                break;
            }
            xnew = xold - (fxold / fdashxold); //the values obtained are used in Newton's method         

            diff = (Math.abs(xnew - xold)) * 100;// gets the difference of old value and new, multiplies it by 10, iteration would be done more times, thus getting more accurate results
            iteration = iteration + 1;//increases iteration by one

            if (iteration == it) {
                break;
            }
            xold = xnew;
            //adds values to the list
            list.add(xold);
            list.add(fxold);
            list.add(fdashxold);
        } while (diff > decimalpl);
        {
            root = round(xnew, decimalpl);
        }
    }

    private double[][] toArray(LinkedList list) {
        if (list.isEmpty()) {
            result = new double[0][0];
            return result;
        } else {
            int item = 0; // counter to get trough all the items in the list
            int row = list.size() / 4; // getting number of rows needed 
            int col = 4; //collumn number is four as in the table there is four collumns
            result = new double[row][col]; // specifying array's size      
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (item == list.size()) {
                        break; //if all the items have been added to the array - break;
                    }
                    result[i][j] = (double) list.get(item); //adds item from linkedList to array
                    item++;
                }
            }

        }
        return result;
    }

    private double round(double xvalue, int decimalpl) // rounds the values to the decimal place user has selected
    {
        xvalue = (Math.round(xvalue * Math.pow(10, decimalpl))) / Math.pow(10, decimalpl);
        return xvalue;
    }
}
