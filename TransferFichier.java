package serveur;
import java.io.*;              
import java.net.Socket;

public class TransferFichier{
    File fichier ;
    DataOutputStream dataOutputStream = null;
    DataInputStream dataInputStream = null;
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
            File file = getfichier();
            FileInputStream fileInputStream= new FileInputStream(file);
            dataOutputStream.writeLong(file.length());
        byte[] buffer = new byte[4 * 1024];
        int p=0;
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer,0, bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
        System.out.println("tonga");
    }
}