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
public class SecantMethod {

    double a, b, c, startP1, startP2;
    double root;
    int decimalpl, it;
    double[][] result;

    public SecantMethod(double a, double b, double c, double st1, double st2, int dp, int it) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.decimalpl = dp;
        this.startP1 = st1;
        this.startP2 = st2;
        this.it = it;
        this.result = new double[it][5];
        solveQuadratic(a, b, c, decimalpl, startP1, startP2, it, result);

    }

    public SecantMethod(double a, double b, double st1, double st2, int dp, int it) {
        this.a = a;
        this.b = b;
        this.decimalpl = dp;
        this.it = it;
        this.startP1 = st1;
        this.startP2 = st2;
        this.result = new double[it][5];

        solveLog(a, b, decimalpl, startP1, startP2, it, result);
    }

    public SecantMethod(double a, double st1, double st2, int dp, int it) {
        this.a = a;
        this.decimalpl = dp;
        this.startP1 = st1;
        this.startP2 = st2;
        this.it = it;
        this.result = new double[it][5];
        solveExponential(a, decimalpl, st1, st2, it, result);
    }

    public double[][] getResult() //getter, returns the list of calculation breakdown
    {
        return result;
    }

    public double getRoot() {
        return root;
    }
    

    private void solveQuadratic(double a, double b, double c, int decimalpl, double startP1, double startP2, int it, double[][] result) {
        double xold1, xold2, fxold1, fxold2, diff;
        double xnew = 0;
        int iteration = 0;

        xold1 = Double.min(startP1, startP2);  // assigns smaller value to lower point
        xold2 = Double.max(startP1, startP2);  // assigns greater value to higher point

        fxold1 = a * xold1 * xold1 + b * xold1 + c; // calculates the functions value at lower point
        fxold2 = a * xold2 * xold2 + b * xold2 + c;  // calculates the functions value at higher point

        if (fxold1 * fxold2 < 0) // checks if the condition for Secant method is fullfiled(if values of intial guesses put into function are opposite)
        {
            try // tries to do all the calculations
            {
                do {

                    if ((fxold1 - fxold2) == 0) // checks if divident is zero, if yes - error message pops up
                    {
                        JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
                        break;
                    }
                    fxold1 = a * xold1 * xold1 + b * xold1 + c;//calculates the functions value at point xold1  
                    fxold2 = a * xold2 * xold2 + b * xold2 + c;//calculates the functions value at point xold2  

                    xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);  //the values obtained are put into Secant's method

                    diff = Math.abs(xnew - xold1) * 1000;// gets the difference of old value and new, multiplies it by 1000, so a few more iterations are done to make sure the root is correct

                    //stores values into array
                    result[iteration][0] = xold1;
                    result[iteration][1] = xold2;
                    result[iteration][2] = fxold1;
                    result[iteration][3] = fxold2;
                    result[iteration][4] = xnew;

                    
                    //new values are assigned to variables
                    xold2 = xold1;
                    xold1 = xnew;
                    iteration = iteration + 1;//increases iteration by one

                    if (iteration == it) {
                        break;
                    }

                } while (diff > decimalpl);
                {
                    root = round(xnew, decimalpl);
                }
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
        double xold1, xold2, fxold1, fxold2, diff;
        double xnew = 0;
        int iteration = 0;

        xold1 = Double.min(startP1, startP2);  // assigns smaller value to lower point
        xold2 = Double.max(startP1, startP2);  // assigns greater value to higher point

        fxold1 = Math.log(xold1 + a) + b;//calculates the functions value at point xold1  
        fxold2 = Math.log(xold2 + a) + b;//calculates the functions value at point xold2

        if (fxold1 * fxold2 < 0) // checks if the condition for Secant method is fullfiled(if values of intial guesses put into function are opposite)
        {
            try // tries to do all the calculations
            {
                do {
                    if ((fxold1 - fxold2) == 0) // checks if divident is zero, if yes - error message pops up
                    {
                        JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
                        break;
                    }
                    fxold1 = Math.log(xold1 + a) + b;
                    fxold2 = Math.log(xold2 + a) + b;

                    xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);  //the values obtained are put into Secant's method
                    diff = Math.abs(xnew - xold1) * 1000;// gets the difference of old value and new, multiplies it by 1000, so a few more iterations are done to make sure the root is correct
                 
                    //stores values into array
                    result[iteration][0] = xold1;
                    result[iteration][1] = xold2;
                    result[iteration][2] = fxold1;
                    result[iteration][3] = fxold2;
                    result[iteration][4] = xnew;
                    
                     if (Double.isInfinite(fxold1)||Double.isInfinite(fxold2)) // checks if the point goes to infinity, if yes - breaks;
                {
                    JOptionPane.showMessageDialog(null, "The point goes to inifinity, calculations can't be done");
                    break;
                }


                    //new values are assigned to variables
                    xold2 = xold1;
                    xold1 = xnew;
                    iteration = iteration + 1;//increases iteration by one

                    if (iteration == it) {
                        break;
                    }

                } while (diff > decimalpl);
                {
                    root = round(xnew, decimalpl);
                }
            } catch (ArithmeticException e) // if whilst doing calculations error occurs - a message pops up to inform the user
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong! ");
            }
        } else // if the condition for method is not met a message pops up to inform the user about that
        {
            JOptionPane.showMessageDialog(null, "whoops, seems like the method you have selected cannot be used with your inputs!\n Don't forget that f(a)*f(b) has to have a negative value.");
        }
    }

    private void solveExponential(double a, int decimalpl, double startP1, double startP2, int it, double[][] result) {
        double xold1, xold2, fxold1, fxold2, diff;
        double xnew = 0;
        int iteration = 0;

        xold1 = Double.min(startP1, startP2);  // assigns smaller value to lower point
        xold2 = Double.max(startP1, startP2);  // assigns greater value to higher point

        fxold1 = Math.exp(xold1) + a * xold1;
        fxold2 = Math.exp(xold2) + a * xold2;

        if (fxold1 * fxold2 < 0) // checks if the condition for Secant method is fullfiled(if values of intial guesses put into function are opposite)
        {
            try // tries to do all the calculations
            {
                do {
                    if ((fxold1 - fxold2) == 0) // checks if divident is zero, if yes - error message pops up
                    {
                        JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong!");
                        break;
                    }
                    fxold1 = (Math.exp(xold1) + a * xold1);
                    fxold2 = (Math.exp(xold2) + a * xold2);

                    xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);  //the values obtained are put into Secant's method
                    diff = Math.abs(xnew - xold1)  ;// gets the difference of old value and new, multiplies it by 1000, so a few more iterations are done to make sure the root is correct
   
                    //stores values into array
                    result[iteration][0] = xold1;
                    result[iteration][1] = xold2;
                    result[iteration][2] = fxold1;
                    result[iteration][3] = fxold2;
                    result[iteration][4] = xnew;

                    //new values are assigned to variables
                    xold2 = xold1;
                    xold1 = xnew;
                    iteration = iteration + 1;//increases iteration by one

                    if (iteration == it) 
                    {
                        break;
                    }

                } while (diff > decimalpl);
                {
                    root = round(xnew, decimalpl);
                }
            } catch (ArithmeticException e) // if whilst doing calculations error occurs - a message pops up to inform the user
            {
                JOptionPane.showMessageDialog(null, "whoops, seems like something went wrong! ");
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
}
