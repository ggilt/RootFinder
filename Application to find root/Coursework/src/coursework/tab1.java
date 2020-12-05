/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ggiliute
 */
public class tab1 extends Coursework
{
    Font fnt = new Font("Monospaced", Font.PLAIN, 24);
    Font fnt2 = new Font("Monospaced ", Font.BOLD, 34);

    Border blackline;
    
    JPanel mainPanel = new JPanel();
    JPanel instructions = new JPanel();

    

    public tab1() 
    {
        this.mainPanel=createAPanel();
    }
    
    
    
    private JPanel createAPanel()
{
        mainPanel.setPreferredSize(new Dimension(1100, 630));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JLabel blank = new JLabel (" ");
        JLabel blank1 = new JLabel (" ");
        JLabel blank2 = new JLabel (" ");
        JLabel blank3 = new JLabel (" ");

        
        JLabel instructionLabel = new JLabel ("Instructions on how to use root finding program:");
        instructionLabel.setFont(fnt2);
        
        JLabel step1 = new JLabel (" 1. Select which function you want to use;");
        step1.setFont(fnt);

        JLabel step2 = new JLabel (" 2. Input the values into blank fields;");
        step2.setFont(fnt);

        
        JLabel step3 = new JLabel (" 3. Input starting point(s);");
        step3.setFont(fnt);

                
        JLabel step4 = new JLabel (" 4. Select your preferences such as:");
        step4.setFont(fnt);
                
             JLabel pref1 = new JLabel ("      • method you want to use;");
             pref1.setFont(fnt);

             JLabel pref2 = new JLabel ("      • decimal place of the root value;");
             pref2.setFont(fnt);
 
             
             JLabel pref3 = new JLabel ("      • maximum iteration in case of infinite loop;");
             pref3.setFont(fnt);

         JLabel step5 = new JLabel (" 5. Press the magic 'solve' button.");
         step5.setFont(fnt);

         JLabel voila = new JLabel ("Voila!");
         voila.setFont(fnt2);

         
         mainPanel.add(instructionLabel);
         mainPanel.add(blank);
         mainPanel.add(blank1);

         mainPanel.add(step1);
         mainPanel.add(step2);
         mainPanel.add(step3);
         mainPanel.add(step4);
        
         
         mainPanel.add(pref1);
         mainPanel.add(pref2);
         mainPanel.add(pref3);
         mainPanel.add(step5);
         mainPanel.add(blank2);
         mainPanel.add(blank3);


         mainPanel.add(voila);
         
               
                     
               return mainPanel;
}

    public JPanel getMainPanel() {
        return mainPanel;
    }

    
}
