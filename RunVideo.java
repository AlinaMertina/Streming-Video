package lecteurvideo;

import java.lang.*;
import client.*;
public class RunVideo implements Runnable{
    
    static String nomVideo ;
    static FenetreVideo frameV ;
    
    Runenvoi envoiMessage; 
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

    public void setNomVideo(String m){
        nomVideo=m;
    }
    public String getNomVideo(){
        return nomVideo;
    }
    public RunVideo(String n,Runenvoi en){
        setNomVideo(n);
        setEnvoiMessage(en);
    }
    public void setFrameV(FenetreVideo f){
        frameV=f;
    }
    public FenetreVideo getFrameV(){
        return frameV;
    }
    @Override
    public void run() {
        InitialisationNB.LoadLibraryNB("C:\\Program Files (x86)\\VideoLAN\\VLC");
        FenetreVideo f = new FenetreVideo(getNomVideo(),getEnvoiMessage(),getRecevoirVideo());
        setFrameV(f);
        getFrameV().AfficherVideo();
    }
}