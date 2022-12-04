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
    Vector<String> listeMusique = new Vector<String>();
   

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
    public Runenvoi(){}
    public Runenvoi(ServerSocket s,Socket so,PrintWriter pf,Scanner sa,BufferedReader bf){
        setServeurSocket(s);
        setClientSocket(so);
        setOut(pf);
        setScanner(sa);
        setIn(bf);
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/La_fin du_monde.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/La_fougere_et_le_bambou.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Le_pot_de_verre.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Le_prince_et_le_sage.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Lecho_de_nos_paroles.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Lelephant_du_cirque.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Les_etudiants_et_le_lion.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Regarde_mon_fils_regarde.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Richesse_Succes_et_Amour.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/Un_bebe_demande_a_Dieu.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/11 - Starry Eyes.mp3");
        listeMusique.add("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic/MASTER_KG-WAYAWAYA.mp3");
        
        
    }
    @Override
        public void run() {
          
            while(getRunrecevoir().getMessage()!=null){
                
                if(FindCaractInWord(getRunrecevoir().getMessage())==true){
                    if(getRunrecevoir().getMessage().compareTo("musique")==0){
                        Thread transfer= new Thread(new TransferWithT("D:/L2/S3/Reseux/Streaming video/serveur/ListeMusic.txt",getClientSocket())) ;
                        transfer.start();
                        getOut().println("ListeMusic.txt");
                        p++;
                    }
                    else if(p==1){
                        Thread transfer= new Thread(new TransferWithT(choixMusique(getRunrecevoir().getMessage()),getClientSocket())) ;
                        transfer.start();
                        p=0;
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