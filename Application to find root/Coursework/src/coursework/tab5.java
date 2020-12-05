/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author ggiliute
 */
public class tab5 
{
    Font fnt = new Font("Monospaced", Font.ITALIC, 14);
    Border blackline;
    
    JPanel mainPanel = new JPanel();
    JPanel instructions = new JPanel();
    
    public tab5() 
    {
        this.mainPanel=createAPanel();
    }
    
     private JPanel createAPanel()
{
        mainPanel.setPreferredSize(new Dimension(1100, 630));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel aboutlb = new JLabel ("Write something about the thing");
        instructions.add(aboutlb);
        mainPanel.add(instructions);
               
                     
               return mainPanel;
}

    public JPanel getMainPanel() {
        return mainPanel;
    }
    
}
