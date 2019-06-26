package Test;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.ImageLabel;
import media.music.Song;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class SongId3tagTest
{
    private static final String url="D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\03 Stan (Featuring Dido).mp3";
    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        Song song=new Song(new File(url));
        System.out.println(song.getAlbum());
        System.out.println(song.getArtist());
        System.out.println(song.getFrameCount());
        System.out.println(song.getTitle());
        System.out.println(song.getSongLengthMilliseconds());
        System.out.println(song.getSampleRate());
        System.out.println(song.hasAlbumImage());
        if(song.hasAlbumImage()){
            JFrame frame=new JFrame();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            JPanel image=new JPanel();
            Icon icon=song.getAlbumImageAsSize(128,128);
            JLabel label=new JLabel();
            label.setIcon(icon);

            image.add(label);
            frame.add(image);

        }
    }



}
