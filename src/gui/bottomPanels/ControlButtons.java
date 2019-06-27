package gui.bottomPanels;

import com.manager.Manager;
import gui.CustomColors;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ControlButtons extends JPanel {

    //private
    ControlButtons() {
        //add(new JLabel("Control Buttons"));


        setBackground(MaterialColors.WHITE);
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());


        JPanel containerPanel=new JPanel();
        containerPanel.setLayout(new GridLayout(1, 5, 10, 10));
        containerPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

        JLabel shuffleButton = new JLabel();
        Icon shuffleIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHUFFLE, 20, CustomColors.DARK_GRAY);
        shuffleButton.setIcon(shuffleIcon);
        shuffleButton.setOpaque(false);
        containerPanel.add(shuffleButton);
        shuffleButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        JLabel previous = new JLabel();
        Icon previousIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SKIP_PREVIOUS, 20, CustomColors.DARK_GRAY);
        previous.setIcon(previousIcon);
        previous.setOpaque(false);
        containerPanel.add(previous);

        JLabel play = new JLabel();
        Icon playIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLAY_CIRCLE_FILLED, 35, CustomColors.PRIMARY);
        play.setIcon(playIcon);
        play.setOpaque(false);
        containerPanel.add(play);


        JLabel next = new JLabel();
        Icon nextIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SKIP_NEXT, 20, CustomColors.DARK_GRAY);
        next.setIcon(nextIcon);
        next.setOpaque(false);
        containerPanel.add(next);


        JLabel repeat = new JLabel();
        Icon repeatIcon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPEAT, 20, CustomColors.DARK_GRAY);
        repeat.setIcon(repeatIcon);
        repeat.setOpaque(false);
        containerPanel.add(repeat);

        containerPanel.setOpaque(false);
        add(containerPanel);


    }

}
