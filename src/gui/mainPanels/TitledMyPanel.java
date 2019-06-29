package gui.mainPanels;

import com.manager.Manager;
import gui.ImageLabel;
import gui.mainPanels.cards.PlayListSongCard;
import gui.mainPanels.cards.SongCard;
import mdlaf.utils.MaterialFonts;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TitledMyPanel extends MyPanel {
    String title;
    boolean isPlaylist;
    public TitledMyPanel(String title, ArrayList<Song> list, boolean isPlaylist, Manager manager) { //passed for mouse event listener
        super(isPlaylist, manager);
        this.isPlaylist =isPlaylist;
        this.title = title;
        JPanel titlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        ImageIcon cover = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src\\gui\\icons\\png\\64\\playlists.png").getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        if (list.size() > 0)
            cover = list.get(0).getAlbumImageAsSize(100, 100);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 5;
        ImageLabel coverLabel = new ImageLabel(cover);
        coverLabel.setBorder(new EmptyBorder(new Insets(10, 5, 10, 10)));
        titlePanel.add(coverLabel, gridBagConstraints);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(MaterialFonts.BLACK.deriveFont(30f));
        titleLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        titlePanel.add(titleLabel, gridBagConstraints);

        JLabel numberOfSongs = new JLabel(list.size() + " Songs");
        numberOfSongs.setFont(MaterialFonts.ITALIC);
        numberOfSongs.setBorder(new EmptyBorder(new Insets(0, 10, 10, 10)));
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 2;
        titlePanel.add(numberOfSongs, gridBagConstraints);
        add(titlePanel);
        titlePanel.setMaximumSize(new Dimension(2500, (int) getPreferredSize().getHeight()));
    }
    public void addCard(Song song, ArrayList<Song> list) {  //creates card for Playlist Songs and album songs
        if (isPlaylist) {
            PlayListSongCard newcard = new PlayListSongCard(song, list);
            newcard.setEventListeners(manager,title);
            add(newcard);
        }else {
            SongCard newcard = new SongCard(song);
            add(newcard);
            newcard.setEventListeners(manager);
        }
    }
}
