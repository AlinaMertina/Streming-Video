package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class RecevoirFile {

    static DataOutputStream dataOutputStream = null;
    static DataInputStream dataInputStream = null;
    String nomFichier;
    Socket socketClient;
    Player jlPlayer;
    Thread lecture = new Thread();

    public void setSocketClient(Socket s){
        socketClient=s;
    }
    public Socket getSocketClient(){
        return socketClient;
    }
    public void setTitre(String t){
        nomFichier=t;
    }
    public String getTitre(){
        return nomFichier;
    }
    public RecevoirFile(Socket s,String nomFile){
        setSocketClient(s);
        setTitre(nomFile);
    }
   

    public void receiveFile()throws Exception{
        try {
           int p=0;
            dataInputStream = new DataInputStream(getSocketClient().getInputStream());
            dataOutputStream = new DataOutputStream(getSocketClient().getOutputStream());
            jlPlayer = new Player(getSocketClient().getInputStream() );
            lecture =  new Thread( new ThreadLecteurAudio(jlPlayer) );
            lecture.start();
            lecture.sleep(5000);
          
                
        } catch (Exception e) {
           System.out.println(e);
        }
        
    }

    public void play(String mp3FileToPlay ) {
        try {
            FileInputStream fileInputStream = new FileInputStream( mp3FileToPlay );
            BufferedInputStream bufferedInputStream = new BufferedInputStream( fileInputStream );
            jlPlayer = new Player( fileInputStream );
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + mp3FileToPlay);
            System.out.println(e.getMessage());
        }
        Thread lecture =  new Thread( new ThreadLecteurAudio(jlPlayer) );
        lecture.start();
        
    }
    
    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
}