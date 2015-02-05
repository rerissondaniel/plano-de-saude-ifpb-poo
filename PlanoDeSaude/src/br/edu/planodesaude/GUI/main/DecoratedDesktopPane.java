package br.edu.planodesaude.GUI.main;

import java.awt.Graphics;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
  
public class DecoratedDesktopPane extends JDesktopPane {  
  
    private static final long serialVersionUID = 1L;  
  
    private ImageIcon image;  
    private MediaTracker tracker;  
  
    public DecoratedDesktopPane(String caminhoImagem) {  
  
        image = new ImageIcon(this.getClass().getResource(caminhoImagem));  
  
        tracker = new MediaTracker(this);  
        tracker.addImage(image.getImage(), 0);  
  
        try {  
            tracker.waitForID(0);  
        } catch (InterruptedException exception) {  
            exception.printStackTrace();  
        } 
  
    }   
  
    public void paintComponent(Graphics graphics) {  
  
        super.paintComponent(graphics);  
    
        graphics.drawImage(image.getImage(),  
                this.getWidth() / 2 - image.getImage().getWidth(this) / 2,  
                this.getHeight() / 2 - image.getImage().getHeight(this) / 2,  
                this.getBackground(), this);  
    }  
}  