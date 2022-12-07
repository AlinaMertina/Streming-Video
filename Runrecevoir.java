package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;

public class Runrecevoir implements Runnable{
    ServerSocket serveurSocket ;
    Socket clientSocket ;
    BufferedReader in;
    PrintWriter out;
    Runenvoi run;

    String message = new String(" ");
    
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

    public void setRunenvoi(Runenvoi r){
        run=r;
    }
    public Runenvoi getRunenvoi(){
        return run;
    }
    public Runrecevoir(){}
    public Runrecevoir(ServerSocket s,Socket so,BufferedReader bf,PrintWriter pf ){
        setServeurSocket(s);
        setClientSocket(so);
        setIn(bf);
        setOut(pf);
    }
    public void run() { 
      
        try {
            setMessage(getIn().readLine());
            //tant que le client est connecté
            while(getMessage()!=null){
                System.out.println("Client : "+getMessage());
                setMessage(getIn().readLine());//miandri an'le valeur ni printenle client izi vo mi excetuter an'le code ambany 
                System.out.println("message"+getMessage());
            }
            //sortir de la boucle si le client a déconecté
            System.out.println("Client déconecté");
            //fermer le flux et la session socket
            getOut().close();
            getClientSocket().close();
            getServeurSocket().close();
            } catch (IOException e) {
                    e.printStackTrace();
        }
    }
}