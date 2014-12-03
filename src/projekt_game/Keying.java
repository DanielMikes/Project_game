/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Robot;

/**
 *
 * @author Daniel
 */
public class Keying extends JPanel{
    
    public Rectangle character;
    public Rectangle bottomBox;
    public int charW = 24;
    public int charH = 24;
    public long jumpingTime = 200;
    public boolean right = false;
    public boolean left = false;
    public boolean up = false;
    public boolean down = false;
    public boolean mouseActive = false;
    public boolean jumping = false;
    public Point mouse;
    public int x = -500, y = -500;
    public double angle = 0.001;
    
    public Keying(Display f, Images i){
        character = new Rectangle(400,300, charW, charH);
        bottomBox = new Rectangle(0, 540, 9000, 30);
        Robot robot;
        try {
            robot = new Robot();
            robot.mouseMove(500, 500);
        } catch (AWTException ex) {
        }
        
        //f.setCursor(f.getToolkit().createCustomCursor(
        //    new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        

        f.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_D){
                    right = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    left = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_W){
                    up = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    down = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_M){
                    mouseActive = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    jumping = true;
                    new Thread(new thread()).start();
                }
            }

            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_D){
                    right = false;                  
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    left = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_W){
                    up = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    down = false;
                }
            }
        });

        f.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseMoved(MouseEvent e){
                mouse = new Point(e.getX(), e.getY());
                if(mouseActive){
                    character.x = mouse.x - charW/2;
                    character.y = mouse.y - charH*3/2;
                    //x = (int) Math.ceil((double)mouse.x/400.00 * Main.f.i.bg.getWidth(null))-Main.f.i.bg.getWidth(null)*2;
                    //System.out.println(x);
                    //y = (int) Math.ceil((double)mouse.y/300.00 * Main.f.i.bg.getHeight(null))-Main.f.i.bg.getHeight(null)*2;
                    //y = (int) Math.ceil((double)mouse.y/600.00 * Main.f.i.bg.getHeight(null));
                }
                repaint();
            }
        });
        
        

        f.addMouseListener(new MouseAdapter(){

            public void mouseClicked(MouseEvent e){
                mouse = new Point(e.getX(), e.getY() - 25);

                if(e.getButton() == MouseEvent.BUTTON1 && !mouseActive){
                    character.x = mouse.x;
                    character.y = mouse.y;
                }
            }
        });
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(Main.f.i.bg, x, y, 1920, 1080, null);
       
        Graphics2D g2d=(Graphics2D)g; // Create a Java2D version of g.
         g2d.translate(0, 0); // Translate the center of our coordinates.
         //g2d.rotate(0,this.character.x + charW/2 , this.character.y + charH/2); // Rotate the image by 1 radian.
         //g2d.drawImage(Main.f.i.smiley, this.character.x - charW/2, this.character.y - charH/2, 50, 50, null);
         //angle = (double)(character.y - charH/2 - mouse.y)/(double)(character.x + charW/2 - mouse.x)/3.14;
        try{
            angle = Math.atan2(character.y - charH/2 - mouse.y, character.x - charW/2 - mouse.x) - Math.PI / 2;
        }catch(Exception ex){
        }
        g2d.rotate(angle, character.x - charW/2, character.y - charH/2); // Rotate the image by 1 radian.
        g2d.drawImage(Main.f.i.smiley, this.character.x - 3*charW/2, this.character.y - 3*charH/2, 50, 50, null);
        
        //((Graphics2D)g).rotate(angle, character.x - charW/2, character.y - charH/2);
         
        Point pt1 = new Point(character.x, character.y+5 + character.height);
        if(!bottomBox.contains(pt1) && !mouseActive && !jumping){
            //character.y = character.y+5;
        }
        
        this.setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        //g.fillRect(character.x, character.y, character.width, character.height);
        //g.fillRect(bottomBox.x, bottomBox.y, bottomBox.width, bottomBox.height);
        
        if(right){
            if(character.x < Main.w - 200)
                character.x = character.x + 5;
            if(character.x==Main.w - 200 && x > -(Main.f.i.bg.getWidth(null))+Main.w){
                x = x - 5;
            }
            System.out.println(x);
        }
        if(left){
            if(character.x > 150)
                character.x = character.x - 5;
            if(character.x==150 && x < 0){
                x = x+5;
            }
            System.out.println(x);
        }
        if(up){
            if(character.y > 150)
                character.y = character.y - 5;
            if(character.y==150 && y < 0){
                y = y+5;
            }
        }
        if(down){
            if(character.y < Main.h - 200)
                character.y = character.y + 5;
            if(character.y==Main.h - 200 && y > -(Main.f.i.bg.getHeight(null))+Main.h-charW){
                y = y - 5;
            }
            System.out.println();
        }
        if(jumping){
            character.y = character.y - 5;
        }
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        repaint();
    }
    
    public class thread implements Runnable{

        @Override
        public void run() {
            try{
                Thread.sleep(jumpingTime);
                jumping = false;
            }catch(Exception e){ 
                e.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }
        }    
    }
}
