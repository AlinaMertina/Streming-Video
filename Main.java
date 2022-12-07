package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import client.*;
import image.*;
import java.lang.*;
import client.*;

public class Main{

    public static void main(String[] test) {
 
        Socket clientSocket ;
        BufferedReader in;
        PrintWriter out;
        Scanner sc=new Scanner(System.in);
     
            try {
                    clientSocket = new Socket("192.168.95.1",1780);
                    out = new PrintWriter(clientSocket.getOutputStream());
                    in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
                   
                    Runenvoi en = new Runenvoi(out,sc);
                    Runrecevoir rec = new Runrecevoir(clientSocket,in,out);
                    rec.setRunenvoi(en);
                    en.setRunrecevoir(rec);
                    Fenetre frame = new Fenetre(en,rec);
                    
                        Thread envoi= new Thread(en) ;
                            envoi.start();
                        Thread recevoir= new Thread(rec);
                        recevoir.start();

                }catch (IOException e) {
                    e.printStackTrace();
                }
           
        }
}