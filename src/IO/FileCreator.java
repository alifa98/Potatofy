package IO;

import media.music.PlayList;
import media.music.Song;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileCreator {
    private MetaData metaData;
    private ArrayList<SerializableSong> serializableSongs;
    private ArrayList<SerializablePlaylist> serializablePlaylists;
    private static final String dataURL = "src\\saved data\\data.bin";

    public FileCreator() {

    }

    public void setData(MetaData metaData, ArrayList<Song> songs, ArrayList<PlayList> playLists) {
        this.metaData = metaData;

        this.serializableSongs =new ArrayList<>();
        for(Song song:songs){
            SerializableSong songData=new SerializableSong();
            songData.setDataFromSong(song);
            serializableSongs.add(songData);
        }

        serializablePlaylists=new ArrayList<>();
        for(PlayList playList : playLists){
            SerializablePlaylist playlistData=new SerializablePlaylist();
            playlistData.setData(playList);
            serializablePlaylists.add(playlistData);

        }

    }

    public void writeData() throws IOException {
        ObjectOutputStream out;
        out = new ObjectOutputStream(new FileOutputStream(dataURL));

        out.writeObject(metaData);
        out.writeObject(serializableSongs);
        out.writeObject(serializablePlaylists);
        out.close();

    }





}
