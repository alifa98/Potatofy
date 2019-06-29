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
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EditListCard extends ListCard { // for editing play list songs - name - and deleted
    JLabel renameLabel, editOriderLabel, deleteLabel;
    PlayList playList;

    public EditListCard(PlayList p) {
        super(p.getPlayListName(), p.getSongsArrayList(), true);
        playList =p;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        editOriderLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAYLIST_ADD, 20, MaterialColors.BLACK));
        editOriderLabel.setBorder(new EmptyBorder(new Insets(10, 10, 5, 10)));
        editOriderLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        add(editOriderLabel, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        renameLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MODE_EDIT, 20, MaterialColors.BLACK));
        renameLabel.setBorder(new EmptyBorder(new Insets(10, 10, 5, 10)));
        renameLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        add(renameLabel, gbc);


        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        deleteLabel = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DELETE_FOREVER, 20, MaterialColors.BLACK));
        deleteLabel.setBorder(new EmptyBorder(new Insets(10, 10, 5, 10)));
        deleteLabel.addMouseListener(MaterialUIMovement.getMovement(this, CustomColors.LIGHTER_GRAY));
        add(deleteLabel, gbc);
    }

    @Override
    public void setEventListeners(Manager manager) {
        deleteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.deletePlayList(playList);
            }
        });
        renameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.renamePlayList(playList);
            }
        });
        editOriderLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.showEditPlayListPanel(playList.getPlayListName(),playList.getSongsArrayList());
            }
        });

    }
}
