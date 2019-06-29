package gui.mainPanels.cards;

import com.manager.Manager;
import gui.CustomColors;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PlayListSongCard extends SongCard {
    private JLabel sweepUpLabel;
    private JLabel sweepDownLabel;
    private ArrayList<Song> playlistsongs;
    public PlayListSongCard(Song song, ArrayList<Song> songs) {
        super(song);
        this.playlistsongs = songs;

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        sweepUpLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_UP, 40, MaterialColors.BLACK));
        sweepUpLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        add(sweepUpLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        sweepDownLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_DOWN, 40, MaterialColors.BLACK));
        sweepDownLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        add(sweepDownLabel, gridBagConstraints);
    }

    @Override
    public void setEventListeners(Manager manager) {
        super.setEventListeners(manager);

        sweepUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo call sweep up method in manager( passing song and ArrayList to swap ) and re create playlist panel and show;
            }
        });
        sweepUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo call sweep up method in manager( passing song and ArrayList to swap) and re create playlist panel and show;
            }
        });
    }
}
