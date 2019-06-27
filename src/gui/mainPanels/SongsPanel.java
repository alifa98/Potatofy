package gui.mainPanels;

import gui.mainPanels.cards.SongCard;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;


public class SongsPanel extends JPanel {
    private ArrayList<SongCard> songCards;

    public SongsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        songCards = new ArrayList<>();
    }

    public void addSongCard(String musicName, String albumName, File coverFile, long musicLength, boolean isFavorite) {
        SongCard newCard = new SongCard(musicName, albumName, coverFile, musicLength, isFavorite);
        add(newCard);
        songCards.add(newCard);
        validate();
    }

    public void setCardState(String songName, boolean setToPlaying) {
    //todo when a song go to play or pause state this method must be called to update little state icon next to the cover
        for (SongCard card : songCards) {
            if (card.getMusicName().equals(songName)) {
                card.setStateIcon(setToPlaying);
                break;
            }
        }
    }
}
