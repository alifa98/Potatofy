package IO;

import media.music.PlayList;
import media.music.Song;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {
    private MetaData metaData;
    private ArrayList<SerializableSong> songs;
    private ArrayList<SerializablePlaylist> playLists;
    private static final String dataURL = "src\\saved data\\data.bin";

    public FileManager() {

    }

    public void setData(MetaData metaData, ArrayList<SerializableSong> songs, ArrayList<SerializablePlaylist> playLists) {
        this.metaData = metaData;
        this.songs = songs;
        this.playLists = playLists;
    }

    public void writeData() throws IOException {
        ObjectOutputStream out;
        out = new ObjectOutputStream(new FileOutputStream(dataURL));

        out.writeObject(metaData);
        out.writeObject(songs);
        out.writeObject(playLists);
        out.close();

    }


}
