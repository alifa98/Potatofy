package Test;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import media.music.Song;

import java.io.File;
import java.io.IOException;

public class SongTest {
    public static void main(String[] args) {
        Song song=null;
        try {

            song = new Song(new File("D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\08 The Real Slim Shady.mp3"));
        } catch (InvalidDataException | UnsupportedTagException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(song.getFrameCount());
    }
}
