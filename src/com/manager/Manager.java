package com.manager;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.MainFrame;
import gui.bottomPanels.BottomPanel;
import media.music.Song;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * This is Manager Prototype
 * Most of it is HardCoded for now
 *
 */
public class Manager {
    private static final String songURL ="D:\\Downloads\\Compressed\\2000 The Marshall Mathers LP\\2000 The Marshall Mathers LP\\03 Stan (Featuring Dido).mp3";
    public Manager()  {
        MainFrame mainFrame=new MainFrame("potato");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        BottomPanel bottomPanel=mainFrame.getBottomPanel();
        Song song= null;
        try {
            song = new Song(new File(songURL));
        } catch (InvalidDataException | IOException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        try {
            bottomPanel.setSong(song);
        } catch (InvalidDataException | IOException | UnsupportedTagException e) {
            e.printStackTrace();
        }

        bottomPanel.setCurrentTime(240000);


    }
}
