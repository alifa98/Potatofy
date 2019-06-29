package gui.topPanels;

import com.manager.Manager;
import gui.CustomColors;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RightButtons extends JPanel {
    private JButton editPlayListsButton, addSongButton, playListsButton, favoritePlayListButton, songsButton, albumsButton, videoButton;

    public RightButtons() {
        //Padding to Button Section
        Insets padding = new Insets(10, 20, 10, 10);
        setBorder(new EmptyBorder(padding));

        //padding prepare for buttons
        Border paddingForElements = new EmptyBorder(new Insets(5, 5, 5, 5));

        // prepare insets for margin of buttons
        Insets marginInsets = new Insets(5, 10, 5, 10); //this and set margin method is useless :D

        //setting Grid Bag Layout to this Panel
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);


        addSongButton = new JButton("Add Song");
        Icon addSongButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ADD_TO_PHOTOS, 25, CustomColors.PRIMARY);
        addSongButton.setIcon(addSongButtonIcon);
        addSongButton.setBorder(paddingForElements);
        addSongButton.setMargin(marginInsets);
        addSongButton.setBackground(MaterialColors.WHITE);
        addSongButton.addMouseListener(MaterialUIMovement.getMovement(addSongButton, CustomColors.LIGHTER_GRAY));
        add(addSongButton);

        songsButton = new JButton("Songs");
        Icon songsButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.LIBRARY_MUSIC, 25, new Color(0, 0, 0));
        songsButton.setIcon(songsButtonIcon);
        songsButton.setBorder(paddingForElements);
        songsButton.setMargin(marginInsets);
        songsButton.setBackground(MaterialColors.WHITE);
        songsButton.addMouseListener(MaterialUIMovement.getMovement(songsButton, CustomColors.LIGHTER_GRAY));
        add(songsButton);

        favoritePlayListButton = new JButton("Favorite Songs");
        Icon favoritePlayListButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FAVORITE, 25, new Color(0, 0, 0));
        favoritePlayListButton.setIcon(favoritePlayListButtonIcon);
        favoritePlayListButton.setBorder(paddingForElements);
        favoritePlayListButton.setMargin(marginInsets);
        favoritePlayListButton.setBackground(MaterialColors.WHITE);
        favoritePlayListButton.addMouseListener(MaterialUIMovement.getMovement(favoritePlayListButton, CustomColors.LIGHTER_GRAY));
        add(favoritePlayListButton);

        albumsButton = new JButton("Albums");
        Icon albumsButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.RECENT_ACTORS, 25, new Color(0, 0, 0));
        albumsButton.setIcon(albumsButtonIcon);
        albumsButton.setBorder(paddingForElements);
        albumsButton.setMargin(marginInsets);
        albumsButton.setBackground(MaterialColors.WHITE);
        albumsButton.addMouseListener(MaterialUIMovement.getMovement(albumsButton, CustomColors.LIGHTER_GRAY));
        add(albumsButton);

        playListsButton = new JButton("Play Lists");
        Icon playListsButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FORMAT_LIST_BULLETED, 25, new Color(0, 0, 0));
        playListsButton.setIcon(playListsButtonIcon);
        playListsButton.setBorder(paddingForElements);
        playListsButton.setMargin(marginInsets);
        playListsButton.setBackground(MaterialColors.WHITE);
        playListsButton.addMouseListener(MaterialUIMovement.getMovement(playListsButton, CustomColors.LIGHTER_GRAY));
        add(playListsButton);

        editPlayListsButton = new JButton("Edit PlayLists");
        Icon editPlayListsButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EDIT, 25, new Color(0, 0, 0));
        editPlayListsButton.setIcon(editPlayListsButtonIcon);
        editPlayListsButton.setBorder(paddingForElements);
        editPlayListsButton.setMargin(marginInsets);
        editPlayListsButton.setBackground(MaterialColors.WHITE);
        editPlayListsButton.addMouseListener(MaterialUIMovement.getMovement(editPlayListsButton, CustomColors.LIGHTER_GRAY));
        add(editPlayListsButton);

        videoButton = new JButton("Videos");
        Icon videoButtonIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.VIDEO_LIBRARY, 25, new Color(0, 0, 0));
        videoButton.setIcon(videoButtonIcon);
        videoButton.setBorder(paddingForElements);
        videoButton.setMargin(marginInsets);
        videoButton.setBackground(MaterialColors.WHITE);
        videoButton.addMouseListener(MaterialUIMovement.getMovement(videoButton, MaterialColors.ORANGE_50));
        add(videoButton);
    }

    private void setButtonsEvents(Manager manager) {

        addSongButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.openAddSongsDialog();
            }
        });

        playListsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        albumsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.showAlbums();
            }
        });

        favoritePlayListButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.showFavoriteSongs();
            }
        });
        songsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.showAllSongs();
            }
        });
        videoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // todo video button in to menu clicked --> load video Lists
            }
        });
    }

    public void setEventListeners(Manager manager) {
        setButtonsEvents(manager);
    }
}
