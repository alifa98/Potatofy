package IO;

import media.music.Song;

import java.io.Serializable;

public class MetaData implements Serializable {
    private SerializableSong serializableActiveSong;
    private int frameNumber;
    private int volumeStatus;
    public MetaData(Song ActiveSong, int frameNumber, int volumeStatus){
        serializableActiveSong=new SerializableSong();
        serializableActiveSong.setDataFromSong(ActiveSong);
        this.frameNumber=frameNumber;
        this.volumeStatus=volumeStatus;

    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int getVolumeStatus() {
        return volumeStatus;
    }

    public SerializableSong getSerializableActiveSong() {
        return serializableActiveSong;
    }
}
