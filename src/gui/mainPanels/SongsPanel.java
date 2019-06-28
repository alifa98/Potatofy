package gui.mainPanels;

import gui.mainPanels.cards.SongCard;

import javax.swing.*;
import java.util.ArrayList;

//todo IMPORTANT ::::==> add scroll bar to ListPanels !!! shuuld be handled in mainframe ?! tomarrow i handle it ! Good night 01:22 AM Friday
public class SongsPanel extends JPanel {
    private ArrayList<SongCard> songCards;

    public SongsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        songCards = new ArrayList<>();
    }

    public void addSongCard(String musicName, String albumName, String artistName, ImageIcon coverFile, long musicLength, boolean isFavorite) {
        SongCard newCard = new SongCard(musicName, albumName, artistName, coverFile, musicLength, isFavorite);
        add(newCard);
        songCards.add(newCard);
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
