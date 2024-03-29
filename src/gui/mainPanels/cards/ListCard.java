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
    protected ArrayList<Song> songs;
    private boolean isPlayList;
    protected String title;

    public ListCard(String title, ArrayList<Song> songs, boolean isPlaylist) {
        this.title = title;
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
        Font big = MaterialFonts.BLACK.deriveFont(15f);
        titleLabel.setFont(big);
        titleLabel.setBorder(padding);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 1;
        add(titleLabel, gridBagConstraints);

        //List info
        JLabel songsNumber = new JLabel("Has " + ((songs.size() == 1) ? "One Song" : songs.size() + " Songs"));
        songsNumber.setFont(MaterialFonts.LIGHT);
        songsNumber.setBorder(padding);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0;
        add(songsNumber, gridBagConstraints);
        setMaximumSize(new Dimension(2500, (int) getPreferredSize().getHeight()));
    }

    public void setEventListeners(Manager manager) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.showTitledPanel(title, songs, isPlayList);
            }
        });
    }

    private ImageIcon getCover() {
        if (songs.size() > 0) {
            return songs.get(0).getAlbumImageAsSize(48, 48);
        } else {
            return new ImageIcon(Toolkit.getDefaultToolkit().getImage("src\\gui\\icons\\png\\48\\playlists.png"));
        }
    }
}
