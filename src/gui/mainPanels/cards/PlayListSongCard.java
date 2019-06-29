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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        sweepUpLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_UP, 20, MaterialColors.BLACK));
        sweepUpLabel.setBorder(new EmptyBorder(new Insets(5, 5, 0, 10)));
        sweepUpLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        add(sweepUpLabel, gbc);

        gbc.gridy = 1;
        sweepDownLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.KEYBOARD_ARROW_DOWN, 20, MaterialColors.BLACK));
        sweepDownLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        sweepDownLabel.setBorder(new EmptyBorder(new Insets(0, 5, 2, 10)));
        add(sweepDownLabel, gbc);
    }

    public void setEventListeners(Manager manager, String title) {
        super.setEventListeners(manager);
        sweepUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.swapUpSong(song,playlistsongs,title);
            }
        });
        sweepDownLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //todo call sweep up method in manager( passing song and ArrayList to swap) and re create playlist panel and show;
            }
        });
    }
}
