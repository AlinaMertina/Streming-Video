package lecteurvideo;
/*
    creation fenetre video
    java.awt.Canvas Un Canvascomposant représente une zone rectangulaire vierge de l'écran sur laquelle l'application peut dessiner ou à partir de laquelle l'application peut intercepter les événements d'entrée de l'utilisateur.
    RectangleAV rectangle misi ny mouvement an'le action ( dessin video)
    */
    import java.awt.*;
    import java.awt.Canvas;
    import java.io.File;
    import javax.swing.*;
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    import javax.swing.SwingUtilities;
    import com.sun.jna.Native;
    import com.sun.jna.NativeLibrary;
    import  client.*;
    import ecouteur.*;

import client.Runrecevoir;
import uk.co.caprica.vlcj.binding.LibVlc;
    import uk.co.caprica.vlcj.player.MediaPlayerFactory;
    import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
    import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
    import uk.co.caprica.vlcj.runtime.RuntimeUtil;
    import uk.co.caprica.vlcj.runtime.x.LibXUtil;

public class FenetreVideo extends JFrame{
    Canvas RectangleAV = new Canvas();
    JPanel contentRect = new JPanel();
    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    static EmbeddedMediaPlayer mediaPlayer ;
    String nomFilm;
    JPanel contentAction = new JPanel();
    JButton pausse = new JButton("=");
    JButton next = new JButton("=>");
    JButton pren = new JButton("<=");
    JButton fermer = new JButton("X");

    Runenvoi envoiMessage; 
    //RecevoirVideo afana manw next
    RecevoirVideo recevVideo ;
    public void setRecevoirVideo(RecevoirVideo r){
        recevVideo=r;
    }
    public RecevoirVideo getRecevoirVideo(){
        return recevVideo;
    }
   
    public Runenvoi getEnvoiMessage(){
        return envoiMessage;
    }
    public void setEnvoiMessage(Runenvoi run){
        envoiMessage=run;
    }

    public void setnomFilm(String f){
        nomFilm=f;
    }
    public String getnomFilm(){
        return nomFilm;
    }
    public EmbeddedMediaPlayer getEmbeddedMediaPlayer(){
        return mediaPlayer ;
    }
    public JButton getPausse(){
        return pausse;
    }
    public JButton getNext(){
        return next;
    }
    public JButton getPren(){
        return pren;
    }
    public JButton getFermer(){
        return fermer;
    }

    public FenetreVideo(String f,Runenvoi enmes,RecevoirVideo run){
        setEnvoiMessage(enmes);
        setnomFilm(f);
        setRecevoirVideo(run);
        fermer.setBackground(Color.RED);

        contentAction.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        contentAction.add(pausse);
        /*
        contentAction.add(pren);
        contentAction.add(next);
        pren.addMouseListener(new EcouteurVideo(this));
        next.addMouseListener(new EcouteurVideo(this));*/
        pausse.addMouseListener(new EcouteurVideo(this));
       
        fermer.addMouseListener(new EcouteurVideo(this));
        
        setLocation(400, 100);
        setSize(600, 600);
        setVisible(true);
        //ajout proprieter canvas 
        RectangleAV.setSize(600,600);
        RectangleAV.setBackground(Color.black);
        //JPanel
        contentRect.setLayout(new BorderLayout());
        contentRect.add(RectangleAV, BorderLayout.CENTER);
        contentRect.add(fermer,BorderLayout.NORTH);
        this.add(contentRect, BorderLayout.CENTER);
        this.add(contentAction,BorderLayout.SOUTH); 
       
        //
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(RectangleAV));
        mediaPlayer.setEnableMouseInputHandling(true);
        mediaPlayer.setEnableKeyInputHandling(true);
        mediaPlayer.prepareMedia(getnomFilm());
        System.out.println("time video "+mediaPlayer.getVideoDimension());
       
    }
    public void AfficherVideo(){
            mediaPlayer.play();
    }
    public void StopVideo(){
        mediaPlayer.stop();
    }
    public void PausseVideo(){
        mediaPlayer.pause();
    }
   
    
}