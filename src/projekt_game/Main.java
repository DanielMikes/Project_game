/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_game;

import javax.swing.JFrame;

/**
 *
 * @author Daniel
 */
public class Main {
    
    public static Display f = new Display();
    public static int w = 800;
    public static int h = 600;
    
    public static void main (String args[]){
     
        f.setSize(w, h);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Game");
        f.setLocationRelativeTo(null);
        f.setAlwaysOnTop(true);
        
    }
}
