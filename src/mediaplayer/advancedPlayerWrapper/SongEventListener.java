package mediaplayer.advancedPlayerWrapper;

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

class SongEventListener extends PlaybackListener {

    private AdvancedPlayerWrapper advancedPlayerWrapper;


    public SongEventListener(AdvancedPlayerWrapper player){
        advancedPlayerWrapper=player;
    }

    @Override
    public void playbackStarted(PlaybackEvent playbackEvent) {
        //advancedPlayerWrapper.listenerPlaybackStarted(playbackEvent);
    }

    @Override
    public void playbackFinished(PlaybackEvent playbackEvent) {
        //advancedPlayerWrapper.listenerPlaybackFinished(playbackEvent);
    }



}