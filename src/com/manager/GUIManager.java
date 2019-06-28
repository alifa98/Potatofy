package com.manager;

import gui.CustomScrollBarUI;
import gui.MainFrame;
import gui.mainPanels.MyPanel;
import media.music.Song;

import javax.swing.*;
import java.util.ArrayList;

public class GUIManager {


    public static void showAllSongs(MainFrame frame, ArrayList<Song> songs) {
        MyPanel panel = new MyPanel(false);
        JScrollPane scrollablePanel = new JScrollPane(panel);
        scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        for (Song s : songs){
            panel.addCard(s);
        }

        frame.setMainPanel(scrollablePanel);
    }
}
