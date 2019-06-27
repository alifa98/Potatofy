package gui.mainPanels;

import gui.ImageLabel;
import gui.mainPanels.cards.SongCard;
import mdlaf.utils.MaterialFonts;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class AlbumSongsPanel extends JPanel {
    private ArrayList<SongCard> songCards;
    private String albumName;
    private File albumCover;

    public AlbumSongsPanel(String albumName, File albumCover) {
        this.albumName = albumName;
        this.albumCover = albumCover;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        songCards = new ArrayList<>();


        //Add title
        JPanel topTitlePanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        topTitlePanel.setLayout(gbl);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        ImageIcon scaledCover = new ImageIcon(Toolkit.getDefaultToolkit().getImage(albumCover.getPath()).getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        ImageLabel coverPicture = new ImageLabel(scaledCover);
        gbl.setConstraints(coverPicture, gbc);
        topTitlePanel.add(coverPicture);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        JLabel albumNameLabel = new JLabel(albumName);
        Font bigFont = MaterialFonts.BOLD.deriveFont(25f);
        albumNameLabel.setFont(bigFont);
        topTitlePanel.add(albumNameLabel);

        topTitlePanel.setMaximumSize(new Dimension(2000, (int) topTitlePanel.getPreferredSize().getHeight()));
        add(topTitlePanel);
    }

    public String getAlbumName() {
        return albumName;
    }

    public void addSongCard(String musicName, String albumName, String artistName, ImageIcon coverFile, long musicLength, boolean isFavorite) {
        SongCard newCard = new SongCard(musicName, albumName, artistName, coverFile, musicLength, isFavorite);
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
