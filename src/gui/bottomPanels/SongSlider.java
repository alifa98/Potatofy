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
    SongSlider() {

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        setMinimumSize(new Dimension(250,64));

        //add(new JLabel("SongSlider"));

        JLabel currentSongTime=new JLabel("00:00");
        currentSongTime.setForeground(CustomColors.DARK_GRAY);
        add(currentSongTime,BorderLayout.WEST);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 65535, 2);
        //UIManager sliderUI=new UIManager();
        //slider.setUI(new CustomSliderUi(slider));
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

        JLabel totalSongTime=new JLabel("00:00");
        totalSongTime.setForeground(CustomColors.DARK_GRAY);
        add(totalSongTime,BorderLayout.EAST);

    }

}

//class CustomSliderUi extends MaterialSliderUI {
//     CustomSliderUi(JSlider slider) {
//        super(slider);
//    }
//}