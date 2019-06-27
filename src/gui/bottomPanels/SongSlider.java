package gui.bottomPanels;

import com.manager.Manager;
import gui.CustomColors;
import mdlaf.components.slider.MaterialSliderUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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


        slider = new JSlider(JSlider.HORIZONTAL, 0, BottomPanel.MAX_SLIDER_VALUE, 2);
        slider.setForeground(CustomColors.PRIMARY);
        slider.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        slider.setOpaque(false);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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


     int getSliderValue(){
        return slider.getValue();
    }


    void setSliderEvents(Manager manager){


        slider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                manager.setSliderMouseDownEvent();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                manager.setSliderMouseUpEvent();

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                manager.setSliderMouseExitEvent();
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                manager.songSliderChangeEventCall();
                //todo pay attention
            }
        });
    }


}

