package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;
import java.util.regex.*;
import java.text.ParseException;

public class Runenvoi implements Runnable{
    PrintWriter out;
    Scanner sc;

    String message = new String();
    Runrecevoir run;
    static String Action= new String();
    public void setAction(String a){
        Action=a;
    }
    public String getAction(){
        return Action;
    }
    public void setRunrecevoir(Runrecevoir r){
        run=r;
    }
    public Runrecevoir getRunrecevoir(){
        return  run;
    }
    public void setScanner(Scanner s){
        sc=s;
    }
    public Scanner getScanner(){
        return sc;
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
    public  Runenvoi(PrintWriter pf,Scanner sa){
        setOut(pf);
        setScanner(sa);
    }
    @Override
        public void run() {
            while(getMessage()!=null){
                if(FindCaractInWord(getMessage())==true){
                    getOut().println(getMessage());
                    if(getMessage().toLowerCase().contains("a")==true && getMessage().toLowerCase().contains("i")!=true){
                        setAction(getMessage());
                    }
                    getOut().flush();
                    setMessage("11111");
                }
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