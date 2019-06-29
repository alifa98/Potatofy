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
import java.util.ArrayList;

public class EditPlayListCard extends JPanel {
    protected Song song;
    ArrayList<Song> songs;
    private Border padding = new EmptyBorder(new Insets(5, 5, 5, 20)); //padding for elements
    private ImageLabel coverPicture;
    private JLabel musicNameLabel;
    private JLabel albumNameLabel;
    private JLabel checkIconLabel;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private EditPlayListCard thisCardObject = this;
    private boolean isInPlaylist;
    public EditPlayListCard(Song song, ArrayList<Song> songs, boolean isInPlaylist) {
        this.song = song;
        this.songs = songs;
        this.isInPlaylist = isInPlaylist;

        setLayout(new GridBagLayout());
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;

        addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));

        //Song Cover
        coverPicture = new ImageLabel(song.getAlbumImageAsSize(48, 48));
        coverPicture.setBorder(padding);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        add(coverPicture, gridBagConstraints);

        // music name label
        musicNameLabel = new JLabel(song.getTitle());
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
        albumNameLabel.setFont(MaterialFonts.ITALIC);
        albumNameLabel.setBorder(padding);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        add(albumNameLabel, gridBagConstraints);

        // song length Label
        JLabel songLength = new JLabel(TimeData.reformatMilisecForSong(song.getSongLengthMilliseconds()));
        songLength.setFont(MaterialFonts.LIGHT);
        songLength.setBorder(padding);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 0;
        add(songLength, gridBagConstraints);

        //add music check Icon
        Icon checkIcon;
        if (isInPlaylist) {
            checkIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DONE, 40, CustomColors.GREEN);
        } else {
            checkIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DONE, 40, CustomColors.GRAY);
        }
        checkIconLabel = new JLabel(checkIcon);
        checkIconLabel.setBorder(padding);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        add(checkIconLabel, gridBagConstraints);

        setMaximumSize(new Dimension(2500, (int) getPreferredSize().getHeight()));
    }

    public Song getSong() {
        return song;
    }

    public void setEventListeners(Manager manager) {
        System.out.println("mouselistenr added");
        thisCardObject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisCardObject.toggler(manager);
            }
        });
    }

    //add or remove from play list
    private void toggler(Manager manager){
        if(isInPlaylist){
            checkIconLabel.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DONE, 40, CustomColors.GRAY));
            isInPlaylist = false;
            songs.remove(song);
        }else {
            checkIconLabel.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DONE, 40, CustomColors.GREEN));
            isInPlaylist = true;
            songs.add(song);
        }
    }
}
