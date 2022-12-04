package serveur;
import java.io.*;              
import java.net.Socket;

public class TransferFichier{
    File fichier ;
    static DataOutputStream dataOutputStream = null;
    static DataInputStream dataInputStream = null;
    Socket socketClient ;

    public void setSocketClient(Socket s){
        socketClient=s;
    }
    public Socket getSocketClient(){
        return socketClient;
    }

    public void setfichier(File fi){
        fichier=fi;
    }
    public File getfichier(){
        return fichier;
    }
    public TransferFichier(File fil,Socket s){
        setfichier(fil);
        setSocketClient(s);
    }
    public void sendFile()throws Exception{
        dataInputStream = new DataInputStream(getSocketClient().getInputStream());
        dataOutputStream = new DataOutputStream(getSocketClient().getOutputStream());
       
        int bytes = 0;
        // Open the File where he located in your pc
            File file = getfichier();
            FileInputStream fileInputStream= new FileInputStream(file);
        // Here we send the File to Server
            dataOutputStream.writeLong(file.length());
        // Here we  break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer,0, bytes);
            dataOutputStream.flush();
        }
        // close the file here
        fileInputStream.close();
        System.out.println("tonga");
    }
}