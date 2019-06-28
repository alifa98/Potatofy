package mediaplayer.advancedPlayerWrapper;

import javazoom.jl.decoder.JavaLayerException;
import com.manager.Manager;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

class SongPlayerThread implements Runnable {
    private volatile AdvancedPlayer player;
    private volatile boolean needsUpdate;
    private volatile int startingFrame;
    private volatile int finalFrame;
    private volatile boolean keepAlive = true;
    private volatile boolean isPlaying;
    private volatile long lastPlayTime;
    private volatile boolean pause;

    SongPlayerThread(AdvancedPlayer advancedPlayer) {
        player = advancedPlayer;
        needsUpdate = false;
        startingFrame = 0;
        finalFrame = Integer.MAX_VALUE;
        isPlaying = false;
        pause=true;
    }



    @Override
    public void run() {
        while (keepAlive){

            if(keepAlive && pause){
                try {
                    isPlaying=false;
                    setThreadToSleep();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            try {
                isPlaying=true;
                if (!player.play(1)){
                    keepAlive=false;
                }
            } catch (JavaLayerException e) {
                keepAlive=false;
            }
            startingFrame=startingFrame+1;



        }
        try{
            player.stop();
        }catch (Exception e){

        }

        player.close();
        Manager.setFinishedSong(true);
        //end of stream event

    }
    synchronized void pause(){
        pause=true;
    }

    synchronized void resume(){
        pause=false;
        notifyAll();
    }

    synchronized void kill(){
        keepAlive=false;
        resume();
    }


    synchronized int getCurrentFrame(){
        return startingFrame;
    }


    private synchronized void setThreadToSleep() throws InterruptedException {
        wait();
    }


    public void seekTo(int frame , InputStream stream) {
        synchronized (player) {
            player.close();
            try {
                player = new AdvancedPlayer(stream);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
            try {
                player.play(frame, frame + 1);
                startingFrame=frame+1;
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
