
package image;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
public class  JpanelImage extends JPanel{
    Image img ;
    public void seImage(Image im){
        img=im;
    }
    public Image getImage(){
        return img;
    }
    public JpanelImage(Image im){
        seImage(im);
    }
    public void paintComponent(Graphics g){
      
        g.drawImage(getImage(),0,0,500,500,null);
    }
}