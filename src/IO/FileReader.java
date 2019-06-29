package IO;

import media.music.PlayList;
import media.music.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileReader {
    private MetaData metaData;
    private ArrayList<SerializableSong> serializableSongs;
    private ArrayList<SerializablePlaylist> serializablePlaylists;


    private static final String dataURL = "src\\saved data\\data.bin";

    public FileReader(){


    }

    public boolean hasSavedData(){
        File savedDataFile=new File(dataURL);
        return savedDataFile.exists();
    }

    public void readSavedData() throws IOException, ClassNotFoundException {
        ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File(dataURL)));
        metaData=(MetaData) in.readObject();
        serializableSongs= (ArrayList<SerializableSong>)in.readObject();
        serializablePlaylists= (ArrayList<SerializablePlaylist>) in.readObject();
        in.close();

    }

    public ArrayList<SerializablePlaylist> getSerializablePlaylists() {
        return serializablePlaylists;
    }

    public ArrayList<SerializableSong> getSerializableSongs() {
        return serializableSongs;
    }

    public MetaData getMetaData() {
        return metaData;
    }

}
