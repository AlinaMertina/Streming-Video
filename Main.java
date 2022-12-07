package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import serveur.*;

public class Main {
 
 public static void main(String[] test) {
 
    ServerSocket serveurSocket ;
    Socket clientSocket ;
    BufferedReader in;//mamaki amin'ny ligne de commanden'le client
    PrintWriter out;//manoratra amn'ny ligne de commande ny le client 
    Scanner sc=new Scanner(System.in);
    String messageserveur=new String();
 
        try {
            	serveurSocket = new ServerSocket(1780);
                clientSocket = serveurSocket.accept();
                System.out.println("client connecter");
                out = new PrintWriter(clientSocket.getOutputStream());
                in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
                Runenvoi run = new Runenvoi(serveurSocket,clientSocket,out,sc,in);
                Runrecevoir run1 = new Runrecevoir(serveurSocket,clientSocket,in,out);
                run.setRunrecevoir(run1);
                run1.setRunenvoi(run);

            Thread envoi= new Thread(run) ;
            envoi.start();
            Thread recevoir= new Thread(run1);
            recevoir.start();
            }catch (IOException e) {
                e.printStackTrace();
            }
    }
}