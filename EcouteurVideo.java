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

public class EcouteurVideo implements MouseListener {
    FenetreVideo frame;
    int p=0;
    public void setFrame(FenetreVideo fr){
        frame=fr;
    }
    public FenetreVideo getFrame(){
        return frame;
    }
    public EcouteurVideo(FenetreVideo fr){
        setFrame(fr);
    }
    public void mouseClicked(MouseEvent e){
        int nbrVideo =getNbrVideo();
        if(e.getSource()==getFrame().getPausse()){
            if(p==0){
                getFrame().PausseVideo();
                p++;
            }else{
                getFrame().AfficherVideo();
                p=0;
            }
        }
        if(e.getSource()==getFrame().getNext()){
            System.out.println("nombre "+ nbrVideo);
            for(int i=0;i<nbrVideo;i++){
                if(getFrame().getEnvoiMessage().getAction().compareTo(i+"a")==0){
                    int h=i+1;
                    getFrame().StopVideo();
                    getFrame().getRecevoirVideo().closeFileOutputStream();
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
                    
                    getFrame().StopVideo();
                    getFrame().getRecevoirVideo().closeFileOutputStream();
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
            getFrame().StopVideo();
            getFrame().getRecevoirVideo().closeFileOutputStream();
            getFrame().dispose();
            File fich = new File("Video.mp4");
            fich.delete();
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

    public int getNbrVideo(){;
        int p=0;
      
        try 
        {
            File file = new File("ListeVideo.txt"); 
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