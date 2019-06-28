package media.music;
import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songsArrayList;
    private String AlbumName;

    public Album(String name) {
        AlbumName = name;
        songsArrayList = new ArrayList<>();
    }

    public void addSong(Song song) {
        songsArrayList.add(song);
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public ArrayList<Song> getSongsArrayList() {
        return songsArrayList;
    }

    public void removeSong(Song song) {
        songsArrayList.remove(song);
    }

}
