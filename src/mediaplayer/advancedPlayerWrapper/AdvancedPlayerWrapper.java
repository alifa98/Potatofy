package mediaplayer.advancedPlayerWrapper;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.util.Date;

public class AdvancedPlayerWrapper {

    private volatile AdvancedPlayer player;
    private volatile File songFile;
    private volatile InputStream inputStream;
    private volatile SongEventListener playBackListener;
    private volatile Thread playerThread;
    private volatile SongPlayerThread runnablePlayer;
    private volatile int startingFrame;

    private static Date date = new Date();

    public AdvancedPlayerWrapper(File source) throws FileNotFoundException, JavaLayerException {
        songFile = source;
        inputStream = new FileInputStream(source);
        player = new AdvancedPlayer(inputStream);
        playBackListener = new SongEventListener(this);
        player.setPlayBackListener(playBackListener);
        runnablePlayer = new SongPlayerThread(player);
        playerThread = new Thread(runnablePlayer);
        playerThread.start();
        startingFrame = 0;


    }


    public synchronized void pause() {
        runnablePlayer.pause();
    }

    public synchronized void resume() throws IOException, JavaLayerException {
        if(playerThread.getState()!=Thread.State.TERMINATED)
            runnablePlayer.resume();
        else{
            System.out.println("else resume");
            goToFrame(0);
            runnablePlayer.resume();

        }

    }




    public synchronized boolean isPlaying() {

        return runnablePlayer.isPlaying();
    }


    /**
     * Goes to the frame number and pauses.
     * call resume if you want to keep playing
     *
     * @param frameNumber the number of the frame to jump to
     */
    public synchronized void goToFrame(int frameNumber) throws IOException, JavaLayerException {
        goToFrame(frameNumber,false);

    }
    public synchronized void goToFrame(int frameNumber , boolean playAfterUpdate) throws IOException, JavaLayerException {
        startingFrame = frameNumber;
        makeNewPlayer();


    }





    public synchronized int getCurrentFrame() {
        if(playerThread.getState()!=Thread.State.TERMINATED)
            return  runnablePlayer.getCurrentFrame();
        return -1;

    }

    public void setSongFile(File songFile) throws IOException, JavaLayerException {
        //runnablePlayer.kill();
        this.songFile = songFile;
        startingFrame =0;
        makeNewThread();

    }

    private void makeNewThread() throws IOException, JavaLayerException {
        InputStream placeHolder=inputStream;
        System.out.println("reached make new thread");
        runnablePlayer.kill();
        inputStream=new FileInputStream(songFile);
        player=new AdvancedPlayer(inputStream);
        player.setPlayBackListener(playBackListener);
        runnablePlayer = new SongPlayerThread(player);
        playerThread = new Thread(runnablePlayer);
        playerThread.start();
        placeHolder.close();
    }

    private synchronized void makeNewPlayer() throws IOException, JavaLayerException {
        InputStream placeHolder=inputStream;
        inputStream = new FileInputStream(songFile);
        runnablePlayer.seekTo(startingFrame,inputStream);
        try {
            placeHolder.close();
        }catch (IOException e){

        }

    }






}


