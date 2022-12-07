package ecouteur;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import image.*;
import java.util.TimerTask;
import java.util.Timer;
import client.*;
import java.io.File;

public class Ecouteur implements MouseListener {
    Fenetre frame;
    public void setFrame(Fenetre fr){
        frame=fr;
    }
    public Fenetre getFrame(){
        return frame;
    }
    public Ecouteur(Fenetre fr ){
        setFrame(fr);
    }
    public void mouseClicked(MouseEvent e){
        if(e.getSource()==frame.getMusic()){
            frame.dispose();
           frame.getEnvoiMessage().setMessage("musique");
        }  
        if(e.getSource()==frame.getVideo()){
            frame.dispose();
            frame.getEnvoiMessage().setMessage("video");
            
            try {
                File fichier=new File("Video.mp4");
                if(fichier.exists()){
                    System.gc();
                    Thread.sleep(1000);
                    fichier.delete();
                }
            } catch (Exception el) {
              System.out.println(el);
            }
        }  
        if(e.getSource()==frame.getImage()){
            frame.dispose();
            frame.getEnvoiMessage().setMessage("image");
           
        }  
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}