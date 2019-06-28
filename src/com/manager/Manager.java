package com.manager;


import com.manager.audio.Audio;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.MainFrame;
import gui.bottomPanels.BottomPanel;
import javazoom.jl.decoder.JavaLayerException;
import mdlaf.MaterialLookAndFeel;
import media.music.Song;
import mediaplayer.advancedPlayerWrapper.AdvancedPlayerWrapper;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is Manager Prototype
 * Most of it is HardCoded for now
 */
public class Manager {
    private static final String songURL = "D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\08 The Real Slim Shady.mp3";
    private MainFrame mainFrame;
    private BottomPanel bottomPanel;
    private AdvancedPlayerWrapper songPlayer;
    private Song activeSong;
    private boolean isActivatedByInterval = false;
    private boolean songSliderMouseDown = false;
    private boolean isPlayingSong = false;
    private ArrayList<Song> songs;


    public Manager() {
        mainFrame = new MainFrame("Jpotify");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        bottomPanel = mainFrame.getBottomPanel();
        Timer timer = new Timer(500, new TimerEventListener(this));
        timer.setRepeats(true);
        timer.start();

        songs = new ArrayList<>();

    }


    public void setEventListeners() {
        bottomPanel.setEventListeners(this);
        mainFrame.getTopPanel().getRightButtons().setEventListeners(this);
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
        if (!isActivatedByInterval && songPlayer != null && !songSliderMouseDown) {
            int sliderValue = bottomPanel.getSliderValue();
            try {
                songPlayer.goToFrame(sliderValue * activeSong.getFrameCount() / BottomPanel.MAX_SLIDER_VALUE, isPlayingSong);
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        }

        if (isPlayingSong && !songPlayer.isPlaying()) {
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

            songPlayer = new AdvancedPlayerWrapper(activeSong.getSource(), activeSong.getMSPerFrame(),activeSong.getFrameCount());

        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
        isPlayingSong = true;
        songPlayer.play();

    }

    public void setSliderMouseDownEvent() {
        songSliderMouseDown = true;
    }

    public void setSliderMouseUpEvent() {
        songSliderMouseDown = false;
        songSliderChangeEvent();
        updateSlider();
    }

    public void setSliderMouseExitEvent() {

    }

    public void songSliderChangeEventCall() {

    }

    public void volumeSliderMouseUpEvent(int sliderValue) {
        float masterVolume = (float) sliderValue / BottomPanel.MAX_SLIDER_VALUE;
        Audio.setMasterOutputVolume(masterVolume);
    }

    public void initialSetting() {
        bottomPanel.setVolumeSliderValue((int) (Audio.getMasterOutputVolume() * BottomPanel.MAX_SLIDER_VALUE));

    }

    public void setArtistsListToMain() {
        JScrollPane artistList = mainFrame.getScrollableArtistsPanel();
        mainFrame.setMainPanel(artistList);
    }

    public void setAlbumsListTMain() {
        JScrollPane albumList = mainFrame.getScrollableAlbumsPanel();
        mainFrame.setMainPanel(albumList);
    }

    public void setFavoritePlayListToMain() {
        JScrollPane favList = mainFrame.getScrollableFavoriteSongsPanel();
        mainFrame.setMainPanel(favList);
    }

    public void setPlayListsListToMain() {
        JScrollPane playlists = mainFrame.getScrollablePlayListsPanel();
        mainFrame.setMainPanel(playlists);
    }

    public void setSongsListToMain() {
        JScrollPane songs = mainFrame.getScrollableSongsPanel();
        mainFrame.setMainPanel(songs);
    }

    public void openAddSongsDialog() {
        System.out.println("Add song method called ...");
//        LookAndFeel currLF = UIManager.getLookAndFeel();
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println("System Look And Feel has problem...");
//        }
        JFileChooser fileChooser = new JFileChooser();

//        try {
//            UIManager.setLookAndFeel(currLF);
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
//        fileChooser.setDialogTitle("Choose a mp3 songs or its parent directories ...");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);
//        fileChooser.setApproveButtonText("Add Songs");
        fileChooser.repaint();
        int dialog = fileChooser.showOpenDialog(mainFrame);

        if (dialog == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            Arrays.asList(files).forEach(file -> {
                        if (file.isDirectory()) {
                            addSongFilesFromDirectory(file);
                        } else {
                            addSongFile(file);
                        }
                    }
            );
        }
    }

    private void addSongFilesFromDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            Arrays.asList(files).forEach(file -> {
                if (file.isDirectory()) {
                    addSongFilesFromDirectory(file);
                } else {
                    addSongFile(file);
                }
            });
        }
    }

    private void addSongFile(File file) {
        if (file.getName().endsWith(".mp3") || file.getName().endsWith(".MP3")) {
            try {
                Song newSong = new Song(file);
                songs.add(newSong);

                //create and add song card to Songs
                mainFrame.getSongsPanel().addSongCard(newSong.getTitle(), newSong.getAlbum(), newSong.getArtist(), newSong.getAlbumImageAsSize(48, 48), newSong.getSongLengthMilliseconds(), false);

                //todo add to or create cards artist and album !
            } catch (Exception e) {
                //todo handle !! by showing a error dialog? I don't know !
            }
        }
    }

}