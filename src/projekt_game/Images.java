/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_game;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class Images extends JPanel{
    
    public int w = Main.w;
    public int h = Main.h;
    public boolean imagesLoaded = false;
    
    public Image bg;
    public Image smiley;
    
    public Images(){
        this.setBackground(Color.BLACK);
    }
    
    public void loadImages(){
        bg = new ImageIcon("porsche.jpg").getImage();
        smiley = new ImageIcon("smiley.png").getImage();
        imagesLoaded = true;
        System.out.println(bg.getWidth(this));
    }
    
}
