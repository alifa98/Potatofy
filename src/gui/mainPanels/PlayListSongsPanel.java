package gui.mainPanels;

import gui.mainPanels.cards.SongCard;
import mdlaf.utils.MaterialFonts;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class PlayListSongsPanel extends JPanel {
    private ArrayList<SongCard> songCards;
    private String playListName;
    public PlayListSongsPanel(String playlistName) {
        this.playListName = playlistName;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        songCards = new ArrayList<>();


        //Add title
        JLabel playlistnameLabel = new JLabel(playlistName);
        Font bigFont = MaterialFonts.BOLD.deriveFont(25f);
        playlistnameLabel.setFont(bigFont);
        playlistnameLabel.setMaximumSize(new Dimension(1900,(int) playlistnameLabel.getPreferredSize().getHeight()));
        add(playlistnameLabel);
    }

    public String getPlayListName() {
        return playListName;
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
