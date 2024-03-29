package com.manager;

import gui.CustomScrollBarUI;
import gui.MainFrame;
import gui.mainPanels.MyPanel;
import gui.mainPanels.TitledMyPanel;
import media.music.Album;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import java.util.ArrayList;

public class GUIManager {

    private static JScrollPane getCustomJScollPaneJ(JPanel panel) { //this method add scroll bar to panels
        JScrollPane scrollablePanel = new JScrollPane(panel);
        scrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollablePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        return scrollablePanel;
    }

    public static void showAllSongs(MainFrame frame, ArrayList<Song> songs, Manager manager) { //pass manager instance for event listeners in cards.
        MyPanel panel = new MyPanel(false, manager);

        for (Song s : songs) {
            panel.addCard(s);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }

    public static void showFavoriteSongs(MainFrame frame, ArrayList<Song> songs, Manager manager) {
        MyPanel panel = new MyPanel(false, manager);
        for (Song s : songs) {
            if (s.isFavorite())
                panel.addCard(s);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }

    public static void showAlbums(MainFrame frame, ArrayList<Album> albums, Manager manager) {
        MyPanel panel = new MyPanel(false, manager);
        for (Album a : albums) {
            panel.addCard(a);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }

    public static void showTitledPanel(MainFrame frame, String title, ArrayList<Song> songs, boolean isPlaylist, Manager manager) {
        TitledMyPanel panel = new TitledMyPanel(title, songs, isPlaylist, manager);

        for (Song s : songs) {
            panel.addCard(s, songs);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();

    }

    public static String getANamyByDialog() {
        String input = JOptionPane.showInputDialog("Enter Playlist name");
        if (input == null || input.equals("")) {
            input = "My List";
        }
        return input;
    }

    public static void showEditPlayList(MainFrame frame, String title, ArrayList<Song> allSongs, ArrayList<Song> playListSongs, Manager manager) {
        TitledMyPanel panel = new TitledMyPanel(title, playListSongs, false , manager);

        for (Song s : allSongs) {
            panel.editPlaylistCard(s, playListSongs);
        }

        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }
    public static void showPlayLists(MainFrame frame, ArrayList<PlayList> playLists, Manager manager) {
        MyPanel panel = new MyPanel(false, manager);
        for (PlayList p : playLists) {
            panel.addCard(p);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }
    public static void showEditPlayLists(MainFrame frame, ArrayList<PlayList> playLists, Manager manager) {
        MyPanel panel = new MyPanel(false, manager);
        for (PlayList p : playLists) {
           panel.editPlaylistsCard(p);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }
    public static void showSharedSongs(MainFrame frame, ArrayList<Song> songs, Manager manager) {
        MyPanel panel = new MyPanel(false, manager);
        for (Song s : songs) {
            if (s.isShared())
                panel.addCard(s);
        }
        frame.setMainPanel(getCustomJScollPaneJ(panel));
        frame.validate();
    }
}
