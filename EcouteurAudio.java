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
import lecteurvideo.*;
import java.io.*;

public class EcouteurAudio implements MouseListener {
    FenetreAudio frame;
    int p=0;
    public void setFrame(FenetreAudio fr){
        frame=fr;
    }
    public FenetreAudio getFrame(){
        return frame;
    }
    public EcouteurAudio(FenetreAudio fr){
        setFrame(fr);
    }
    public void mouseClicked(MouseEvent e){
        int nbrVideo =getNbrVideo();
        if(e.getSource()==getFrame().getPausse()){
            if(p==0){
                getFrame().PausseAudio();
                p++;
            }else{
                getFrame().EcouterAudio();
                p=0;
            }
            
        }
        if(e.getSource()==getFrame().getNext()){
            for(int i=0;i<nbrVideo;i++){
                if(getFrame().getEnvoiMessage().getAction().compareTo(i+"a")==0){
                    int h=i+1;
                    
                    getFrame().StopAudio();
                    getFrame().getRecevoirFile().closeFileOutputStream();
                    getFrame().dispose();
                    File fich = new File("Video.mp4");
                    fich.delete();
                    if(h<nbrVideo){
                        getFrame().getEnvoiMessage().setMessage(h+"a");
                    }else{
                        getFrame().getEnvoiMessage().setMessage("0a");
                    }
                    
                }
            }
           
        }
        if(e.getSource()==getFrame().getPren()){
            for(int i=0;i<nbrVideo;i++){
                if(getFrame().getEnvoiMessage().getAction().compareTo(i+"a")==0){
                    int h=i-1;
                    
                    getFrame().StopAudio();
                    getFrame().getRecevoirFile().closeFileOutputStream();
                    getFrame().dispose();
                    File fich = new File("Video.mp4");
                    fich.delete();
                    if(h<nbrVideo && h>=0){
                        getFrame().getEnvoiMessage().setMessage(h+"a");
                    }else{
                        h=nbrVideo-1;
                        getFrame().getEnvoiMessage().setMessage(h+"a");
                    }
                    
                }
            }
        }
        if(e.getSource()==getFrame().getFermer()){
                    getFrame().StopAudio();
                    getFrame().getRecevoirFile().closeFileOutputStream();
                    getFrame().dispose();
                    File fich = new File("Video.mp4");
                    fich.delete();
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public int getNbrVideo(){
        int p=0;
      
        try 
        {
            File file = new File("ListeMusic.txt"); 
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                p++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
        return p;
    }

}