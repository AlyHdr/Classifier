/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifier;

import java.util.ArrayList;
import javax.swing.UIManager;

/**
 *
 * @author Ali Haidar
 */
public class Classifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(counter);
               try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
           catch(Exception e)
                   {
                       System.out.println("Error");
                   }
        new Interface().setVisible(true);
    }
    
}
