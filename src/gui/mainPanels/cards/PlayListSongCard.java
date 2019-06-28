package gui.mainPanels.cards;

import com.manager.Manager;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.utils.MaterialColors;
import media.music.PlayList;
import media.music.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayListSongCard extends SongCard {
    private JLabel sweepUpLabel;
    private JLabel sweepDownLabel;
    private PlayList playlist;
    public PlayListSongCard(Song song, PlayList playlist) {
        super(song);
        this.playlist = playlist;

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        sweepUpLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_UP, 40, MaterialColors.BLACK));
        add(sweepUpLabel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        sweepDownLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_DOWN, 40, MaterialColors.BLACK));
        add(sweepDownLabel, gridBagConstraints);
    }

    @Override
    public void setEventListeners(Manager manager) {
        super.setEventListeners(manager);

        sweepUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo call sweep up method in manager( passing song and playlist ) and re create playlist panel and show;
            }
        });
        sweepUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo call sweep up method in manager( passing song and playlist ) and re create playlist panel and show;
            }
        });
    }
}
