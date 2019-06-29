package IO;

import media.music.PlayList;
import media.music.Song;

import java.io.Serializable;
import java.util.ArrayList;

public class SerializablePlaylist implements Serializable {
    private String playlistName;
    private ArrayList<SerializableSong> serializableSongs;

    void setData(PlayList playlist){
        playlistName=playlist.getPlayListName();
        serializableSongs=new ArrayList<>();
        ArrayList<Song> songArrayList=playlist.getSongsArrayList();
        for(Song song:songArrayList){
            SerializableSong songData=new SerializableSong();
            songData.setDataFromSong(song);
            serializableSongs.add(songData);
        }
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public ArrayList<SerializableSong> getSerializableSongs() {
        return serializableSongs;
    }
}
