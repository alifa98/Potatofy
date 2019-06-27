package mediaplayer.advancedPlayerWrapper;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class AdvancedPlayerWrapper {

    private volatile AdvancedPlayer player;
    private volatile File songFile;
    private volatile InputStream inputStream;
    private volatile SongEventListener playBackListener;
    private volatile Thread playerThread;
    private volatile SongPlayerThread runnablePlayer;
    private volatile int lastFrame;

    private  double msPerFrame ;
    private static Date date = new Date();

    public AdvancedPlayerWrapper(File source,double msPerFrame) throws FileNotFoundException, JavaLayerException {
        songFile = source;
        inputStream = new FileInputStream(source);
        player = new AdvancedPlayer(inputStream);
        playBackListener = new SongEventListener(this);
        player.setPlayBackListener(playBackListener);
        runnablePlayer = new SongPlayerThread(player);
        playerThread = new Thread(runnablePlayer);
        this.msPerFrame=msPerFrame;
        playerThread.start();
        lastFrame = 0;


    }


    public synchronized void pause() {
        runnablePlayer.stop();
    }

    public synchronized void resume() throws FileNotFoundException, JavaLayerException {
        makeNewPlayer();
        runnablePlayer.updatePlayer(player,true);


    }

    public synchronized void play() {
        runnablePlayer.play();
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
    public synchronized void goToFrame(int frameNumber) throws FileNotFoundException, JavaLayerException {
        goToFrame(frameNumber,false);

    }
    public synchronized void goToFrame(int frameNumber , boolean playAfterUpdate) throws FileNotFoundException, JavaLayerException {
        lastFrame = frameNumber;
        makeNewPlayer();
        runnablePlayer.updatePlayer(player,playAfterUpdate);

    }





    public synchronized int getCurrentFrame() {
        if (runnablePlayer.isPlaying()) {

            date=new Date();
            //System.out.println("frames since lastFrame: "+((int) ((date.getTime() - runnablePlayer.getLastPlayTime()) / msPerFrame)));
            return lastFrame + ((int) ((date.getTime() - runnablePlayer.getLastPlayTime()) / msPerFrame));

        }
        return lastFrame;

    }

    public void setSongFile(File songFile) throws FileNotFoundException, JavaLayerException {
        this.songFile = songFile;
        makeNewPlayer();
        lastFrame=0;
        runnablePlayer.updatePlayer(player);

    }

    private synchronized void makeNewPlayer() throws FileNotFoundException, JavaLayerException {
        InputStream placeHolder=inputStream;
        runnablePlayer.setStartingFrame(lastFrame);
        inputStream = new FileInputStream(songFile);
        player = new AdvancedPlayer(inputStream);
        player.setPlayBackListener(playBackListener);
        placeHolder=null;
    }


    //listener functions
    // don't touch it :D
    synchronized void listenerPlaybackStarted(PlaybackEvent playbackEvent) {
        //System.err.println("startingFrame:" +playbackEvent.getFrame());
    }

    synchronized void listenerPlaybackFinished(PlaybackEvent playbackEvent) {
        lastFrame += playbackEvent.getFrame() / msPerFrame;

    }


}


