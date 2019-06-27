package com.manager;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.MainFrame;
import gui.bottomPanels.BottomPanel;
import javazoom.jl.decoder.JavaLayerException;
import media.music.Song;
import mediaplayer.advancedPlayerWrapper.AdvancedPlayerWrapper;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is Manager Prototype
 * Most of it is HardCoded for now
 */
public class Manager {
    private static final String songURL = "D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\03 Stan (Featuring Dido).mp3";
    private MainFrame mainFrame;
    private BottomPanel bottomPanel;
    private AdvancedPlayerWrapper songPlayer;
    private Song activeSong;
    private boolean isActivatedByInterval = false;
    private boolean songSliderMouseDown=false;
    private boolean isPlayingSong=false;

    //todo remove the singleton
    //initialize every eventListener in this class
    public Manager() {

        mainFrame = new MainFrame("potato");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        bottomPanel = mainFrame.getBottomPanel();
        Timer timer = new Timer(500, new TimerEventListener(this));
        timer.setRepeats(true);
        timer.start();

    }



    public void setEventListeners() {
        bottomPanel.setEventListeners(this);
    }


    void intervalRun() {
        isActivatedByInterval = true;
        if (songPlayer != null && songPlayer.isPlaying() && !songSliderMouseDown) {
            updateSlider();
        }
        isActivatedByInterval = false;
    }

    private void updateSlider() {
        if (songPlayer != null) {
            bottomPanel.setCurrentTime((long) (songPlayer.getCurrentFrame() * activeSong.getMSPerFrame()));
        }
    }


    public void songSliderChangeEvent() {
        if (!isActivatedByInterval && songPlayer!=null &&!songSliderMouseDown) {
            int sliderValue = bottomPanel.getSliderValue();
            try {
                songPlayer.goToFrame(sliderValue * activeSong.getFrameCount() / BottomPanel.MAX_SLIDER_VALUE,isPlayingSong);
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        }

        if(isPlayingSong && !songPlayer.isPlaying()){
            try {
                songPlayer.resume();
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public void playTheHardCodedSong() {
        activeSong = null;
        try {
            activeSong = new Song(new File(songURL));
        } catch (InvalidDataException | IOException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        try {
            bottomPanel.setSong(activeSong);
        } catch (InvalidDataException | IOException | UnsupportedTagException e) {
            e.printStackTrace();
        }

        songPlayer = null;
        try {

            songPlayer = new AdvancedPlayerWrapper(activeSong.getSource(), activeSong.getMSPerFrame());
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
        isPlayingSong=true;
        songPlayer.play();

    }

    public void setSliderMouseDownEvent() {
        songSliderMouseDown=true;
    }
    public void setSliderMouseUpEvent() {
        songSliderMouseDown=false;
        songSliderChangeEvent();
        updateSlider();

    }
    public void setSliderMouseExitEvent() {

    }

    public void songSliderChangeEventCall() {

    }
}
