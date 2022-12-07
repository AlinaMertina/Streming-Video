package image;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import lecteurvideo.*;
import client.*;
import ecouteur.*;

public class FenetreImage extends JFrame {
    Image img ;
    

    Runenvoi envoiMessage; 
    public Runenvoi getEnvoiMessage(){
        return envoiMessage;
    }
    public void setEnvoiMessage(Runenvoi run){
        envoiMessage=run;
    }

    public void setImage( Image i){
        img=i;
    }
    public Image getImage(){
        return img;
    }
    

    public FenetreImage(Image i,Runenvoi r){
        setImage(i);
        setEnvoiMessage(r);

        JpanelImage jp = new JpanelImage(getImage());
        JPanel contente = new JPanel();
        
        contente.setLayout( new BorderLayout());
        setSize(500,500);
        setTitle("Basket");
        setVisible(true);
        setLocation(300, 30);
        contente.add(jp,BorderLayout.CENTER);
        setContentPane(contente);
        
    }
   
}