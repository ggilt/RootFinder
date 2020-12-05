/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import javax.swing.JOptionPane;

/**
 *
 * @author ggiliute
 */
public class BisectionMethod {

    double a, b, c, startP1, startP2, root;
    int decimalpl, it;
    double[][] result;

    // constructors to assign values for corresponding local variables and to use the right method
    public BisectionMethod(double a, double b, double c, double st1, double st2, int dp, int it) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.decimalpl = dp;
        this.startP1 = st1;
        this.startP2 = st2;
        this.it = it;
        this.result = new double[it][6];
        solveQuadratic(a, b, c, decimalpl, startP1, startP2, it, result);
    }

    public BisectionMethod(double a, double b, double st1, double st2, int dp, int it) {
        this.a = a;
        this.b = b;
        this.decimalpl = dp;
        this.startP1 = st1;
        this.startP2 = st2;
        this.result = new double[it][6];
        solveLog(a, b, decimalpl, startP1, startP2, it, result);
    }

    public BisectionMethod(double a, double st1, double st2, int dp, int it) {
        this.a = a;
        this.decimalpl = dp;
        this.startP1 = st1;
        this.startP2 = st2;
        this.it = it;
        this.result = new double[it][6];
        solveExponential(a, decimalpl, startP1, startP2, it, result);
    }

    public double[][] getResult() //getter, returns the list of calculation breakdown
    {
        return result;
    }

    public double getRoot() {
        return root;
    }

    private void solveQuadratic(double a, double b, double c, int decimalpl, double startP1, double startP2, int it, double[][] result) // method to solve the quadratic equation
    {                                                                                                                                     //a lot of code is taken out of lectures slides

        double xlower, xupper, xmid, fxlower, fxupper, fxnew;
        double oldxmid = 0;
        int iteration = 0;

        xlower = Double.min(startP1, startP2);  // assigns smaller value to lower point
        xupper = Double.max(startP1, startP2);  // assigns greater value to upper point

        fxlower = a * xlower * xlower + b * xlower + c; // calculates the functions value at lower point
        fxupper = a * xupper * xupper + b * xupper + c; // calculates the functions value at higher point

        if (fxlower * fxupper < 0) // checks if the condition for Bisection method is fullfiled(if functions values have different signs)
        {
            try // tries to do all the calculations
            {
                do {

                    xmid = (xlower + xupper) / 2; // calculates the middle point of interval
                    fxnew = a * xmid * xmid + b * xmid + c; //calculates the functions value at the midpoint

                    System.out.println(xmid + " " + fxnew);

                    //stores values into array
                    result[iteration][0] = xlower;
                    result[iteration][1] = xupper;
                    result[iteration][2] = xmid;
                    result[iteration][3] = fxlower;
                    result[iteration][4] = fxupper;
                    result[iteration][5] = fxnew;

                    // checks which point to replace so there would be a zero crossing within the new interval
                    if (fxlower * fxnew >= 0) {
                        xlower = xmid;
                        fxlower = fxnew;
                    } else if (fxupper * fxnew >= 0) {
                        xupper = xmid;
                        fxupper = fxnew;
                    }
                    iteration = iteration + 1;

                    if (iteration == it) //check whether the maximum amount of iteration has been reached if yes - stops
                    {
                        break;
                    }
                    xlower = round(xlower, decimalpl);
                    xupper = round(xupper, decimalpl);

                    if (oldxmid != 0) {
                        if (checkIfEnough(oldxmid, xmid, decimalpl)) {
                            break; //checks decimal places of oldxmid and xmid, compares them;
                        }
                    }
                    oldxmid = xmid; // assigns value to oldxmid so we can check if decimal place is close enough

                } while (xupper != xlower);

                root = round(xmid, decimalpl);

            } catch (ArithmeticException e) // if whilst doing calculations error occurs - a message pops up to inform the user
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong! ");
            }
        } else // if the condition for method is not met a message pops up to inform the user about that
        {
            JOptionPane.showMessageDialog(null, "whoops, seems like the method you have selected cannot be used with your inputs!\n Don't forget that f(a)*f(b) has to have a negative value.");
        }
    }

    private void solveLog(double a, double b, int decimalpl, double startP1, double startP2, int it, double[][] result) {
        double xlower, xupper, xmid, fxlower, fxupper, fxnew;
        double oldxmid = 0;
        int iteration = 0;

        xlower = Double.min(startP1, startP2);// assigns smaller value to lower point
        xupper = Double.max(startP1, startP2);// assigns greater value to upper point

        fxlower = (Math.log(xlower + a) + b); // calculates the functions value at lower point
        fxupper = (Math.log(xupper + a) +b); // calculates the functions value at higher point
        

        if (fxlower * fxupper < 0) // checks if the condition for Bisection method is fullfiled(if functions values have different signs)
        {
            try // tries to do all the calculations
            {
                do {
                    xmid = (xlower + xupper) / 2; // calculates the middle point of interval
                    fxnew = (Math.log(xmid + a) + b); //calculates the functions value at the midpoint  

                    //diff = (Math.abs(xupper - xlower)/2);
                    //stores values into array
                    result[iteration][0] = xlower;
                    result[iteration][1] = xupper;
                    result[iteration][2] = xmid;
                    result[iteration][3] = fxlower;
                    result[iteration][4] = fxupper;
                    result[iteration][5] = fxnew;
                    
                    if(Double.isInfinite(fxlower)||Double.isInfinite(fxupper))
                    {
                        
                      JOptionPane.showMessageDialog(null, "The value goes to inifinity, calculations can't be done");  
                        break;
                    }
                    
                    
                    // checks which point to replace so there would be a zero crossing within the new interval
                    if (fxlower * fxnew > 0) {
                        xlower = xmid;
                        fxlower = fxnew;
                    } else if (fxupper * fxnew > 0) {
                        xupper = xmid;
                        fxupper = fxnew;
                    }
                    iteration = iteration + 1;

                    if (iteration == it) //check whether the maximum amount of iteration has been reached if yes - stops
                    {
                        break;
                    }
                    xlower = round(xlower, decimalpl);
                    xupper = round(xupper, decimalpl);

                    if (oldxmid != 0) 
                    {
                        if (checkIfEnough(oldxmid, xmid, decimalpl)) 
                        {
                            break; //checks decimal places of oldxmid and xmid, compares them;
                        }
                    }
                    oldxmid = xmid; // assigns value to oldxmid so we can check if decimal place is close enough

                } while (xupper != xlower);

                root = round(xmid, decimalpl);

            } catch (ArithmeticException e) // if whilst doing calculations error occurs - a message pops up to inform the user
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
            }
        } else // if the condition for method is not met a message pops up to inform the user about that
        {
            JOptionPane.showMessageDialog(null, "whoops, seems like the method you have selected cannot be used with your inputs!\n Don't forget that f(a)*f(b) has to have a negative value.");
        }
    }

    private void solveExponential(double a, int decimalpl, double startP1, double startP2, int it, double[][] result) {
        double xlower, xupper, xmid, fxlower, fxupper, fxnew;
        double oldxmid = 0;
        int iteration = 0;

        xlower = Double.min(startP1, startP2);  // assigns smaller value to lower point
        xupper = Double.max(startP1, startP2);  // assigns greater value to lower point

        fxlower = Math.exp(xlower) + a * xlower; // calculates the functions value at lower point
        fxupper = Math.exp(xupper) + a * xupper; // calculates the functions value at higher point

        if (fxlower * fxupper < 0) // checks if the condition for Bisection method is fullfiled(if functions values have different signs)
        {
            try // tries to do all the calculations
            {
                do {
                    xmid = (xlower + xupper) / 2; // calculates the middle point of interval
                    fxnew = Math.exp(xmid) + a * xmid; //calculates the functions value at the midpoint  

                    //stores values into array   
                    result[iteration][0] = xlower;
                    result[iteration][1] = xupper;
                    result[iteration][2] = xmid;
                    result[iteration][3] = fxlower;
                    result[iteration][4] = fxupper;
                    result[iteration][5] = fxnew;

                    // checks which point to replace so there would be a zero crossing within the new interval
                    if (fxlower * fxnew >= 0) {
                        xlower = xmid;
                        fxlower = fxnew;
                    } else if (fxupper * fxnew >= 0) {
                        xupper = xmid;
                        fxupper = fxnew;
                    }
                    
                    iteration = iteration + 1;

                    if (iteration == it)//check whether the maximum amount of iteration has been reached if yes - stops
                    {
                        break;
                    }
                    xlower = round(xlower, decimalpl);
                    xupper = round(xupper, decimalpl);
                    if (oldxmid != 0) {
                        if (checkIfEnough(oldxmid, xmid, decimalpl)) {
                            break; //checks decimal places of oldxmid and xmid, compares them;
                        }
                    }
                    oldxmid = xmid; // assigns value to oldxmid so we can check if decimal place is close enough

                } while (xupper != xlower);

                root = round(xmid, decimalpl);

            } catch (ArithmeticException e) // if whilst doing calculations error occurs - a message pops up to inform the user
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
            }
        } else // if the condition for method is not met a message pops up to inform the user about that
        {
            JOptionPane.showMessageDialog(null, "whoops, seems like the method you have selected cannot be used with your inputs!\n Don't forget that f(a)*f(b) has to have a negative value.");
        }
    }

    private double round(double xvalue, int decimalpl) // rounds the values to the decimal place user has selected
    {
        xvalue = (Math.round(xvalue * Math.pow(10, decimalpl))) / Math.pow(10, decimalpl);
        return xvalue;
    }

    private boolean checkIfEnough(double oldxmid, double newxmid, int decimalpl) {
        long decimal1 = (long) (newxmid * Math.pow(10, decimalpl)); //finds decimal place of new middle point
        long decimal2 = (long) (oldxmid * Math.pow(10, decimalpl)); //finds decimal place of old middle point

        return decimal1 == decimal2; //if equal returns true, loop breaks, if false - loop goes on

    }

}
