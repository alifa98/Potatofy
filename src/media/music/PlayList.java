package media.music;

import java.util.ArrayList;

public class PlayList {
    private ArrayList<Song> songsArrayList;
    private String playListName;
    public PlayList(String name){
        playListName = name;
    }
    public void addSong(Song song){
        songsArrayList.add(song);
    }

    public String getPlayListName() {
        return playListName;
    }

    public ArrayList<Song> getSongsArrayList() {
        return songsArrayList;
    }
    public void removeSong(Song song){
            songsArrayList.remove(song);
    }
}
