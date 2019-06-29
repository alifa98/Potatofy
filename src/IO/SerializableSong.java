package IO;

import media.music.Song;

import java.io.Serializable;

public class SerializableSong implements Serializable {
    private String source;
    private long lastPlayTime;
    private boolean isFavorite;
    private boolean isShared;


    void setDataFromSong(Song song){
        source=song.getSource().getAbsolutePath();
        lastPlayTime=song.getTimeStamp();
        isFavorite=song.isFavorite();
        isShared=song.isShared();
    }

    public String getSource() {
        return source;
    }

    public long getLastPlayTime() {
        return lastPlayTime;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean isShared() {
        return isShared;
    }
}
