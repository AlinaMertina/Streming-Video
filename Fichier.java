 package client;

import java.io.*;
public class Fichier
{
    public Fichier(){}
    public void Ecrire(String nom_fichier,String  tabe1)
    {
   
        File fichier = new File(nom_fichier);
        
            try{
                FileWriter ecrire = new FileWriter(fichier,true);
                BufferedWriter bw = new BufferedWriter(ecrire);
                    bw.write(tabe1);
                    bw.newLine();
                    bw.close();
                    ecrire.close();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        
    }
}