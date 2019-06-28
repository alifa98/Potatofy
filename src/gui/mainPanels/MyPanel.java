package gui.mainPanels;

import gui.mainPanels.cards.ListCard;
import gui.mainPanels.cards.PlayListSongCard;
import gui.mainPanels.cards.SongCard;
import media.music.Album;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    private boolean isPlayList;

    public MyPanel(boolean isPlayList) {
        this.isPlayList = isPlayList;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addCard(PlayList playList) { //for showing playlist list
        ListCard newCard = new ListCard(playList.getPlayListName(), playList.getSongsArrayList(), true);
        add(newCard);
    }

    public void addCard(Album album) { //for showing album list
        ListCard newCard = new ListCard(album.getAlbumName(), album.getSongsArrayList(), false);
        add(newCard);
    }

    public void addCard(Song song, PlayList playList) {  //creates card for Playlist Songs
        if (isPlayList) {
            PlayListSongCard newcard = new PlayListSongCard(song, playList);
            add(newcard);
        }
    }
    public void addCard(Song song) {  //creates card for normal songs and album songs
            SongCard newcard = new SongCard(song);
            add(newcard);
    }

}
