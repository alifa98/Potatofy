package gui.bottomPanels;

import com.TimeData;
import com.manager.Manager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import mdlaf.utils.MaterialColors;
import media.music.Song;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BottomPanel extends JPanel {

    private SongInfo songInfo;
    private ControlButtons controlButtons;
    private SongSlider songSlider;
    private VolumeControl volumeControl;
    private Song displaySong;

    private long totalSongLengthMS;
    private long currentPlaytimeMS;

    public static final int MAX_SLIDER_VALUE=65535;

    private static final int ALBUM_IMAGE_DIMENSION =64;

    public BottomPanel(){
        super();


        songInfo=new SongInfo();
        controlButtons=new ControlButtons();
        songSlider=new SongSlider();
        volumeControl=new VolumeControl();

        setLayout(new GridBagLayout());
        setSize(0,500);
        setMaximumSize(new Dimension(0,500));
        setBackground(MaterialColors.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1;
        c.gridy=0;
        c.gridheight=500;
        c.weightx=1;
        add(songInfo,c);

        c.weightx=1;
        c.gridx=2;
        add(controlButtons,c);

        c.gridx=3;
        c.weightx=5;
        add(songSlider,c);

        c.gridx=4;
        c.weightx=1;
        add(volumeControl,c);


    }

    public void setSong(Song song) throws InvalidDataException, IOException, UnsupportedTagException {
        //should update Album Art, title , artist, Slider label ,song slider
        displaySong=song;
        currentPlaytimeMS=0;
        updatePanels();
        setCurrentTime(0);
    }

    private void updatePanels() throws InvalidDataException, IOException, UnsupportedTagException {
        songInfo.setArtist(displaySong.getArtist());
        songInfo.setSongTitle(displaySong.getTitle());
        songInfo.setAlbumImage(displaySong.getAlbumImageAsSize(ALBUM_IMAGE_DIMENSION, ALBUM_IMAGE_DIMENSION));
        songSlider.setTotalSongTime(TimeData.reformatMilisecForSong(displaySong.getSongLengthMilliseconds()));
        totalSongLengthMS=displaySong.getSongLengthMilliseconds();
    }

    /**
     *
     * @param playTime  play time in milliseconds
     */
    public void setCurrentTime(long playTime){
        songSlider.setCurrentSongTime(TimeData.reformatMilisecForSong(playTime));
        currentPlaytimeMS=playTime;
        int sliderPosition;

        songSlider.setSliderPosition(calculateSliderPosition());
    }


    private int calculateSliderPosition(){
        double result;
        result =currentPlaytimeMS;
        result/=totalSongLengthMS;
        result*=MAX_SLIDER_VALUE;

        return (int)result;

    }

    public int getSliderValue(){
        return songSlider.getSliderValue();
    }

    public void setEventListeners(Manager manager){
        songSlider.setSliderEvents(manager);
        volumeControl.setVolumeSliderEvents(manager);
        controlButtons.setControlButtonsListener(manager);
    }

    public void setVolumeSliderValue(int value){
        volumeControl.setSliderValue(value);
    }


}
