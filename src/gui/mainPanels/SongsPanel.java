package gui.mainPanels;

import gui.mainPanels.cards.SongCard;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;


public class SongsPanel extends JPanel {
    ArrayList<SongCard> songCards;

    public SongsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        songCards = new ArrayList<>();
    }

    public void addSongCard(String musicName, String albumName, File coverFile, long musicLength, boolean isFavorite) {
        SongCard newCard = new SongCard(musicName, albumName, coverFile, musicLength, isFavorite);
        add(newCard);
        songCards.add(newCard);
    }

    public void setCardState(String songName, boolean setToplaying) {

        for (SongCard card : songCards) {
            if (card.getMusicName().equals(songName)) {
                card.setStateIcon(setToplaying);
                break;
            }
        }
    }
}
