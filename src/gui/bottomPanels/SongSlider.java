package gui.bottomPanels;

import gui.CustomColors;
import mdlaf.components.slider.MaterialSliderUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SongSlider extends JPanel {

    private JLabel currentSongTime;
    private JSlider slider;
    private JLabel totalSongTime;

    SongSlider() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        setMinimumSize(new Dimension(250,64));



        currentSongTime = new JLabel("");
        currentSongTime.setForeground(CustomColors.DARK_GRAY);
        add(currentSongTime,BorderLayout.WEST);


        slider = new JSlider(JSlider.HORIZONTAL, 0, 65535, 2);
        slider.setForeground(CustomColors.PRIMARY);
        slider.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        slider.setOpaque(false);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JSlider sourceSlider = (JSlider) e.getSource();
                BasicSliderUI ui = (MaterialSliderUI) sourceSlider.getUI();
                int value = ui.valueForXPosition(e.getX());
                slider.setValue(value);

            }
        });
        add(slider,BorderLayout.CENTER);


        totalSongTime = new JLabel("");
        totalSongTime.setForeground(CustomColors.DARK_GRAY);
        add(totalSongTime,BorderLayout.EAST);

    }

    void setTotalSongTime(String totalSongTime) {
        this.totalSongTime.setText(totalSongTime);
    }

    void setCurrentSongTime(String currentSongTime) {
        this.currentSongTime.setText(currentSongTime);
    }

    /**
     *
     * @param position the maximum value is 65535 and the minimum is 0
     */
    void setSliderPosition(int position){
        slider.setValue(position);
    }

}

