package gui.mainPanels.cards;

import com.TimeData;
import com.manager.Manager;
import gui.CustomColors;
import gui.ImageLabel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFonts;
import media.music.Song;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SongCard extends JPanel {
    private Border padding = new EmptyBorder(new Insets(5, 5, 5, 20)); //padding for elements
    protected Song song;
    private ImageLabel coverPicture;
    private JLabel musicNameLabel;
    private JLabel albumNameLabel;
    private JLabel favoriteIconLabel;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private SongCard thisCardObject = this;

    public SongCard(Song song) {
        this.song = song;
        setLayout(new GridBagLayout());
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));

        //Song Cover
        coverPicture = new ImageLabel(song.getAlbumImageAsSize(48, 48));
        coverPicture.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        coverPicture.setBorder(padding);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        add(coverPicture, gridBagConstraints);

        // music name label
        musicNameLabel = new JLabel(song.getTitle());
        musicNameLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        musicNameLabel.setFont(MaterialFonts.MEDIUM);
        musicNameLabel.setBorder(padding);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        add(musicNameLabel, gridBagConstraints);

        // album name and artist Label
        albumNameLabel = new JLabel(song.getAlbum() + " - " + song.getArtist());
        albumNameLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        albumNameLabel.setFont(MaterialFonts.ITALIC);
        albumNameLabel.setBorder(padding);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        add(albumNameLabel, gridBagConstraints);

        // song length Label
        JLabel songLength = new JLabel(TimeData.reformatMilisecForSong(song.getSongLengthMilliseconds()));
        songLength.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        songLength.setFont(MaterialFonts.LIGHT);
        songLength.setBorder(padding);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0;
        add(songLength, gridBagConstraints);

        //add music favorite icon
        Icon favoriteIcon;
        if (song.isFavorite()) {
            favoriteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE, 20, CustomColors.PRIMARY);
        } else {
            favoriteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE_BORDER, 20, MaterialColors.BLACK);
        }
        favoriteIconLabel = new JLabel(favoriteIcon);
        favoriteIconLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        favoriteIconLabel.setBorder(padding);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        add(favoriteIconLabel, gridBagConstraints);

        setMaximumSize(new Dimension(2500, (int) getPreferredSize().getHeight()));
    }

    public Song getSong() {
        return song;
    }

    public void setEventListeners(Manager manager) {
        musicNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //  todo call a function in manager to play this song
            }
        });

        albumNameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //  todo call a function in manager to play this song
            }
        });

        coverPicture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //  todo call a function in manager to play this song
            }
        });

        favoriteIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (song.isFavorite()) {
                    System.out.println("I Love it"); //for debug
                    song.setFavorite(false);
                    favoriteIconLabel.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE_BORDER, 20, MaterialColors.BLACK));
                } else {
                    System.out.println("I don't Love it"); //for debug
                    song.setFavorite(true);
                    favoriteIconLabel.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE, 20, CustomColors.PRIMARY));
                }
            }
        });
    }
}
