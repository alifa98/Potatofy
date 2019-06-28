package com.manager;
import com.manager.audio.Audio;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.MainFrame;
import gui.bottomPanels.BottomPanel;
import gui.bottomPanels.ControlButtons;
import javazoom.jl.decoder.JavaLayerException;
import media.music.Song;
import mediaplayer.advancedPlayerWrapper.AdvancedPlayerWrapper;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    private static final String songURL = "D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\08 The Real Slim Shady.mp3";
    private static final String secondSongUrl="D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\\\07 The Way I Am.mp3";
    private MainFrame mainFrame;
    private BottomPanel bottomPanel;
    private AdvancedPlayerWrapper songPlayer;
    private Song activeSong;
    private boolean isActivatedByInterval = false;
    private boolean songSliderMouseDown = false;
    private boolean isPlayingSong = false;
    private ArrayList<Song> songs;
    private ArrayList<Song> playingQueue;
    private static volatile boolean thereIsAFinishedSong =false;
    private boolean shuffleIsActive;
    private boolean repeatIsActive;
    private int activeSongIndex=0;



    public Manager() {
        mainFrame = new MainFrame("Potatofy");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        bottomPanel = mainFrame.getBottomPanel();
        Timer timer = new Timer(500, new TimerEventListener(this));
        timer.setRepeats(true);
        timer.start();

        songs = new ArrayList<>();
        playingQueue=new ArrayList<>();

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

        if( Manager.thereIsAFinishedSong){
            checkAfterFinishedSong();
            Manager.thereIsAFinishedSong =false;
        }

        isActivatedByInterval = false;
    }

    private void checkAfterFinishedSong() {
        //todo it goes to nex currently
        if(repeatIsActive){
            playActiveSong();
        }
        else{
            incrementActiveSongIndex();
        }

    }

    private void incrementActiveSongIndex() {
        if(playingQueue.size()<=activeSongIndex){
            activeSongIndex=0;
        }
        else{
            activeSongIndex++;
        }
    }

    private void decrementActiveSongIndex(){
        if(playingQueue.size()<=0){
            activeSongIndex=0;
        }else{
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

            songPlayer = new AdvancedPlayerWrapper(activeSong.getSource());

        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
        isPlayingSong = true;
        try {
            songPlayer.resume();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
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
        float masterVolume = (float) sliderValue / BottomPanel.MAX_SLIDER_VALUE;
        Audio.setMasterOutputVolume(masterVolume);
    }

    public void initialSetting() {
        bottomPanel.setVolumeSliderValue((int) (Audio.getMasterOutputVolume() * BottomPanel.MAX_SLIDER_VALUE));

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
            updateQueue();
        }
    }

    private void addSongFile(File file) {
        if (file.getName().endsWith(".mp3") || file.getName().endsWith(".MP3")) {
            try {
                Song newSong = new Song(file);
                songs.add(newSong);

                //create and add song card to Songs
                //mainFrame.getSongsPanel().addSongCard(newSong.getTitle(), newSong.getAlbum(), newSong.getArtist(), newSong.getAlbumImageAsSize(48, 48), newSong.getSongLengthMilliseconds(), false);
                System.out.println("added an item");

                //todo add to or create cards artist and album !
            } catch (Exception e) {
                //todo handle !! by showing a error dialog? I don't know !
            }

        }
    }

    public static synchronized void setFinishedSong(boolean thereIsAFinishedSong) {
        Manager.thereIsAFinishedSong =thereIsAFinishedSong;
    }


    public void shuffleClickEvent(boolean shuffleIsActive){
        this.shuffleIsActive=shuffleIsActive;
    }

    public void previousClickEvent(){
        if(songPlayer.getCurrentFrame()>=100){
            try {
                if(songPlayer!=null){
                    songPlayer.goToFrame(0);
                }
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }else{
            decrementActiveSongIndex();
            playActiveSong();
        }

    }
    public void playPauseClickEvent(ControlButtons newState){
        if(newState.isPlaying()){
            pausePlayingSong(newState);
        }

        else {

            playActiveSongEventCall(newState);
        }
    }
    public void nextClickEvent(){
        incrementActiveSongIndex();
        playActiveSong();
        System.out.println("here");

    }
    public void repeatClickEvent(boolean repeatActive){
        this.repeatIsActive=repeatActive;
    }

    private void pausePlayingSong(ControlButtons newState){
        if(songPlayer!=null){
            songPlayer.pause();
            isPlayingSong=false;
            newState.setPlaying(false);
        }


    }
    private void playActiveSongEventCall(ControlButtons newState){
        if(songPlayer!=null){
            try {

                songPlayer.resume();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
            isPlayingSong=true;
            newState.setPlaying(true);
        }
        else {
            playActiveSong();
        }

    }

    private void updateQueue(){
        playingQueue=new ArrayList<>(songs);
        if(!playingQueue.isEmpty())
            activeSong=playingQueue.get(activeSongIndex);
    }

    private void playActiveSong(){
        if(playingQueue==null)return;
        if(playingQueue.size()==0)return;

        activeSong=playingQueue.get(activeSongIndex);
        if(!activeSong.isValid()) {
            int i;
            for(i = 0; i<playingQueue.size(); i++){
                incrementActiveSongIndex();
                activeSong=playingQueue.get(activeSongIndex);
                if(activeSong.isValid())return;
            }
            if(i==playingQueue.size())return;
        }

        System.out.println("playActiveSong");
        try {
            bottomPanel.setSong(activeSong);
        } catch (InvalidDataException | IOException | UnsupportedTagException e) {
            e.printStackTrace();
        }


            if(songPlayer==null) {
                try {
                    songPlayer=new AdvancedPlayerWrapper(activeSong.getSource());
                } catch (FileNotFoundException | JavaLayerException e) {
                    e.printStackTrace();
                }
            }



            else{
                if(songPlayer.isPlaying()){
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


    }


}