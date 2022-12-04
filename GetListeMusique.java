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

public class GetListeMusique{
    static DataOutputStream dataOutputStream = null;
    static DataInputStream dataInputStream = null;
    String nomFichier;
    Socket socketClient;
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
    public GetListeMusique(Socket s,String nomFile){
        setSocketClient(s);
        setTitre(nomFile);
    }

    public void receiveFile()throws Exception{
        try {
        
            dataInputStream = new DataInputStream(getSocketClient().getInputStream());
            dataOutputStream = new DataOutputStream(getSocketClient().getOutputStream());
            int bytes = 0;
            FileOutputStream fileOutputStream = new FileOutputStream(getTitre());
            long size= dataInputStream.readLong(); // read file size
            byte[] buffer = new byte[4 * 1024];
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0,(int)Math.min(buffer.length, size)))!= -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; 
            }
            fileOutputStream.close();
           
                
        } catch (Exception e) {
        System.out.println(e);
        }
        
    }

}