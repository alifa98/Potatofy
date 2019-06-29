package com.manager;

import com.manager.audio.Audio;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.MainFrame;
import gui.bottomPanels.BottomPanel;
import gui.bottomPanels.ControlButtons;
import javazoom.jl.decoder.JavaLayerException;
import media.music.Album;
import media.music.PlayList;
import media.music.Song;
import mediaplayer.advancedPlayerWrapper.AdvancedPlayerWrapper;
import volumeController.Controller;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    private static volatile boolean thereIsAFinishedSong = false;
    private MainFrame mainFrame;
    private BottomPanel bottomPanel;
    private AdvancedPlayerWrapper songPlayer;
    private Song activeSong;
    private boolean isActivatedByInterval = false;
    private boolean songSliderMouseDown = false;
    private boolean isPlayingSong = false;
    private ArrayList<Song> songs;
    private ArrayList<Song> playingQueue;
    private ArrayList<Album> albums;
    private ArrayList<PlayList> playlists;
    private boolean shuffleIsActive;
    private boolean repeatIsActive;
    private int activeSongIndex = 0;

    public Manager() {
        mainFrame = new MainFrame("Potatofy", this);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        bottomPanel = mainFrame.getBottomPanel();
        Timer timer = new Timer(500, new TimerEventListener(this));
        timer.setRepeats(true);
        timer.start();

        songs = new ArrayList<>();
        albums = new ArrayList<>();
        playlists = new ArrayList<>();
        playingQueue = new ArrayList<>();

    }

    public static synchronized void setFinishedSong(boolean thereIsAFinishedSong) {
        Manager.thereIsAFinishedSong = thereIsAFinishedSong;
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

        if (Manager.thereIsAFinishedSong) {
            checkAfterFinishedSong();
            Manager.thereIsAFinishedSong = false;
        }

        isActivatedByInterval = false;
    }

    private void checkAfterFinishedSong() {
        //todo it goes to nex currently
        if (repeatIsActive) {
            playActiveSong();
        } else {
            incrementActiveSongIndex();
        }

    }

    private void incrementActiveSongIndex() {
        if (playingQueue.size() <= activeSongIndex) {
            activeSongIndex = 0;
        } else {
            activeSongIndex++;
        }
    }

    private void decrementActiveSongIndex() {
        if (activeSongIndex <= 0) {
            activeSongIndex = 0;
        } else {
            activeSongIndex--;
        }
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
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }

        if (isPlayingSong && !songPlayer.isPlaying()) {
            try {
                songPlayer.resume();
            } catch (JavaLayerException | IOException e) {
                e.printStackTrace();
            }
        }
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
        Controller.setSystemVolume(sliderValue);
    }

    public void initialSetting() {
        bottomPanel.setVolumeSliderValue(BottomPanel.MAX_SLIDER_VALUE);

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
        GUIManager.showAllSongs(mainFrame, songs, this);
        updateQueue();
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
                boolean songIsExist = false;
                for (Song s : songs) { //check and prevent duplicate adding
                    if (newSong.getSource().getAbsolutePath().equals(s.getSource().getAbsolutePath())) {
                        songIsExist = true;
                        break;
                    }
                }
                if (!songIsExist) {
                    addSongToSongsArrayList(newSong);
                }

            } catch (Exception e) {
                //todo handle !! by showing a error dialog? I don't know !
            }

        }
    }

    private void addSongToSongsArrayList(Song song) {
        //todo for adding a song in array list please use this method.
        songs.add(song);
        detectAndAddSongToAlbums(song);
    }

    public void shuffleClickEvent(boolean shuffleIsActive) {
        this.shuffleIsActive = shuffleIsActive;
    }

    public void previousClickEvent() {
        if (songPlayer.getCurrentFrame() >= 100) {
            try {
                if (songPlayer != null) {
                    songPlayer.goToFrame(0);
                }
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        } else {
            decrementActiveSongIndex();
            playActiveSong();
        }

    }

    public void playPauseClickEvent(ControlButtons newState) {
        if (newState.isPlaying()) {
            pausePlayingSong(newState);
        } else {

            playActiveSongEventCall(newState);
        }
    }

    public void nextClickEvent() {
        incrementActiveSongIndex();
        playActiveSong();
        System.out.println("here");

    }

    public void repeatClickEvent(boolean repeatActive) {
        this.repeatIsActive = repeatActive;
    }

    private void pausePlayingSong(ControlButtons newState) {
        if (songPlayer != null) {
            songPlayer.pause();
            isPlayingSong = false;
            newState.setPlaying(false);
        }


    }

    private void playActiveSongEventCall(ControlButtons newState) {
        if (songPlayer != null) {
            try {

                songPlayer.resume();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
            isPlayingSong = true;
            newState.setPlaying(true);
        } else {
            if (playActiveSong()) {
                isPlayingSong = true;
                newState.setPlaying(true);
            }

        }

    }

    private void updateQueue() {
        playingQueue = new ArrayList<>(songs);
        if (!playingQueue.isEmpty())
            activeSong = playingQueue.get(activeSongIndex);
    }

    private boolean playActiveSong() {
        if (playingQueue == null) return false;
        if (playingQueue.size() == 0) return false;

        activeSong = playingQueue.get(activeSongIndex);

        if (!activeSong.isValid()) {
            int i;
            for (i = 0; i < playingQueue.size(); i++) {
                incrementActiveSongIndex();
                activeSong = playingQueue.get(activeSongIndex);
                if (activeSong.isValid()) break;
            }
            if (i == playingQueue.size()) return false;
        }

        //System.out.println("playActiveSong");
        try {
            bottomPanel.setSong(activeSong);
        } catch (InvalidDataException | IOException | UnsupportedTagException e) {
            e.printStackTrace();
        }


        if (songPlayer == null) {
            try {
                songPlayer = new AdvancedPlayerWrapper(activeSong.getSource());
            } catch (FileNotFoundException | JavaLayerException e) {
                e.printStackTrace();
            }
        } else {
            if (songPlayer.isPlaying()) {
                songPlayer.pause();
            }
            try {
                songPlayer.setSongFile(activeSong.getSource());
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }

        try {
            songPlayer.goToFrame(0);
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }


        try {
            songPlayer.resume();
        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }

        return true;
    }

    //todo call this method when you add
    private void detectAndAddSongToAlbums(Song song) {
        boolean albumFound = false;
        for (Album a : albums) {
            if (a.getAlbumName().equals(song.getAlbum())) {
                a.addSong(song);
                albumFound = true;
                break;
            }
        }
        if (!albumFound) {
            Album newAlbum = new Album(song.getAlbum());
            newAlbum.addSong(song);
            albums.add(newAlbum);
        }
    }

    // listeners calls this methods please don't touch them.
    public void showAllSongs() {
        GUIManager.showAllSongs(mainFrame, songs, this);
    }

    public void showFavoriteSongs() {
        GUIManager.showFavoriteSongs(mainFrame, songs, this);
    }

    public void showAlbums() {
        GUIManager.showAlbums(mainFrame, albums, this);
    }

    public void showTitledPanel(String title, ArrayList<Song> songs, boolean isPlalistt) {
        GUIManager.showTitledPanel(mainFrame, title, songs, isPlalistt, this);
    }
}
