package client;

import javazoom.jl.player.Player;
import java.lang.*;


public class ThreadLecteurAudio implements Runnable{
    Player jlPlayer;
    public ThreadLecteurAudio(Player jl){
        jlPlayer=jl;
    }
    @Override
    public void run() {
        try {
            jlPlayer.play();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}