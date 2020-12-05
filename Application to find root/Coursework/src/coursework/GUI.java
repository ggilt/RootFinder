/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

/**
 *
 * @author ggiliute
 */
public class GUI extends JFrame implements ActionListener
{
    Font fnt = new Font("Monospaced", Font.ITALIC, 14);
    Border blackline;
    Scanner in = new Scanner (System.in);

    JFrame frame = new JFrame(" Root finding application ");
    JPanel panel = new JPanel(new FlowLayout());
    JTabbedPane tabbedPane = new JTabbedPane();
        
    tab1 t1 = new tab1();
    tab2 t2 = new tab2();
    tab3 t3 = new tab3();
    tab4 t4 = new tab4();

    public GUI()
    {
        view();
        
    }
    private void view() 
    {
 
        frame.setSize(1200,700);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
 
        tabbedPane.add("How to use", t1.getMainPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        tabbedPane.add("f(x)=ax²+bx+c", t2.getMainPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
        
        tabbedPane.add("f(x)=ln(x+a)+b", t3.getMainPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);

        tabbedPane.add("f(x)=e^x+ax", t4.getMainPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_4);

        frame.add(tabbedPane,BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }


    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
