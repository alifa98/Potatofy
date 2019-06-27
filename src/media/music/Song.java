package media.music;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Song {
    private File source;
    private String title;
    private String artist;
    private String album;
    private int genre;
    private String year;
    private long songLengthMilliseconds;//song length in milliseconds
    private int frameCount;
    private double sampleRate;


    public Song(File source) throws InvalidDataException, IOException, UnsupportedTagException {
        this.source = source;
        updateFields();
    }

    private static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage residedImag = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = residedImag.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return residedImag;
    }

    public void updateFields() throws UnsupportedTagException, IOException, InvalidDataException {
        Mp3File mp3File = new Mp3File(source);
        if (!mp3File.hasId3v1Tag()) {
            throw new UnsupportedTagException("this file does't have Id3v1 tag");
        }
        //todo get id3v1tags by hand
        ID3v1 id3v1Tag = mp3File.getId3v1Tag();
        album = id3v1Tag.getAlbum();
        artist = id3v1Tag.getArtist();
        title = id3v1Tag.getTitle();
        genre = id3v1Tag.getGenre();
        year = id3v1Tag.getYear();
        frameCount = mp3File.getFrameCount();
        songLengthMilliseconds = mp3File.getLengthInMilliseconds();
        sampleRate = mp3File.getSampleRate();
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public long getSongLengthMilliseconds() {
        return songLengthMilliseconds;
    }

    public String getAlbum() {
        return album;
    }

    public File getSource() {
        return source;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public double getSampleRate() {
        return sampleRate;
    }

    public boolean hasAlbumImage() throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File(source);
        if (!mp3File.hasId3v2Tag()) return false;
        ID3v2 id3v2tag = mp3File.getId3v2Tag();
        if (id3v2tag.getAlbumImageMimeType() == null) return false;
        return true;
    }

    public byte[] getAlbumImage() throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File(source);
        if (!hasAlbumImage()) return null;
        ID3v2 tag = mp3File.getId3v2Tag();
        return tag.getAlbumImage();
    }

    public Icon getAlbumImageAsSize(int width, int height) throws InvalidDataException, IOException, UnsupportedTagException {
        if (!hasAlbumImage()) return null;
        ByteArrayInputStream bis = new ByteArrayInputStream(this.getAlbumImage());
        Image image1 = ImageIO.read(bis).getScaledInstance(48, 48, Image.SCALE_DEFAULT);
        return new ImageIcon(image1);
    }

    public double getMSPerFrame() {
        return songLengthMilliseconds / frameCount;
    }
}
