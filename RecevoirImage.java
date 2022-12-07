package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.FileNameMap;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import java.lang.Byte;
import image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Vector;
import java.awt.image.BufferedImage;

public class RecevoirImage{
    DataOutputStream dataOutputStream = null;
    DataInputStream dataInputStream = null;
    Socket socketClient;
    Runenvoi envoiMessage; 
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
    public RecevoirImage(Socket s,Runenvoi r){
        setSocketClient(s);
        setEnvoiMessage(r);
    }
   
    public  void  RecevoirSImage() throws Exception{
        dataInputStream = new DataInputStream(getSocketClient().getInputStream());
        dataOutputStream = new DataOutputStream(getSocketClient().getOutputStream());
        int bytes = 0;
        long size= dataInputStream.readLong();
        byte[] buffer = new byte[4 * 1024];
        Vector<Byte> img = new Vector<Byte>();
        while (true) {
            byte b = dataInputStream.readByte();
            if(dataInputStream.available()==0){
                break;
            }else{
                img.add(b);
            }
       }
        Object[] imageBT = img.toArray();
        byte[] picture = new byte[imageBT.length];
        for(int i=0;i<picture.length;i++){
            picture[i]=(byte) imageBT[i];
        }
        ByteArrayInputStream byteStream = new ByteArrayInputStream(picture);
        BufferedImage bufferdimage = ImageIO.read(byteStream);
        ImageIcon icon = new ImageIcon(bufferdimage);
        Image imgi = icon.getImage();
        FenetreImage fenetre = new FenetreImage(imgi,getEnvoiMessage());
    }
}