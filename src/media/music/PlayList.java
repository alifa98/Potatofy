package media.music;
import java.util.ArrayList;

public class PlayList {
    private ArrayList<Song> songsArrayList;
    private String playListName;

    public PlayList(String name) {
        playListName = name;
        songsArrayList = new ArrayList<>();
    }

    public void addSong(Song song) {
        songsArrayList.add(song);
    }

    public String getPlayListName() {
        return playListName;
    }

    public ArrayList<Song> getSongsArrayList() {
        return songsArrayList;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
}
