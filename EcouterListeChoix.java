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

public class EcouterListeChoix implements MouseListener {
    ShowListe frame;
    public void setFrame(ShowListe fr){
        frame=fr;
    }
    public ShowListe getFrame(){
        return frame;
    }
    public EcouterListeChoix(ShowListe fr ){
        setFrame(fr);
    }
    public void mouseClicked(MouseEvent e){
        for(int i=0;i<frame.getButton().size();i++){
            if(e.getSource()==frame.getButton().get(i)){
                frame.getFrame().getEnvoiMessage().setMessage(frame.getButton().get(i).getText());
                frame.dispose();
            }  
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}