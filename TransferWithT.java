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

public class TransferWithT implements Runnable{
    
    static Socket SocketServeur ;
    String emplacemetFile;

    public void setEmplacemetFile(String r){
        emplacemetFile=r;
    }
    public String getEmplacemetFile(){
        return emplacemetFile;
    }
    public void setServeurSocket(Socket ser){
        SocketServeur=ser;
    }
    public Socket getServeurSocket(){
        return SocketServeur;
    }
    public TransferWithT(String emp,Socket s){
        setServeurSocket(s);
        setEmplacemetFile(emp);
    }
    @Override
    public void run() {
        TransferFichier transf = new TransferFichier(new File(getEmplacemetFile()),getServeurSocket());
                    try {
                        transf.sendFile();
                    } catch (Exception e) {
                       System.out.println(e);
                    }
    }

}