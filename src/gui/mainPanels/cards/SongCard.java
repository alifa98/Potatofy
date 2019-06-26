package gui.mainPanels.cards;

import com.TimeData;
import gui.CustomColors;
import gui.ImageLabel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFonts;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SongCard extends JPanel {
    private String musicName;
    private JLabel stateIcon;
    private boolean favorite = false;
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public SongCard(String musicName, String albumName, File coverFile, long musicLength, boolean isFavorite) {
        //setting need arguments
        this.musicName = musicName;
        this.favorite = isFavorite;

        //setting Layout
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        setLayout(gridBagLayout);

        //Mouse hover on card -> change bg color
        addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));

        //Status icon
        stateIcon = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAUSE, 20, CustomColors.DARK_GRAY));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(stateIcon, gridBagConstraints);
        add(stateIcon);
        // todo add method to change state

        //Song Cover
        ImageIcon scaledCover = new ImageIcon(Toolkit.getDefaultToolkit().getImage(coverFile.getPath()).getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        ImageLabel coverPicture = new ImageLabel(scaledCover);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagLayout.setConstraints(coverPicture, gridBagConstraints);
        add(coverPicture);

        // music name label
        JLabel musicNameLabel = new JLabel(musicName);
        musicNameLabel.setFont(MaterialFonts.MEDIUM);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagLayout.setConstraints(musicNameLabel, gridBagConstraints);
        add(musicNameLabel);

        // album name Label
        JLabel albumNameLabel = new JLabel(albumName);
        albumNameLabel.setFont(MaterialFonts.ITALIC);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagLayout.setConstraints(musicNameLabel, gridBagConstraints);
        add(albumNameLabel);

        // song length Label
        JLabel songLength = new JLabel(TimeData.reformatMilisecForSong(musicLength));
        songLength.setFont(MaterialFonts.LIGHT);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagLayout.setConstraints(songLength, gridBagConstraints);
        add(songLength);

        //add music favorite icon
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        Icon favoriteIcon;
        if (isFavorite) {
            favoriteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE, 20, CustomColors.PRIMARY);
        } else {
            favoriteIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE_BORDER, 20, MaterialColors.BLACK);
        }
        JLabel favoriteIconLabel = new JLabel(favoriteIcon);
        add(favoriteIconLabel);

        //add music option icon
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        Icon songOptioneIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MORE_HORIZ, 20, CustomColors.DARK_GRAY);
        JLabel songOptioneLabel = new JLabel(songOptioneIcon);
        add(songOptioneLabel);
    }

    public String getMusicName() {
        return musicName;
    }

    public void setStateIcon(boolean setToPlay) {

        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        //remove previous icon
        remove(stateIcon);

        //create new icon
        if (setToPlay) {
            stateIcon = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_ARROW, 20, CustomColors.PRIMARY));
        } else {
            stateIcon = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAUSE, 20, CustomColors.DARK_GRAY));
        }

        gridBagLayout.setConstraints(stateIcon, gridBagConstraints);
        add(stateIcon);

    }
}
