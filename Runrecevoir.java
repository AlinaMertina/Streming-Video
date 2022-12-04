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
            
            //tant que le client est connecté
            while(getMessage()!=null){
                if(FindCaractInWord(getMessage())==true){
                    System.out.println(getMessage());// important
                }
                if(getMessage().compareTo("ListeMusic.txt")==0){
                    GetListeMusique recevoirFile = new GetListeMusique(getClientSocket(),"ListeMusic.txt");
                    try {
                        recevoirFile.receiveFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                    ShowListe show = new ShowListe("ListeMusic.txt",new Fenetre(getRunenvoi(),this));
                }
                else if(FindCaractInWord(getMessage())==false){
                    if(h==0){
                        RecevoirFile recevoirFile = new RecevoirFile(getClientSocket(),"Ficher.mp3");
                    try {
                        recevoirFile.receiveFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                        h++;
                    }
                }
                setMessage(getIn().readLine());
            }
            //sortir de la boucle si le client a déconecté
            System.out.println("Serveur déconecté");
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