package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.FileNameMap;
import java.net.ServerSocket;
import java.net.Socket;
import lecteurvideo.*;
import javax.swing.SwingUtilities;
import client.*;


public class RecevoirVideo {
    DataOutputStream dataOutputStream = null;
    DataInputStream dataInputStream = null;
    String nomFichier;
    Socket socketClient;
    RunVideo run;
    Runenvoi envoiMessage; 
    Boolean notclose = true;
    FileOutputStream fileOutputStream;
    public void closeFileOutputStream(){
        notclose=false;
        try {
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public RunVideo getRunVideo(){
        return run;
    }
    public Runenvoi getEnvoiMessage(){
        return envoiMessage;
    }
    public void setEnvoiMessage(Runenvoi run){
        envoiMessage=run;
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
    public RecevoirVideo(Socket s,String nomFile,Runenvoi en){
        setSocketClient(s);
        setTitre(nomFile);
        setEnvoiMessage(en);
       
    }

    
    public  void resaveAndPlay()throws Exception{
        
        dataInputStream = new DataInputStream(getSocketClient().getInputStream());
        dataOutputStream = new DataOutputStream(getSocketClient().getOutputStream());
       
            int i=0;
            int bytes = 0;
            fileOutputStream = new FileOutputStream(getTitre());
            long size= dataInputStream.readLong(); // read file size
            if(size<0){
                return ;
            }
            System.out.println("size"+size);
            byte[] buffer = new byte[4 * 1024];
            //
            InitialisationNB.LoadLibraryNB("C:\\Program Files (x86)\\VideoLAN\\VLC");
            System.out.println(getTitre());
            run = new RunVideo(getTitre(),getEnvoiMessage());
            run.setRecevoirVideo(this);//mba avitana mi close an'le ficher raha misi next na previus
            Thread runVid = new Thread(run);
            //
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