package image;
import javax.swing.JFrame;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import ecouteur.*;
import client.*;
public class Fenetre extends JFrame{

    

    JLabel titreH1 = new JLabel("STREAMING ");
    JLabel music = new JLabel("MUSIC");
    JLabel video = new JLabel("VIDEO");
    JLabel image = new JLabel("IMAGE");
    JButton button1 = new JButton("Go");
    JButton button2 = new JButton("Go");
    JButton button3 = new JButton("Go");

    JPanel contentG = new JPanel();
    JPanel premiere = new JPanel();
    JPanel deusieme = new JPanel();
    JPanel troisieme = new JPanel();
    JPanel center = new JPanel();
    Runenvoi envoiMessage; 
    Runrecevoir recevoirMessage;
    public Runrecevoir getRecevoirMessage(){
        return recevoirMessage;
    }
    public void setRecevoirMessage(Runrecevoir rec){
        recevoirMessage=rec;
    }
    public Runenvoi getEnvoiMessage(){
        return envoiMessage;
    }
    public void setEnvoiMessage(Runenvoi run){
        envoiMessage=run;
    }
    public JButton getMusic(){
        return button1;
    }
    public JButton getVideo(){
        return button2;
    }
    public JButton getImage(){
        return button3;
    }
    public Fenetre(Runenvoi run,Runrecevoir rec){

        setRecevoirMessage(rec);
        setEnvoiMessage(run);

        button1.addMouseListener(new Ecouteur(this));
        button2.addMouseListener(new Ecouteur(this));
        button3.addMouseListener(new Ecouteur(this));

        contentG.setLayout(new BorderLayout());
        contentG.add(titreH1,BorderLayout.NORTH);
        premiere.setLayout(new FlowLayout(FlowLayout.CENTER));
        deusieme.setLayout(new FlowLayout(FlowLayout.CENTER));
        troisieme.setLayout(new FlowLayout(FlowLayout.CENTER));
        premiere.add(music);
        premiere.add(button1);
        deusieme.add(video);
        deusieme.add(button2);
        troisieme.add(image);
        troisieme.add(button3);

        center.setLayout(new GridLayout(3,1));
        center.add(premiere);
        center.add(deusieme);
        center.add(troisieme);
        contentG.add(center,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,300);
        setTitle("Basket");
        setVisible(true);
        setLocation(300, 30);
        setContentPane(contentG);
    }

}