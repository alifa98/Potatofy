package mediaplayer.advancedPlayerWrapper;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileNotFoundException;
import java.util.Date;

class SongPlayerThread implements Runnable {
    private volatile AdvancedPlayer player;
    private volatile boolean needsUpdate;
    private volatile int startingFrame;
    private volatile int finalFrame;
    private volatile boolean keepAlive = true;
    private volatile boolean isPlaying;
    private volatile long lastPlayTime;

    SongPlayerThread(AdvancedPlayer advancedPlayer) {
        player = advancedPlayer;
        needsUpdate = false;
        startingFrame = 0;
        finalFrame = Integer.MAX_VALUE;
        isPlaying = false;
    }


    synchronized void updatePlayer(AdvancedPlayer newPlayer) {

        updatePlayer(newPlayer,false);
    }

    synchronized void updatePlayer(AdvancedPlayer newPlayer, boolean playAfterUpdate) {

        needsUpdate = true;

        if (isPlaying) {
            player.stop();
            player.close();
        }


        player = newPlayer;

        try {
            Thread.sleep(1);//todo remove
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(playAfterUpdate)
        notify();
    }


    /**
     * just updates its fields. does not effect the song that is currently playing.
     * call updatePlayer to apply it.
     *
     * @param startingFrame :\
     */
    synchronized void setStartingFrame(int startingFrame) {//todo maybe update the player????
        this.startingFrame = startingFrame;
    }

    synchronized void setFinalFrame(int finalFrame) {
        this.finalFrame = finalFrame;
    }

    synchronized boolean isPlaying() {
        return isPlaying;
    }


    /**
     * initial playing
     * call it after you called the constructor
     */
    synchronized void play() {
        if (!isPlaying) {
            needsUpdate = true;
            notify();
        }

    }

    synchronized void stop() {
        if (isPlaying) {
            player.stop();
            player.close();
        }
    }

    synchronized long getLastPlayTime() {
        return lastPlayTime;
    }

    private synchronized void setThreadToSleep() throws InterruptedException {
        wait();
    }

    @Override
    public void run() {
        while (keepAlive) {
            if (needsUpdate) {
                isPlaying = true;
                Date date = new Date();
                lastPlayTime= date.getTime();
                try {
                    player.play(startingFrame, finalFrame);
                } catch (JavaLayerException e) {
                    e.printStackTrace();//todo how to handle it?
<<<<<<< HEAD

=======
>>>>>>> master
                }
                isPlaying = false;
                needsUpdate = false;
            }

            try {
                setThreadToSleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
