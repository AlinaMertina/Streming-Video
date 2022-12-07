package lecteurvideo;

import java.lang.*;
import client.*;
public class RunAudio implements Runnable{
    
    static String nomVideo ;
    static FenetreAudio frameV ;
    
    Runenvoi envoiMessage; 

    RecevoirFile recevAudio ;
    public void setRecevoirFile(RecevoirFile r){
        recevAudio =r;
    }
    public RecevoirFile getRecevoirFile(){
        return recevAudio;
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
    public RunAudio(String n,Runenvoi en){
        setNomVideo(n);
        setEnvoiMessage(en);
    }
    public void setFrameV(FenetreAudio f){
        frameV=f;
    }
    public FenetreAudio getFrameV(){
        return frameV;
    }
    @Override
    public void run() {
        InitialisationNB.LoadLibraryNB("C:\\Program Files (x86)\\VideoLAN\\VLC");
        FenetreAudio f = new FenetreAudio(getNomVideo(),getEnvoiMessage(),this.getRecevoirFile());
        setFrameV(f);
        getFrameV().EcouterAudio();
    }
}