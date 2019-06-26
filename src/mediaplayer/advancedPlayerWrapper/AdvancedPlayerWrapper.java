package mediaplayer.advancedPlayerWrapper;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import mediaplayer.advancedPlayerWrapper.SongEventListener;
import mediaplayer.advancedPlayerWrapper.SongPlayerThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class AdvancedPlayerWrapper {

    private volatile AdvancedPlayer player;
    private volatile File songFile;
    private volatile InputStream inputStream;
    private volatile SongEventListener playBackListner;
    private volatile Thread playerThread;
    private volatile SongPlayerThread runnablePlayer;
    private volatile int lastFrame;

    private static final double msPerFrame = 26.122449;
    private static Date date = new Date();

    public AdvancedPlayerWrapper(File source) throws FileNotFoundException, JavaLayerException {
        songFile = source;
        inputStream = new FileInputStream(source);
        player = new AdvancedPlayer(inputStream);
        playBackListner = new SongEventListener(this);
        player.setPlayBackListener(playBackListner);
        runnablePlayer = new SongPlayerThread(player);
        playerThread = new Thread(runnablePlayer);
        playerThread.start();
        lastFrame = 0;


    }


    public synchronized void pause() {
        runnablePlayer.stop();
    }

    public synchronized void resume() throws FileNotFoundException, JavaLayerException {
        makeNewPlayer();
        runnablePlayer.updatePlayer(player);


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
        lastFrame = frameNumber;
        makeNewPlayer();
        runnablePlayer.updatePlayer(player);

    }

    public synchronized int getCurrentFrame() throws FileNotFoundException, JavaLayerException {
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
        runnablePlayer.updatePlayer(player);

    }

    private synchronized void makeNewPlayer() throws FileNotFoundException, JavaLayerException {
        runnablePlayer.setStartingFrame(lastFrame);
        inputStream = new FileInputStream(songFile);
        lastFrame=0;
        player = new AdvancedPlayer(inputStream);
        player.setPlayBackListener(playBackListner);
    }


    //listener functions
    // don't touch it :D
    synchronized void listenerPlaybackStarted(PlaybackEvent playbackEvent) {
        //System.err.println("startingFrame:" +playbackEvent.getFrame());
    }

    synchronized void listenerPlaybackFinished(PlaybackEvent playbackEvent) {
        lastFrame += playbackEvent.getFrame() / msPerFrame;//todo. maybe generalize the hardcoded number

    }


}


