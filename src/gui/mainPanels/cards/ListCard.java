package gui.mainPanels.cards;

import com.manager.Manager;
import gui.CustomColors;
import gui.ImageLabel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialFonts;
import media.music.Song;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListCard extends JPanel {
    private Border padding = new EmptyBorder(new Insets(5, 5, 5, 20)); //padding for elements
    private ArrayList<Song> songs;
    private boolean isPlayList;

    public ListCard(String title, ArrayList<Song> songs, boolean isPlaylist) {
        this.songs = songs;
        isPlayList = isPlaylist;

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));


        //List Cover
        ImageLabel coverPicture = new ImageLabel(getCover());
        coverPicture.setBorder(padding);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        add(coverPicture, gridBagConstraints);

        // List Title
        JLabel titleLabel = new JLabel(title);
        Font big = MaterialFonts.BLACK.deriveFont(30f);
        titleLabel.setFont(big);
        titleLabel.setBorder(padding);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        add(titleLabel, gridBagConstraints);

        //List info
        JLabel songsNumber = new JLabel(songs.size() + " Songs");
        songsNumber.setFont(MaterialFonts.LIGHT);
        songsNumber.setBorder(padding);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        add(songsNumber, gridBagConstraints);
    }
    public void setEventListeners(Manager manager) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo call a method in manager that creates a Titled panels (pass title and array list) and Shows this card array List
                // we must send isPlayList boolean in order to method create PlayListSongCard(Swap-able)
            }
        });

    }

    private ImageIcon getCover() {
        if (songs.size() > 0) {
            return songs.get(0).getAlbumImageAsSize(48, 48);
        }
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage("src\\gui\\icons\\png\\64\\playlists.png").getScaledInstance(48, 48, Image.SCALE_DEFAULT));
    }
}
