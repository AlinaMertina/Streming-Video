package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.lang.*;
import java.io.*;
import java.util.regex.*;
import java.text.ParseException;

public class Runenvoi implements Runnable{
    ServerSocket serveurSocket ;
    Socket clientSocket ;
    PrintWriter out;
    Scanner sc;
    Runrecevoir run;
    BufferedReader in; 
    int p=0;
    int videop=0;
    int imagep=0;
    Vector<String> listeMusique = new Vector<String>(); 
    Vector<String> listeVideo = new Vector<String>();
    Vector<String> listeImage = new Vector<String>();
  

    String message = new String();
    public void setScanner(Scanner s){
        sc=s;
    }
    public Scanner getScanner(){
        return sc;
    }

    public void setServeurSocket(ServerSocket ser){
        serveurSocket=ser;
    }
    public ServerSocket getServeurSocket(){
        return serveurSocket;
    }
    public void setClientSocket(Socket cli){
        clientSocket=cli;
    }
    public Socket getClientSocket(){
        return clientSocket;
    }
    public void setOut(PrintWriter  o){
        out=o;
    }
    public PrintWriter getOut(){
        return out;
    }
    public void setMessage(String m){
        message=m;
    }
    public String getMessage(){
        return message;
    }
    public void setRunrecevoir(Runrecevoir r){
        run=r;
    }
    public Runrecevoir getRunrecevoir(){
        return run;
    }

    public void setIn(BufferedReader bf){
        in=bf;
    }
    public BufferedReader getIn(){
        return in;
    }
    public String  choixMusique(String choix){
        for(int i=0;i<listeMusique.size();i++){
            if(choix.compareTo(i+"a")==0){
                return listeMusique.get(i);
            }
        }
        return null;
    }
    public String choixVideo(String choix){
        for(int i=0;i<listeVideo.size();i++){
            if(choix.compareTo(i+"a")==0){
                return listeVideo.get(i);
            }
        }
        return null;
    }
    public String choixImage(String choix){
        for(int i=0;i<listeImage.size();i++){
            if(choix.compareTo(i+"a")==0){
                return listeImage.get(i);
            }
        }
        return null;
    }
    public Runenvoi(){}
    public Runenvoi(ServerSocket s,Socket so,PrintWriter pf,Scanner sa,BufferedReader bf){
        setServeurSocket(s);
        setClientSocket(so);
        setOut(pf);
        setScanner(sa);
        setIn(bf);
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/La_fin du_monde.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/La_fougere_et_le_bambou.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Le_pot_de_verre.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Le_prince_et_le_sage.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Lecho_de_nos_paroles.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Lelephant_du_cirque.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Les_etudiants_et_le_lion.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Regarde_mon_fils_regarde.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Richesse_Succes_et_Amour.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/Un_bebe_demande_a_Dieu.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/11 - Starry Eyes.mp3");
        listeMusique.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic/MASTER_KG-WAYAWAYA.mp3");
       
        listeVideo.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo/CES_CONSEILS_POURRAIENT_CHANGER_VOTRE_VIE_-_MARTIN_LUTHER_KING.mp4");
        listeVideo.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo/COMMENT_AVOIR_CONFIANCE_EN_SOI___TONY_ROBBINS.mp4");
        listeVideo.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo/JE_SUIS_DESOLE.mp4");
        listeVideo.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo/LE_DISCOURS_DE_CE_PRSIDENT_VA_VOUS_OUVRIR_LES_YEUX.mp4");
        listeVideo.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo/POUR_TROUVER_LES_SECRETS_DE_LUNIVERS,_PENSEZ_EN_TERMES_DENERGIE,_DE_FREQUENCE_ET_DE_VIBRATION.mp4");
        listeVideo.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo/SEULEMENT_5%_DES_GENS_CONNAISSENT_CE_SECRET_CACHE_POUR_DEVENIR_RICHE.mp4");
        
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image1.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image2.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image3.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image4.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image5.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image6.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image7.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image8.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image9.jpg");
        listeImage.add("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage/Image10.jpg");
    }
    @Override
        public void run() {
            
            while(getRunrecevoir().getMessage()!=null){//raha mbola mahazo message avi an'ny amin'ny client de mbola misi ilay boucle
               
                if(FindCaractInWord(getRunrecevoir().getMessage())==true){
                    //Mandefa .txt ilay serveur mba ahafahan'ny le client misafidy ny video ,musique,image ananan'le serveur
                    if(getRunrecevoir().getMessage().compareTo("musique")==0){//raha musique ni message azo de io ny action ataon'ny
                        Thread transfer= new Thread(new TransferWithT("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeMusic.txt",getClientSocket())) ;
                        transfer.start();
                        getOut().println("ListeMusic.txt");
                        p++;
                        videop=0;
                        imagep=0;
                    }
                    else if(getRunrecevoir().getMessage().compareTo("video")==0){//raha video ni message azo de io ny action ataon'ny
                        Thread transfer= new Thread(new TransferWithT("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeVideo.txt",getClientSocket())) ;
                        transfer.start();
                        getOut().println("ListeVideo.txt");
                        videop++;
                        imagep=0;
                        p=0;
                    }
                    else if(getRunrecevoir().getMessage().compareTo("image")==0){
                        Thread transfer= new Thread(new TransferWithT("D:/L2/S3/Reseux/StreamingVideo/serveur/ListeImage.txt",getClientSocket())) ;
                        transfer.start();
                        getOut().println("ListeImage.txt");
                        imagep++;
                        videop=0;
                        p=0;
                    }
                    else if(videop!=0){//rehefa avi mandefa ni .txt izi de mandefa ny video na musique na image 
                        Thread transfer= new Thread(new TransferWithT(choixVideo(getRunrecevoir().getMessage()),getClientSocket())) ;
                        transfer.start();
                    }
                    else if(p!=0){
                        Thread transfer= new Thread(new TransferWithT(choixMusique(getRunrecevoir().getMessage()),getClientSocket())) ;
                        transfer.start();
                    }
                    else if(imagep!=0)
                    {
                        Thread transfer= new Thread(new TransferWithT(choixImage(getRunrecevoir().getMessage()),getClientSocket())) ;
                        transfer.start();
                    }
                    else{
                        getOut().println("Serveur:"+getMessage());
                    }
                    //choixMusique(String choix)
                    
                    getOut().flush();
                    getRunrecevoir().setMessage("111111");
                }
            }
        }

        public  Boolean FindCaractInWord(String word){
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            Matcher  Finderdefaut = pattern.matcher(word);
            if(Finderdefaut.find()==true){ 
                return true;
            }
            else{
                return false;
            }
        }
}