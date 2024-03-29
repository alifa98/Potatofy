package gui.mainPanels;

import com.manager.Manager;
import gui.mainPanels.cards.*;
import media.music.Album;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    private boolean isPlayList;
    protected Manager manager;

    public MyPanel(boolean isPlayList, Manager manager) { //manager passed to use in mouse listeners
        this.isPlayList = isPlayList;
        this.manager = manager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addCard(PlayList playList) { //for showing playlist list
        ListCard newCard = new ListCard(playList.getPlayListName(), playList.getSongsArrayList(), true);
        add(newCard);
        newCard.setEventListeners(manager);
    }

    public void addCard(Album album) { //for showing album list
        ListCard newCard = new ListCard(album.getAlbumName(), album.getSongsArrayList(), false);
        add(newCard);
        newCard.setEventListeners(manager);
    }

    public void addCard(Song song) {  //creates card for normal songs and album songs
        SongCard newcard = new SongCard(song);
        add(newcard);
       newcard.setEventListeners(manager);
    }
    public void editPlaylistCard(Song song, ArrayList<Song> playlistSongArray){
        EditPlayListCard newcard = new EditPlayListCard(song, playlistSongArray, playlistSongArray.contains(song));
        add(newcard);
        newcard.setEventListeners(manager);
    }
    public void editPlaylistsCard(PlayList playList){
        EditListCard newcard = new EditListCard(playList);
        add(newcard);
        newcard.setEventListeners(manager);
    }

}
