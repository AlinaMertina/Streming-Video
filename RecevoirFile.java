package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.lang.*;
import lecteurvideo.*;

public class RecevoirFile {//recevoir Audio

    DataOutputStream dataOutputStream = null;
    DataInputStream dataInputStream = null;
    String nomFichier;
    Socket socketClient;
    Thread lecture = new Thread();
    RunAudio run;
    Runenvoi envoiMessage; 
    long size;
    public long getSizeVideo(){
        return size;
    }

    Boolean notclose = true;
    FileOutputStream fileOutputStream;
    public RunAudio getRunAudio(){
        return run;
    }
    public Runenvoi getEnvoiMessage(){
        return envoiMessage;
    }
    public void setEnvoiMessage(Runenvoi run){
        envoiMessage=run;
    }
    public void closeFileOutputStream(){
        notclose=false;
        try {
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
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
    public RecevoirFile(Socket s,String nomFile,Runenvoi run){
        setSocketClient(s);
        setTitre(nomFile);
        setEnvoiMessage(run);
    }
   
    public void receiveFile()throws Exception{
        dataInputStream = new DataInputStream(getSocketClient().getInputStream());
        dataOutputStream = new DataOutputStream(getSocketClient().getOutputStream());
            int i=0;
            int bytes = 0;
            fileOutputStream = new FileOutputStream(getTitre());
            size= dataInputStream.readLong(); // read file size
            byte[] buffer = new byte[4 * 1024];
            //
            InitialisationNB.LoadLibraryNB("C:\\Program Files (x86)\\VideoLAN\\VLC");
            run = new RunAudio(getTitre(),getEnvoiMessage());
            run.setRecevoirFile(this);
            Thread runVid = new Thread(run);//thread qui va faire fonctionner le lecteur audio
                    
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0,(int)Math.min(buffer.length, size)))!= -1) {
                if(notclose==true){
                    fileOutputStream.write(buffer, 0, bytes);
                }
                else{
                    break;
                }
                size -= bytes; 
                if(i==0){
                    runVid.start();
                        i++;
                    }
            }
            fileOutputStream.close();
            System.out.println("File is Received");
    }

}