package gui.bottomPanels;

import com.manager.Manager;
import gui.CustomColors;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlButtons extends JPanel {

    private JLabel shuffleButton;
    private JLabel previous;
    private JLabel play;
    private JLabel next;
    private JLabel repeat;

    private boolean shuffleActive;
    private boolean repeatActive;
    private boolean isPlaying;

    private ControlButtons controlButtons=this;


    ControlButtons() {
        shuffleActive=false;
        repeatActive=false;
        isPlaying=false;


        setBackground(MaterialColors.WHITE);
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());


        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new GridLayout(1, 5, 10, 10));
        containerPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));


        shuffleButton = new JLabel();
        Icon shuffleIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHUFFLE, 20, CustomColors.LIGHT_GRAY);
        shuffleButton.setIcon(shuffleIcon);
        shuffleButton.setOpaque(false);
        containerPanel.add(shuffleButton);


        previous = new JLabel();
        Icon previousIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SKIP_PREVIOUS, 20, CustomColors.DARK_GRAY);
        previous.setIcon(previousIcon);
        previous.setOpaque(false);
        containerPanel.add(previous);


        play = new JLabel();
        Icon playIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_CIRCLE_FILLED, 35, CustomColors.PRIMARY);
        play.setIcon(playIcon);
        play.setOpaque(false);
        containerPanel.add(play);


        next = new JLabel();
        Icon nextIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SKIP_NEXT, 20, CustomColors.DARK_GRAY);
        next.setIcon(nextIcon);
        next.setOpaque(false);
        containerPanel.add(next);


        repeat = new JLabel();
        Icon repeatIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPEAT, 20, CustomColors.LIGHT_GRAY);
        repeat.setIcon(repeatIcon);
        repeat.setOpaque(false);
        containerPanel.add(repeat);

        containerPanel.setOpaque(false);
        add(containerPanel);


    }

    void setControlButtonsListener(Manager manager) {
        shuffleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shuffleActive=!shuffleActive;
                manager.shuffleClickEvent(shuffleActive);
                updateBottomStates();
            }
        });

        previous.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.previousClickEvent();
            }
        });

        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               manager.playPauseClickEvent(controlButtons);
            }
        });
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.nextClickEvent();
            }
        });

        repeat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                repeatActive=!repeatActive;
                manager.repeatClickEvent(repeatActive);
                updateBottomStates();
            }
        });


    }

    private void updateBottomStates(){
        if(shuffleActive){
            Icon shuffleIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHUFFLE, 20, CustomColors.PRIMARY);
            shuffleButton.setIcon(shuffleIcon);
        }

        else{
            Icon shuffleIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHUFFLE, 20, CustomColors.LIGHT_GRAY);
            shuffleButton.setIcon(shuffleIcon);
        }

        if(repeatActive){
            Icon repeatIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPEAT, 20, CustomColors.PRIMARY);
            repeat.setIcon(repeatIcon);
        }else{
            Icon repeatIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPEAT, 20, CustomColors.LIGHT_GRAY);
            repeat.setIcon(repeatIcon);
        }

        if(isPlaying){
            Icon playIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAUSE_CIRCLE_FILLED, 35, CustomColors.PRIMARY);
            play.setIcon(playIcon);
        }else{
            Icon playIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_CIRCLE_FILLED, 35, CustomColors.PRIMARY);
            play.setIcon(playIcon);
        }

    }

    void setPlayingState(boolean isPlaying){
        this.isPlaying=isPlaying;
        updateBottomStates();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
        updateBottomStates();
    }
}
