package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.regex.*;
import java.text.ParseException;
import image.*;
import lecteurvideo.*;

public class Runrecevoir implements Runnable{
  
    Socket clientSocket ;
    BufferedReader in;
    PrintWriter out;
    Player jlPlayer;
    String message = new String();
    Fichier file = new Fichier();
    int recue=0;
    Runenvoi run;
    int h=0;
    int ho=0;
    int musiquep=0;
    int videop=0;
    int photop=0;
   
    String Action; //indice ny video mandeha amizao
    public void setAction(String h){
        Action=h;
    }
    public String getAction(){
        return Action;
    }
    public void setRunenvoi(Runenvoi r){
        run=r;
    }
    public Runenvoi getRunenvoi(){
        return run;
    }
    public void setRecue(int r){
        recue=r;
    }
    public int getRecue(){
        return recue;
    }

    public void setClientSocket(Socket cli){
        clientSocket=cli;
    }
    public Socket getClientSocket(){
        return clientSocket;
    }
    public void setIn(BufferedReader bf){
        in=bf;
    }
    public BufferedReader getIn(){
        return in;
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
    public Runrecevoir(Socket so,BufferedReader bf,PrintWriter pf){
        setClientSocket(so);
        setIn(bf);
        setOut(pf);
    }
    
    public void run() { 
        try {
            setMessage(getIn().readLine());
            System.out.println("jjjjjj");
            //tant que le client est connecté
            while(getMessage()!=null){
                if(getMessage().compareTo("ListeMusic.txt")==0){
                    System.out.println("ListeMusic.txtaaaa");
                    GetListeMusique recevoirFile = new GetListeMusique(getClientSocket(),"ListeMusic.txt");
                    try {
                        recevoirFile.receiveFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                    ShowListe show = new ShowListe("ListeMusic.txt",new Fenetre(getRunenvoi(),this));
                    musiquep++;
                    videop=0;
                    photop=0;
                }
                if(musiquep!=0){
                    System.out.println("RecevoirListeMusic.txtaaaa");
                    RecevoirFile recevoirFile = new RecevoirFile(getClientSocket(),"Video.mp4",getRunenvoi());
                    try {
                        recevoirFile.receiveFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                }
               //video
                if(getMessage().compareTo("ListeVideo.txt")==0){
                    System.out.println("ListeVideo.txtaaaa");
                    GetListeMusique recevoirFile = new GetListeMusique(getClientSocket(),"ListeVideo.txt");
                    try {
                        recevoirFile.receiveFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                    ShowListe show = new ShowListe("ListeVideo.txt",new Fenetre(getRunenvoi(),this));
                    musiquep=0;
                    videop++;
                    photop=0;
                }
                if(videop!=0){
                    System.out.println("RecevoirListeVideo.txtaaaa");
                 
                    RecevoirVideo RVideo = new RecevoirVideo(getClientSocket(),"Video.mp4",getRunenvoi());
                    try {
                        RVideo.resaveAndPlay();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                }
                
                //image
                if(getMessage().compareTo("ListeImage.txt")==0){
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    
                    System.out.println("ListeImage.txtaaaa");
                    GetListeMusique recevoirFile = new GetListeMusique(getClientSocket(),"ListeImage.txt");
                    try {
                        recevoirFile.receiveFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                    ShowListe show = new ShowListe("ListeImage.txt",new Fenetre(getRunenvoi(),this));
                    musiquep=0;
                    videop=0;
                    photop++;
                }
                if(photop!=0){
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    
                    System.out.println("RecevoirListeImage.txttaaaa");
                    RecevoirImage image = new RecevoirImage(getClientSocket(),getRunenvoi());
                    try {
                        image.RecevoirSImage();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                   
                }
               
                setMessage(getIn().readLine());
               
            }
            //sortir de la boucle si le client a déconecté
            System.out.println("Serveur deconecte");
            //fermer le flux et la session socket
            getOut().close();
            getClientSocket().close();
            } catch (IOException e) {
                    e.printStackTrace();
        }
    }
    public static Boolean FindCaractInWord(String word){
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