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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class PlayListCard extends JPanel {
    private String playListName;
    private int songNumber;
    private  JLabel songNumberLabel;
    private JLabel stateIcon;

    //padding for elements
    Border padding = new EmptyBorder(new Insets(5,5,5,20));

    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public PlayListCard(String playListName, File coverFile, int songNumber) {

        //setting need arguments
        this.playListName = playListName;
        this.songNumber = songNumber;

        //setting Layout
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        setLayout(gridBagLayout);

        //Mouse hover on card -> change bg color
        addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));

        //Status icon
        stateIcon = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAUSE, 25, CustomColors.DARK_GRAY));
        stateIcon.setBorder(padding);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagLayout.setConstraints(stateIcon, gridBagConstraints);
        add(stateIcon);

        //playlist Cover
        ImageIcon scaledCover = new ImageIcon(Toolkit.getDefaultToolkit().getImage(coverFile.getPath()).getScaledInstance(48, 48, Image.SCALE_DEFAULT));
        ImageLabel coverPicture = new ImageLabel(scaledCover);
        coverPicture.setBorder(padding);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagLayout.setConstraints(coverPicture, gridBagConstraints);
        add(coverPicture);

        // playlist name label
        JLabel artistNameLabel = new JLabel(playListName);
        artistNameLabel.setFont(MaterialFonts.BLACK);
        artistNameLabel.setBorder(padding);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.weightx = 1;
        gridBagLayout.setConstraints(artistNameLabel, gridBagConstraints);
        add(artistNameLabel);

        // number of songs in playlist
        songNumberLabel = new JLabel("This play list has "+songNumber+ " Songs.");
        songNumberLabel.setFont(MaterialFonts.BOLD_ITALIC);
        songNumberLabel.setBorder(padding);
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(songNumberLabel, gridBagConstraints);
        add(songNumberLabel);

        //BoxLayout is one of the few layout managers that respects the minimum and maximum sizes of a component.
        //so following code prevent a panel from stretching
        setMaximumSize(new Dimension(1900, (int) getPreferredSize().getHeight()));
    }

    public int getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(int songNumber) {
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;

        this.songNumber = songNumber;
        remove(songNumberLabel);
        songNumberLabel = new JLabel("This play list has "+songNumber+ " Songs.");
        songNumberLabel.setFont(MaterialFonts.BOLD_ITALIC);
        songNumberLabel.setBorder(padding);
        gridBagLayout.setConstraints(songNumberLabel, gridBagConstraints);
        add(songNumberLabel);
    }

    public String getPlayListName() {
        return playListName;
    }
    public void setStateIcon(boolean setToPlay) {
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        //remove previous icon
        remove(stateIcon);

        //create new icon
        if (setToPlay) {
            stateIcon = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_ARROW, 25, CustomColors.PRIMARY));

        } else {
            stateIcon = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAUSE, 25, CustomColors.DARK_GRAY));
        }
        stateIcon.setBorder(padding);
        gridBagLayout.setConstraints(stateIcon, gridBagConstraints);
        add(stateIcon);

    }

    //todo add  onclick listener to open playlist songs panel
}
