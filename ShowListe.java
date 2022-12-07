package image;
import javax.swing.JFrame;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import ecouteur.*;
import client.*;
import java.io.*;

public class ShowListe extends JFrame{

    String nameFile ;
    Vector<String> ListeChoix = new Vector<String>();
    Vector<JButton> ListeNumChoix = new Vector<JButton>();
    JPanel contentG = new JPanel();
    JPanel Liste= new JPanel();
    Fenetre frame;
    String VideoAction = new String();
    public void setVideoAction(String a){
        VideoAction=a;
    }
    public String getVideoAction(){
        return VideoAction;
    }
    
    public void setFrame(Fenetre fr){
        frame=fr;
    }
    public Fenetre getFrame(){
        return frame;
    }

    public void setNameFile(String f){
        nameFile=f;
    }
    public String getNameFile(){
        return nameFile;
    }
    public Vector<JButton> getButton(){
        return ListeNumChoix;
    }
    public ShowListe(String nomFile,Fenetre fr){
        setNameFile(nomFile);
        setFrame(fr);
        setListe();
        Liste.setLayout(new GridLayout(ListeChoix.size(),2));
        for(int i=0;i<ListeChoix.size();i++){
            JLabel label = new JLabel(ListeChoix.get(i));
            JButton but = new JButton(i+"a");
            but.addMouseListener(new EcouterListeChoix(this));
            Liste.add(label);
            Liste.add(but);
            ListeNumChoix.add(but);

        }
        contentG.add(Liste);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
        setTitle("ListeChoix");
        setVisible(true);
        setLocation(300, 30);
        setContentPane(contentG);
    }
    public void setListe(){
        try
        {
            File file = new File(getNameFile());    
            FileReader fr = new FileReader(file);  
            BufferedReader br = new BufferedReader(fr);   
            String line;
                while((line = br.readLine()) != null)
                {
                    ListeChoix.add(line);
                }
            fr.close();    
        }
        catch(IOException e){
            e.printStackTrace();
            }
    }

}