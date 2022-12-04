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
           frame.getEnvoiMessage().setMessage("musique");
           frame.dispose();
        }  
        if(e.getSource()==frame.getVideo()){
           
        }  
        if(e.getSource()==frame.getImage()){
           
        }  
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}